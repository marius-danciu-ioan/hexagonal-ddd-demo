package com.marius.hexagonalddddemo.domain.services;

public interface Validate {
    void validateNotBlankAndFixedLength(String item, String itemName, int length);
}
