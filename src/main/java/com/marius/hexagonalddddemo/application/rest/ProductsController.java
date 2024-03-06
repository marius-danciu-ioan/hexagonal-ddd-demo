package com.marius.hexagonalddddemo.application.rest;

import com.marius.hexagonalddddemo.application.rest.annotations.CommonApiResponses;
import com.marius.hexagonalddddemo.application.rest.dto.SuccessfulResponseDto;
import com.marius.hexagonalddddemo.domain.dto.PricesResponseDto;
import com.marius.hexagonalddddemo.domain.main.GetPrice;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.marius.hexagonalddddemo.infrastructure.constants.Constants;
import com.marius.hexagonalddddemo.infrastructure.constants.ServiceStatus;

import java.util.HashMap;

/**
 * Rest controller for prices
 */
@Slf4j
@RestController
@Tag(name = "Get Price", description = "Get the price of a product from database")
@CommonApiResponses
@AllArgsConstructor
@RequestMapping("/v1")
public class ProductsController {

    private final GetPrice getPrice;

    @Operation(
        summary = "Get the price of a product",
        description = "Service that retrieves a price according to filters.",
        security = { @SecurityRequirement(name = "BearerAuth") },
        parameters = {
                @Parameter(name = Constants.APPLICATION_DATE
                        , in = ParameterIn.PATH
                        , description = "Application date (yyyy-mm-dd hh.mm.ss)"
                        , required = true
                        , example = "2020-06-15 15.00.00"),
                @Parameter(name = Constants.PRODUCT_ID
                        , in = ParameterIn.PATH
                        , description = "Product id"
                        , required = true
                        , example = "35455"),
                @Parameter(name = Constants.BRAND_ID
                        , in = ParameterIn.PATH
                        , description = "Brand id"
                        , required = true
                        , example = "1"),
        }
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "202", description = "Accepted")
    })
    @CrossOrigin(origins = "*")
    @GetMapping(value = "/get-price/{" + Constants.APPLICATION_DATE + "}/{"+ Constants.PRODUCT_ID+"}/{"+ Constants.BRAND_ID+"}")
    public ResponseEntity<SuccessfulResponseDto<PricesResponseDto>> getPrice(
            @Parameter(hidden = true) @RequestHeader(value = HttpHeaders.AUTHORIZATION) String authorization,
            @PathVariable(value = Constants.APPLICATION_DATE) String applicationDate,
            @PathVariable(value = Constants.PRODUCT_ID) String productId,
            @PathVariable(value = Constants.BRAND_ID) String brandId
    ) {

        //create parameters from input
        var parameters = new HashMap<String, String>();
        parameters.put(HttpHeaders.AUTHORIZATION, authorization);
        parameters.put(Constants.APPLICATION_DATE, StringUtils.defaultIfBlank(applicationDate, null));
        parameters.put(Constants.PRODUCT_ID, StringUtils.defaultIfBlank(productId, null));
        parameters.put(Constants.BRAND_ID, StringUtils.defaultIfBlank(brandId, null));

        log.info("Price asked for applicationDate: " + applicationDate + " | productId: " + productId + " | brandId: " + brandId);
        //get price
        var price = getPrice.execute(parameters);
        //build SuccessfulResponseDto<PricesResponseDto>
        var result = SuccessfulResponseDto.<PricesResponseDto>builder()
                    .status(HttpStatus.OK.getReasonPhrase())
                    .message(HttpStatus.OK.getReasonPhrase())
                    .data(price)
                    .messageId(String.valueOf(ServiceStatus.OK.getMessageId()))
                    .build();
        return ResponseEntity.ok().body(result);
    }
}
