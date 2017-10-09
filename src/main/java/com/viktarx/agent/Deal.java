package com.viktarx.agent;

/**
 * Deal's functional structure.
 */
public interface Deal {

    double originalPriceInUsd();

    double discountPriceInUsd();

    double discountPercentage();

}
