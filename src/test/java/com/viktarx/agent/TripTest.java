package com.viktarx.agent;

import org.junit.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;

import static org.junit.Assert.*;

/**
 * Tests for the Trip class.
 */
public class TripTest {

    private Hotel hotel;
    private Trip trip;
    private Price price;

    public TripTest() throws MalformedURLException {
        hotel = new Hotel("San Souce", "A3, St-Mary, Jamaica", "All Inclusive", 4.0, 4.8, new URL("http://couples.com/resorts/sans-souci"));
        price = new Price(1099.0, 999.9);
        trip = new Trip("SFO", "MJB", LocalDate.now(), LocalDate.now().plusDays(5), price, hotel, new URL("https://www.example.com"));
    }

    @Test
    public void departure() throws Exception {
        assertEquals("SFO", trip.departure());
    }

    @Test
    public void destination() throws Exception {
        assertEquals("MJB", trip.destination());
    }

    @Test
    public void startDate() throws Exception {
        assertEquals(LocalDate.now(), trip.startDate());
    }

    @Test
    public void endDate() throws Exception {
        assertEquals(LocalDate.now().plusDays(5), trip.endDate());
    }

    @Test
    public void priceInUsd() throws Exception {
        assertTrue(999.9 == trip.price().discountPriceInUsd());
    }

    @Test
    public void hotel() throws Exception {
        assertEquals(hotel, trip.hotel());
    }

    @Test
    public void url() throws Exception {
        assertEquals(new URL("https://www.example.com"), trip.url());
    }
}