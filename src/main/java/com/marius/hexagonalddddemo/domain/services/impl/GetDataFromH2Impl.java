package com.marius.hexagonalddddemo.domain.services.impl;

import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.marius.hexagonalddddemo.domain.dto.PricesDto;
import com.marius.hexagonalddddemo.domain.services.GetDataFromH2;
import com.marius.hexagonalddddemo.domain.services.PricesDtoMapper;
import com.marius.hexagonalddddemo.infrastructure.constants.Constants;
import com.marius.hexagonalddddemo.infrastructure.error.ServiceException;
import com.marius.hexagonalddddemo.infrastructure.repositories.H2Repository;

import java.util.List;

/**
 * Get items from database and send as Mono
 */
@Slf4j
@Service
@Builder
public class GetDataFromH2Impl implements GetDataFromH2 {
    @Autowired
    H2Repository h2Repository;
    @Autowired
    PricesDtoMapper pricesDtoMapper;

    /**
     * Get prices for a product from H2 database for a specific date and brand
     * @param applicationDate application date for the price
     * @param brandId brand id
     * @param productId product id
     * @return list of prices dto
     */
    public List<PricesDto> getPrices(String applicationDate, String brandId, String productId){
        log.info("getPrices -> applicationDate: " + applicationDate + " | brandId: " + brandId + " | productId: " + productId);
        //get price list from h2 database
        List<PricesDto> result = h2Repository.findByDataAndBrandIdAndProductId(applicationDate, brandId, productId);
        log.info("Result: " + result.toString());
        //error if price list is empty
        if(result.isEmpty()){
            log.error("No price found for applicationDate: " + applicationDate + " | brandId: " + brandId + " | productId: " + productId);
            throw new ServiceException(String.valueOf(HttpStatus.NOT_FOUND.value()), "No price found for applicationDate: " + applicationDate + " | brandId: " + brandId + " | productId: " + productId);
        }
        log.info(Constants.SUCCESS);
        //return the result
        return result;
    }
}
