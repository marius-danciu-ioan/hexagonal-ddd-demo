package com.marius.hexagonalddddemo.domain.services.impl;

import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.marius.hexagonalddddemo.domain.dto.PricesDto;
import com.marius.hexagonalddddemo.domain.services.GetPriceByPriority;
import com.marius.hexagonalddddemo.infrastructure.error.ServiceException;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**
 * Filter price from price list
 */
@Slf4j
@Service
@Builder
public class GetPriceByPriorityImpl implements GetPriceByPriority {

    /**
     * Get price from price list where the criteria is:
     * chose the price with the max priority
     * @param priceList price list
     * @return price dto
     */
    public PricesDto getPrice(List<PricesDto> priceList){
        log.info("Filtering prices");
        //filter price
        Optional<PricesDto> optional = priceList.stream()
                .max(Comparator.comparing(PricesDto::getPriority));
        //if empty throw error
        if(optional.isEmpty()){
            throw new ServiceException(String.valueOf(HttpStatus.NO_CONTENT.value()), "No price found!");
        }
        //return the price
        return optional.get();
    }
}
