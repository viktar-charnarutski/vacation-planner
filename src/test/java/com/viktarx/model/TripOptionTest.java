package com.viktarx.model;

import org.junit.Test;

import java.net.URL;
import java.time.LocalDate;

import static org.junit.Assert.*;

/**
 * Tests for the TripOption class.
 */
public class TripOptionTest {

    @Test
    public void startDate() throws Exception {
        assertEquals(LocalDate.now(), new TripOption(LocalDate.now(), LocalDate.now().plusDays(5), 1099,
                new URL("https://www.example.com")).startDate());
    }

    @Test
    public void endDate() throws Exception {
        assertEquals(LocalDate.now().plusDays(5), new TripOption(LocalDate.now(), LocalDate.now().plusDays(5), 1099,
                new URL("https://www.example.com")).endDate());
    }

    @Test
    public void priceInDollars() throws Exception {
        assertTrue(1099 == new TripOption(LocalDate.now(), LocalDate.now().plusDays(5), 1099,
                new URL("https://www.example.com")).priceInDollars());
    }

    @Test
    public void url() throws Exception {
        assertEquals(new URL("https://www.example.com"), new TripOption(LocalDate.now(), LocalDate.now().plusDays(5), 1099,
                new URL("https://www.example.com")).url());
    }
}