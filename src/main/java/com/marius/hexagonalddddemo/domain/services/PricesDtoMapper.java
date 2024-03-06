package com.marius.hexagonalddddemo.domain.services;

import com.marius.hexagonalddddemo.domain.dto.PricesDto;
import com.marius.hexagonalddddemo.domain.dto.PricesResponseDto;

public interface PricesDtoMapper {
    PricesResponseDto ToPricesResponseDto(PricesDto pricesDto, String applicationDate);
}
