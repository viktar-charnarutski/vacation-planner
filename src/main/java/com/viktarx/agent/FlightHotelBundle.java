package com.viktarx.agent;

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
    private final Price price;
    private final Hotel hotel;
    private final URL url;

    FlightHotelBundle(String departure, String destination, LocalDate startDate, LocalDate endDate, Price price, Hotel hotel, URL url) {
        checkArguments(departure, destination, startDate, endDate, price, hotel, url);
        this.departure = departure;
        this.destination = destination;
        this.startDate = startDate;
        this.endDate = endDate;
        this.price = price;
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
    public Price price() {
        return price;
    }

    public Hotel hotel() {
        return hotel;
    }

    public URL url() {
        return url;
    }

    private static void checkArguments(String departure, String destination, LocalDate startDate, LocalDate endDate, Price price, Hotel hotel, URL url) {
        checkLocations(departure, destination);
        checkDates(startDate, endDate);
        checkPrice(price);
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

    private static void checkPrice(Price price) {
        if (price == null)
            throw new IllegalArgumentException("Price object could not be null");
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
        if (!price.equals(flightHotelBundle.price)) return false;
        if (!hotel.equals(flightHotelBundle.hotel)) return false;
        return url.equals(flightHotelBundle.url);
    }

    @Override
    public int hashCode() {
        int result = departure.hashCode();
        result = 31 * result + destination.hashCode();
        result = 31 * result + startDate.hashCode();
        result = 31 * result + endDate.hashCode();
        result = 31 * result + price.hashCode();
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
                ", price=" + price +
                ", hotel=" + hotel +
                ", url=" + url +
                '}';
    }
}
