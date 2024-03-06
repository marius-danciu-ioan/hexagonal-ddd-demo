package com.marius.hexagonalddddemo.domain.main.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.marius.hexagonalddddemo.domain.services.GetDataFromH2;
import com.marius.hexagonalddddemo.domain.services.GetPriceByPriority;
import com.marius.hexagonalddddemo.domain.services.PricesDtoMapper;
import com.marius.hexagonalddddemo.domain.services.Validate;
import com.marius.hexagonalddddemo.infrastructure.constants.Constants;

@ExtendWith(MockitoExtension.class)
public class GetPriceImplTest {

    @Mock
    Validate validate;
    @Mock
    GetDataFromH2 getDataFromH2;
    @Mock
    GetPriceByPriority getPriceByPriority;
    @Mock
    PricesDtoMapper pricesDtoMapper;

    @InjectMocks
    private GetPriceImpl getPrice;
  
    @Test
    void testExecute() {
         Map<String, String> entryData = new HashMap<>();
         entryData.put(Constants.APPLICATION_DATE, "2020-06-15 15.00.00");
         entryData.put(Constants.BRAND_ID, "35455");
         entryData.put(Constants.PRODUCT_ID, "1");
         when(getDataFromH2.getPrices(anyString(), anyString(), anyString())).thenReturn(null);
         when(getPriceByPriority.getPrice(any())).thenReturn(null);
         when(pricesDtoMapper.ToPricesResponseDto(any(), anyString())).thenReturn(null);
         var result = getPrice.execute(entryData);
         assertEquals(null, result);
    }
}
