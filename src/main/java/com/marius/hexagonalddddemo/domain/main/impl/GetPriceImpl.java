package com.marius.hexagonalddddemo.domain.main.impl;

import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import com.marius.hexagonalddddemo.domain.dto.PricesResponseDto;
import com.marius.hexagonalddddemo.domain.main.GetPrice;
import com.marius.hexagonalddddemo.domain.services.GetDataFromH2;
import com.marius.hexagonalddddemo.domain.services.GetPriceByPriority;
import com.marius.hexagonalddddemo.domain.services.PricesDtoMapper;
import com.marius.hexagonalddddemo.domain.services.Validate;
import com.marius.hexagonalddddemo.infrastructure.constants.Constants;


import java.util.Map;

/**
 * Main interactor
 */
@Slf4j
@Service
@Builder
public class GetPriceImpl implements GetPrice {
    GetDataFromH2 getDataFromH2;
    Validate validate;
    GetPriceByPriority getPriceByPriority;
    PricesDtoMapper pricesDtoMapper;

    /**
     * Functional logic to get the price from a price list
     * @param entryData end point entry data
     * @return price
     */
    public PricesResponseDto execute(final Map<String, String> entryData){
        var applicationDate = entryData.get(Constants.APPLICATION_DATE);
        var brandId = entryData.get(Constants.BRAND_ID);
        var productId = entryData.get(Constants.PRODUCT_ID);
        log.info("Getting prices for applicationDate: " + applicationDate + " | productId: " + productId + " | brandId: " + brandId);

        //validate data, brandId, productId
        validate.validateNotBlankAndFixedLength(applicationDate, Constants.APPLICATION_DATE,19);
        validate.validateNotBlankAndFixedLength(brandId, Constants.BRAND_ID,1);
        validate.validateNotBlankAndFixedLength(productId, Constants.PRODUCT_ID,5);
        //get list of prices
        var prices = getDataFromH2.getPrices(applicationDate, brandId, productId);
        //get price from price list by priority
        var price = getPriceByPriority.getPrice(prices);
        //map priceDto to pricesResponseDto
        var result = pricesDtoMapper.ToPricesResponseDto(price, applicationDate);
        //return PricesResponseDto
        return result;
    }
}
