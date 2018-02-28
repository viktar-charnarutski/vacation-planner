package com.viktarx.vacationplanner.agent;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Tests for the Price class.
 */
public class PriceTest {

    private Price validPrice = new Price(1099.0, 999.9);

    @Test
    public void originalPriceInUsd() throws Exception {
        assertTrue(1099.0 == validPrice.originalPriceInUsd());
    }

    @Test
    public void discountPriceInUsd() throws Exception {
        assertTrue(999.9 == validPrice.discountPriceInUsd());
    }

    @Test
    public void discountPercentage() throws Exception {
        assertTrue(9.01728844404004 == validPrice.discountPercentage());
    }

    @Test
    public void wrongDiscountPriceCheck() throws Exception {
        boolean checkHandled = false;
        try {
            new Price(899.0, 999.9);
        } catch (IllegalArgumentException e) {
            checkHandled = true;
        }
        assertTrue(checkHandled);
    }

    @Test
    public void negativeDiscountPriceCheck() throws Exception {
        boolean checkHandled = false;
        try {
            new Price(899.0, -1.0);
        } catch (IllegalArgumentException e) {
            checkHandled = true;
        }
        assertTrue(checkHandled);
    }

    @Test
    public void negativeOriginalPriceCheck() throws Exception {
        boolean checkHandled = false;
        try {
            new Price(-1.0, 999.9);
        } catch (IllegalArgumentException e) {
            checkHandled = true;
        }
        assertTrue(checkHandled);
    }

}