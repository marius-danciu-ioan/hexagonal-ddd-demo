package com.marius.hexagonalddddemo.domain.services.impl;

import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import com.marius.hexagonalddddemo.domain.dto.PricesDto;
import com.marius.hexagonalddddemo.domain.dto.PricesResponseDto;
import com.marius.hexagonalddddemo.domain.services.PricesDtoMapper;

/**
 * Simple mapper class
 */
@Slf4j
@Service
@Builder
public class PricesDtoMapperImpl implements PricesDtoMapper {

    /**
     * Map values from pricesDto and  applicationDate to PricesResponseDto
     * @param pricesDto price
     * @param applicationDate application date
     * @return PricesResponseDto
     */
    public PricesResponseDto ToPricesResponseDto(PricesDto pricesDto, String applicationDate){
        log.info("ToPricesResponseDto -> pricesDto: " + pricesDto + " | applicationDate: " + applicationDate);
        return PricesResponseDto.builder()
                .productId(pricesDto.getProductId())
                .brandId(pricesDto.getBrandId())
                .priceList(pricesDto.getPriceList())
                .applicationDate(applicationDate)
                .price(pricesDto.getPrice().toString())
                .build();
    }
}
