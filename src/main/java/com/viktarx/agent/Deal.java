package com.viktarx.agent;

/**
 * Deal's functional structure.
 */
public interface Deal {

    double originalPriceInUsd();

    double finalPriceInUsd();

    double discountPercentage();

}
