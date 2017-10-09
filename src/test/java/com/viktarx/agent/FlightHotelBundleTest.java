package com.viktarx.agent;

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
    private Price price;

    public FlightHotelBundleTest() throws MalformedURLException {
        hotel = new Hotel("San Souce", "A3, St-Mary, Jamaica", "All Inclusive", 4.0, 4.8, new URL("http://couples.com/resorts/sans-souci"));
        price = new Price(1099.0, 999.9);
        flightHotelBundle = new FlightHotelBundle("SFO", "MJB", LocalDate.now(), LocalDate.now().plusDays(5), price, hotel, new URL("https://www.example.com"));
    }

    @Test
    public void departure() throws Exception {
        assertEquals("SFO", flightHotelBundle.departure());
    }

    @Test
    public void destination() throws Exception {
        assertEquals("MJB", flightHotelBundle.destination());
    }

    @Test
    public void startDate() throws Exception {
        assertEquals(LocalDate.now(), flightHotelBundle.startDate());
    }

    @Test
    public void endDate() throws Exception {
        assertEquals(LocalDate.now().plusDays(5), flightHotelBundle.endDate());
    }

    @Test
    public void priceInUsd() throws Exception {
        assertTrue(999.9 == flightHotelBundle.price().discountPriceInUsd());
    }

    @Test
    public void hotel() throws Exception {
        assertEquals(hotel, flightHotelBundle.hotel());
    }

    @Test
    public void url() throws Exception {
        assertEquals(new URL("https://www.example.com"), flightHotelBundle.url());
    }
}