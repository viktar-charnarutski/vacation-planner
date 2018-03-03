package com.viktarx.vacationplanner.trip;

/**
 * Price's functional structure.
 */
public interface Price {

    double originalPriceInUsd();

    double discountPriceInUsd();

    double discountPercentage();

}
