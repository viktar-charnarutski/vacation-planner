package com.viktarx.vacationplanner.trip;

import java.net.URL;
import java.time.LocalDate;

/**
 * The class contains information about a trip.
 */
public final class FlightHotelBundle implements VacationTrip {

    private final String departure;
    private final String destination;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final TripPrice tripPrice;
    private final Hotel hotel;
    private final URL url;

    FlightHotelBundle(String departure, String destination, LocalDate startDate, LocalDate endDate, TripPrice tripPrice, Hotel hotel, URL url) {
        checkArguments(departure, destination, startDate, endDate, tripPrice, hotel, url);
        this.departure = departure;
        this.destination = destination;
        this.startDate = startDate;
        this.endDate = endDate;
        this.tripPrice = tripPrice;
        this.hotel = hotel;
        this.url = url;
    }

    @Override
    public String departure() {
        return departure;
    }

    @Override
    public String destination() {
        return destination;
    }

    @Override
    public LocalDate startDate() {
        return startDate;
    }

    @Override
    public LocalDate endDate() {
        return endDate;
    }

    @Override
    public TripPrice price() {
        return tripPrice;
    }

    public Hotel hotel() {
        return hotel;
    }

    public URL url() {
        return url;
    }

    private static void checkArguments(String departure, String destination, LocalDate startDate, LocalDate endDate, TripPrice tripPrice, Hotel hotel, URL url) {
        checkLocations(departure, destination);
        checkDates(startDate, endDate);
        checkPrice(tripPrice);
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

    private static void checkPrice(TripPrice tripPrice) {
        if (tripPrice == null)
            throw new IllegalArgumentException("TripPrice object could not be null");
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
        if (!(o instanceof FlightHotelBundle)) return false;

        FlightHotelBundle flightHotelBundle = (FlightHotelBundle) o;

        if (!departure.equals(flightHotelBundle.departure)) return false;
        if (!destination.equals(flightHotelBundle.destination)) return false;
        if (!startDate.equals(flightHotelBundle.startDate)) return false;
        if (!endDate.equals(flightHotelBundle.endDate)) return false;
        if (!tripPrice.equals(flightHotelBundle.tripPrice)) return false;
        if (!hotel.equals(flightHotelBundle.hotel)) return false;
        return url.equals(flightHotelBundle.url);
    }

    @Override
    public int hashCode() {
        int result = departure.hashCode();
        result = 31 * result + destination.hashCode();
        result = 31 * result + startDate.hashCode();
        result = 31 * result + endDate.hashCode();
        result = 31 * result + tripPrice.hashCode();
        result = 31 * result + hotel.hashCode();
        result = 31 * result + url.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "FlightHotelBundle{" +
                "departure='" + departure + '\'' +
                ", destination='" + destination + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", tripPrice=" + tripPrice +
                ", hotel=" + hotel +
                ", url=" + url +
                '}';
    }
}
