package com.viktarx.vacationplanner.agent;

/**
 * Deal's functional structure.
 */
public interface Deal {

    double originalPriceInUsd();

    double discountPriceInUsd();

    double discountPercentage();

}
