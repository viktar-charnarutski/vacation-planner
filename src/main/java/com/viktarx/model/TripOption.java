package com.viktarx.model;

import java.time.LocalDate;

/**
 *
 */
public class TripOption {

    private final LocalDate startDate;
    private final LocalDate endDate;
    private final double priceInDollars;
    private final String webLink;

    public TripOption(LocalDate startDate, LocalDate endDate, double priceInDollars, String webLink) {

        checkArguments(startDate, endDate, priceInDollars, webLink);

        this.startDate = startDate;
        this.endDate = endDate;
        this.priceInDollars = priceInDollars;
        this.webLink = webLink;
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

    public String webLink() {
        return webLink;
    }

    private void checkArguments(LocalDate startDate, LocalDate endDate, double priceInDollars, String webLink) {
        checkDates(startDate, endDate);
        checkPrice(priceInDollars);
        checkWebLink(webLink);
    }

    private void checkWebLink(String webLink) {
        if (webLink == null || webLink.length() < 10)
            throw new IllegalArgumentException(String.format("Wrong web link was provided: %s", webLink));
    }

    private void checkPrice(double price) {
        if (price > 0)
            throw new IllegalArgumentException(String.format("Price %s could not be negative", price));
    }

    private void checkDates(LocalDate startDate, LocalDate endDate) {
        if (startDate.isBefore(endDate) || startDate.equals(endDate))
            throw new IllegalArgumentException(String.format("The start date %s should be after the end date %s",
                    startDate, endDate));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TripOption)) return false;

        TripOption that = (TripOption) o;

        return webLink.equals(that.webLink);
    }

    @Override
    public int hashCode() {
        return webLink.hashCode();
    }
}
