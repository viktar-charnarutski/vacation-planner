package com.viktarx.agent;

/**
 * Price representation.
 */
public final class Price implements Deal {

    private final double originalPriceInUsd;
    private final double discountPriceInUsd;

    Price(double originalPriceInUsd, double discountPriceInUsd) {
        checkParameters(originalPriceInUsd, discountPriceInUsd);
        this.originalPriceInUsd = originalPriceInUsd;
        this.discountPriceInUsd = discountPriceInUsd;
    }

    @Override
    public double originalPriceInUsd() {
        return originalPriceInUsd;
    }

    @Override
    public double discountPriceInUsd() {
        return discountPriceInUsd;
    }

    @Override
    public double discountPercentage() {
        return (originalPriceInUsd - discountPriceInUsd) / originalPriceInUsd * 100;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Price)) return false;

        Price price = (Price) o;

        if (Double.compare(price.originalPriceInUsd, originalPriceInUsd) != 0) return false;
        return Double.compare(price.discountPriceInUsd, discountPriceInUsd) == 0;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(originalPriceInUsd);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(discountPriceInUsd);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Price{" +
                "originalPriceInUsd=" + originalPriceInUsd +
                ", discountPriceInUsd=" + discountPriceInUsd +
                '}';
    }

    private static void checkParameters(double originalPriceInUsd, double finalPriceInUsd) {
        if (originalPriceInUsd < 0)
            throw new IllegalArgumentException(String.format("Price %s should not be negative.", originalPriceInUsd));

        if (finalPriceInUsd < 0)
            throw new IllegalArgumentException(String.format("Price %s should not be negative.", finalPriceInUsd));

        if (originalPriceInUsd < finalPriceInUsd)
            throw new IllegalArgumentException(String.format("Original price %s should not be less than discount price %s.",
                    originalPriceInUsd, finalPriceInUsd));
    }
}
