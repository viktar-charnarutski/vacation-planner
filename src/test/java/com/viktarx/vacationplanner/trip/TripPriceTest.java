package com.viktarx.vacationplanner.trip;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Tests for the TripPrice class.
 */
public class TripPriceTest {

    private TripPrice validTripPrice = new TripPrice(1099.0, 999.9);

    @Test
    public void originalPriceInUsd() {
        assertTrue(1099.0 == validTripPrice.originalPriceInUsd());
    }

    @Test
    public void discountPriceInUsd() {
        assertTrue(999.9 == validTripPrice.discountPriceInUsd());
    }

    @Test
    public void discountPercentage() {
        assertTrue(9.01728844404004 == validTripPrice.discountPercentage());
    }

    @Test
    public void wrongDiscountPriceCheck() {
        boolean checkHandled = false;
        try {
            new TripPrice(899.0, 999.9);
        } catch (IllegalArgumentException e) {
            checkHandled = true;
        }
        assertTrue(checkHandled);
    }

    @Test
    public void negativeDiscountPriceCheck() {
        boolean checkHandled = false;
        try {
            new TripPrice(899.0, -1.0);
        } catch (IllegalArgumentException e) {
            checkHandled = true;
        }
        assertTrue(checkHandled);
    }

    @Test
    public void negativeOriginalPriceCheck() {
        boolean checkHandled = false;
        try {
            new TripPrice(-1.0, 999.9);
        } catch (IllegalArgumentException e) {
            checkHandled = true;
        }
        assertTrue(checkHandled);
    }

}