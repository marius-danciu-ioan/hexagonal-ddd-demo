package com.marius.hexagonalddddemo.domain.services.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import com.marius.hexagonalddddemo.infrastructure.error.ServiceException;

public class ValidateImplTest {
    
    ValidateImpl validate = new ValidateImpl();


    @Test
    void testValidateNotBlankAndFixedLength() {

        try{
            validate.validateNotBlankAndFixedLength("marius", "myName", 0);
        }catch(ServiceException e){
            assertEquals("Incorrect length for myName", e.getMessage());
        }
    }
}
