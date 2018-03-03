package com.viktarx.vacationplanner.trip;

import org.junit.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;

import static org.junit.Assert.*;

/**
 * Tests for the FlightHotelBundle class.
 */
public class FlightHotelBundleTest {

    private Hotel hotel;
    private FlightHotelBundle flightHotelBundle;
    private TripPrice tripPrice;

    public FlightHotelBundleTest() throws MalformedURLException {
        hotel = new Hotel("San Souce", "A3, St-Mary, Jamaica", "All Inclusive", 4.0, 4.8, new URL("http://couples.com/resorts/sans-souci"));
        tripPrice = new TripPrice(1099.0, 999.9);
        flightHotelBundle = new FlightHotelBundle("SFO", "MJB", LocalDate.now(), LocalDate.now().plusDays(5), tripPrice, hotel, new URL("https://www.example.com"));
    }

    @Test
    public void departure() {
        assertEquals("SFO", flightHotelBundle.departure());
    }

    @Test
    public void destination() {
        assertEquals("MJB", flightHotelBundle.destination());
    }

    @Test
    public void startDate() {
        assertEquals(LocalDate.now(), flightHotelBundle.startDate());
    }

    @Test
    public void endDate() {
        assertEquals(LocalDate.now().plusDays(5), flightHotelBundle.endDate());
    }

    @Test
    public void priceInUsd() {
        assertTrue(999.9 == flightHotelBundle.price().discountPriceInUsd());
    }

    @Test
    public void hotel() {
        assertEquals(hotel, flightHotelBundle.hotel());
    }

    @Test
    public void url() throws Exception {
        assertEquals(new URL("https://www.example.com"), flightHotelBundle.url());
    }
}