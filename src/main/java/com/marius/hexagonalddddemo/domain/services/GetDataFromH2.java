package com.marius.hexagonalddddemo.domain.services;

import java.util.List;

import com.marius.hexagonalddddemo.domain.dto.PricesDto;

public interface GetDataFromH2 {
    List<PricesDto> getPrices(String data, String brandId, String productId);
}
