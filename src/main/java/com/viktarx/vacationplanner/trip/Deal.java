package com.viktarx.vacationplanner.trip;

/**
 * Deal's functional structure.
 */
public interface Deal {

    double originalPriceInUsd();

    double discountPriceInUsd();

    double discountPercentage();

}
