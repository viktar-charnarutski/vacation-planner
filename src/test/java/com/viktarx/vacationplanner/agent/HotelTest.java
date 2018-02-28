package com.viktarx.vacationplanner.agent;

import org.junit.Test;

import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Tests for the Hotel class.
 */
public class HotelTest {

    private Hotel hotel;

    public HotelTest() throws MalformedURLException {
        hotel = new Hotel("San Souce", "A3, St-Mary, Jamaica", "All Inclusive", 4.0,
                4.8, new URL("http://couples.com/resorts/sans-souci"));
    }

    @Test
    public void name() {
        assertEquals("San Souce", hotel.name());
    }

    @Test
    public void address() {
        assertEquals("A3, St-Mary, Jamaica", hotel.address());
    }

    @Test
    public void type() {
        assertEquals("All Inclusive", hotel.type());
    }

    @Test
    public void stars() {
        assertTrue(4.0 == hotel.stars());
    }

    @Test
    public void rating() {
        assertTrue(4.8 == hotel.rating());
    }

    @Test
    public void url() throws Exception {
        assertEquals(new URL("http://couples.com/resorts/sans-souci"), hotel.url());
    }
}