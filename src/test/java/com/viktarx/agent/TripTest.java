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

    public TripTest() throws MalformedURLException {
        hotel = new Hotel("San Souce", "A3, St-Mary, Jamaica", "All Inclusive", 4.0, 4.0,"http://couples.com/resorts/sans-souci");
        trip = new Trip("SFO", "MJB", LocalDate.now(), LocalDate.now().plusDays(5), 1099, hotel, new URL("https://www.example.com"));
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
        assertTrue(1099 == trip.priceInUsd());
    }

    @Test
    public void url() throws Exception {
        assertEquals(new URL("https://www.example.com"), trip.url());
    }
}