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
    private final double priceInUsd;
    private final Hotel hotel;
    private final URL url;

    public Trip(String departure, String destination, LocalDate startDate, LocalDate endDate, double priceInUsd, Hotel hotel, URL url) {
        checkArguments(departure, destination, startDate, endDate, priceInUsd, hotel, url);
        this.departure = departure;
        this.destination = destination;
        this.startDate = startDate;
        this.endDate = endDate;
        this.priceInUsd = priceInUsd;
        this.hotel = hotel;
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

    public double priceInUsd() {
        return priceInUsd;
    }

    public Hotel hotel() {
        return hotel;
    }

    public URL url() {
        return url;
    }

    private static void checkArguments(String departure, String destination, LocalDate startDate, LocalDate endDate, double priceInUsd, Hotel hotel, URL url) {
        checkLocations(departure, destination);
        checkDates(startDate, endDate);
        checkPrice(priceInUsd);
        checkHotel(hotel);
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

    private static void checkHotel(Hotel hotel) {
        if (hotel == null)
            throw new IllegalArgumentException("Hotel should not be null.");
    }

    private static void checkUrl(URL url) {
        if (url == null)
            throw new IllegalArgumentException("URL should not be null.");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Trip)) return false;

        Trip trip = (Trip) o;

        if (Double.compare(trip.priceInUsd, priceInUsd) != 0) return false;
        if (!departure.equals(trip.departure)) return false;
        if (!destination.equals(trip.destination)) return false;
        if (!startDate.equals(trip.startDate)) return false;
        if (!endDate.equals(trip.endDate)) return false;
        if (!hotel.equals(trip.hotel)) return false;
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
        temp = Double.doubleToLongBits(priceInUsd);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + hotel.hashCode();
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
                ", priceInUsd=" + priceInUsd +
                ", hotel=" + hotel +
                ", url=" + url +
                '}';
    }
}
