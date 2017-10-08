package com.viktarx.agent;

import org.junit.Test;

import java.net.URL;
import java.time.LocalDate;

import static org.junit.Assert.*;

/**
 * Tests for the Trip class.
 */
public class TripTest {

    @Test
    public void startDate() throws Exception {
        assertEquals(LocalDate.now(), new Trip("SFO", "MJB", LocalDate.now(), LocalDate.now().plusDays(5), 1099,
                new URL("https://www.example.com")).startDate());
    }

    @Test
    public void endDate() throws Exception {
        assertEquals(LocalDate.now().plusDays(5), new Trip("SFO", "MJB", LocalDate.now(), LocalDate.now().plusDays(5), 1099,
                new URL("https://www.example.com")).endDate());
    }

    @Test
    public void priceInUsd() throws Exception {
        assertTrue(1099 == new Trip("SFO", "MJB", LocalDate.now(), LocalDate.now().plusDays(5), 1099,
                new URL("https://www.example.com")).priceInUsd());
    }

    @Test
    public void url() throws Exception {
        assertEquals(new URL("https://www.example.com"), new Trip("SFO", "MJB", LocalDate.now(), LocalDate.now().plusDays(5), 1099,
                new URL("https://www.example.com")).url());
    }
}