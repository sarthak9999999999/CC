package com.example.cc;

import org.junit.Test;
import static org.junit.Assert.assertTrue;

public class Tests {


    @Test
    public void validMasterCardCardNumber() {
        assertTrue(CardModel.isValidCardNumber("2222420000001113"));

    }

    @Test
    public void validVisaCardCardNumber() {
        assertTrue(CardModel.isValidCardNumber("4111111111111111"));
    }

    @Test
    public void validAmericanCardNumber() {
        assertTrue(CardModel.isValidCardNumber("378282246310005"));
    }
}
