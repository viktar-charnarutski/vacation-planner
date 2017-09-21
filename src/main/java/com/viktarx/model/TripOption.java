package com.viktarx.model;

import java.net.URL;
import java.time.LocalDate;

/**
 * The class contains information of a trip.
 */
public class TripOption {

    private final LocalDate startDate;
    private final LocalDate endDate;
    private final double priceInDollars;
    private final URL url;

    public TripOption(LocalDate startDate, LocalDate endDate, double priceInDollars, URL url) {
        checkArguments(startDate, endDate, priceInDollars, url);
        this.startDate = startDate;
        this.endDate = endDate;
        this.priceInDollars = priceInDollars;
        this.url = url;
    }

    public LocalDate startDate() {
        return startDate;
    }

    public LocalDate endDate() {
        return endDate;
    }

    public double priceInDollars() {
        return priceInDollars;
    }

    public URL url() {
        return url;
    }

    private void checkArguments(LocalDate startDate, LocalDate endDate, double priceInDollars, URL url) {
        checkDates(startDate, endDate);
        checkPrice(priceInDollars);
        checkUrl(url);
    }

    private void checkDates(LocalDate startDate, LocalDate endDate) {
        if (endDate.isBefore(startDate) || startDate.equals(endDate))
            throw new IllegalArgumentException(String.format("The start date %s should be after the end date %s",
                    startDate, endDate));
    }

    private void checkPrice(double price) {
        if (price < 0)
            throw new IllegalArgumentException(String.format("Price %s could not be negative", price));
    }

    private void checkUrl(URL url) {
        if (url == null)
            throw new IllegalArgumentException(String.format("Wrong URL was provided: %s", url));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TripOption)) return false;

        TripOption that = (TripOption) o;

        if (Double.compare(that.priceInDollars, priceInDollars) != 0) return false;
        if (!startDate.equals(that.startDate)) return false;
        if (!endDate.equals(that.endDate)) return false;
        return url.equals(that.url);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = startDate.hashCode();
        result = 31 * result + endDate.hashCode();
        temp = Double.doubleToLongBits(priceInDollars);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + url.hashCode();
        return result;
    }
}
