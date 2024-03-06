package com.marius.hexagonalddddemo.domain.services;

import java.util.List;

import com.marius.hexagonalddddemo.domain.dto.PricesDto;

public interface GetPriceByPriority {
    PricesDto getPrice(List<PricesDto> priceList);
}
