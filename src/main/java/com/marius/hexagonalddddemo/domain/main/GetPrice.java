package com.marius.hexagonalddddemo.domain.main;


import java.util.Map;

import com.marius.hexagonalddddemo.domain.dto.PricesResponseDto;

public interface GetPrice {
    PricesResponseDto execute(final Map<String, String> entryData);
}
