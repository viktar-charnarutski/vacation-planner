package com.viktarx.agent;

import java.net.URL;
import java.time.LocalDate;

/**
 * The class contains information about a trip.
 */
public class Trip {

    private final String departure;
    private final String destination;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final double priceInDollars;
    private final URL url;

    public Trip(String departure, String destination, LocalDate startDate, LocalDate endDate, double priceInDollars, URL url) {
        checkArguments(departure, destination, startDate, endDate, priceInDollars, url);
        this.departure = departure;
        this.destination = destination;
        this.startDate = startDate;
        this.endDate = endDate;
        this.priceInDollars = priceInDollars;
        this.url = url;
    }

    public String departure() {
        return departure;
    }

    public String destination() {
        return destination;
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

    private static void checkArguments(String departure, String destination, LocalDate startDate, LocalDate endDate, double priceInDollars, URL url) {
        checkLocations(departure, destination);
        checkDates(startDate, endDate);
        checkPrice(priceInDollars);
        checkUrl(url);
    }

    private static void checkLocations(String departure, String destination) {
        if (locationsAreTheSame(departure, destination))
            throw new IllegalArgumentException(String.format("Departure %s should be different from destinations %s",
                    departure, destination));
    }

    private static boolean locationsAreTheSame(String departureCity, String destinationCity) {
        return departureCity.equals(destinationCity);
    }

    private static void checkDates(LocalDate startDate, LocalDate endDate) {
        if (endDate.isBefore(startDate) || startDate.equals(endDate))
            throw new IllegalArgumentException(String.format("The start date %s should be after the end date %s",
                    startDate, endDate));
    }

    private static void checkPrice(double price) {
        if (price < 0)
            throw new IllegalArgumentException(String.format("Price %s could not be negative", price));
    }

    private static void checkUrl(URL url) {
        if (url == null)
            throw new IllegalArgumentException(String.format("Wrong URL was provided: %s", url));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Trip)) return false;

        Trip trip = (Trip) o;

        if (Double.compare(trip.priceInDollars, priceInDollars) != 0) return false;
        if (!departure.equals(trip.departure)) return false;
        if (!destination.equals(trip.destination)) return false;
        if (!startDate.equals(trip.startDate)) return false;
        if (!endDate.equals(trip.endDate)) return false;
        return url.equals(trip.url);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = departure.hashCode();
        result = 31 * result + destination.hashCode();
        result = 31 * result + startDate.hashCode();
        result = 31 * result + endDate.hashCode();
        temp = Double.doubleToLongBits(priceInDollars);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + url.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Trip{" +
                "departure='" + departure + '\'' +
                ", destination='" + destination + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", priceInDollars=" + priceInDollars +
                ", url=" + url +
                '}';
    }
}
