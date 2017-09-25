package com.viktarx.agent;

import org.junit.Test;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.Assert.*;

/**
 * Tests for the DateRange class.
 */
public class DateRangeTest {

    private DateRange validDateRange = new DateRange(LocalDate.of(2017, Month.SEPTEMBER, 20),
            LocalDate.of(2017, Month.SEPTEMBER, 25));

    @Test
    public void startDate() throws Exception {
        assertEquals(LocalDate.of(2017, Month.SEPTEMBER, 20), validDateRange.startDate());
    }

    @Test
    public void endDate() throws Exception {
        assertEquals(LocalDate.of(2017, Month.SEPTEMBER, 25), validDateRange.endDate());
    }

    @Test
    public void duration() throws Exception {
        assertEquals(5, validDateRange.duration());
    }

    @Test
    public void startDateIsBeforeEndDate() throws Exception {
        boolean exceptionIsThrown = false;
        try {
            new DateRange(LocalDate.of(2017, Month.SEPTEMBER, 20),
                    LocalDate.of(2017, Month.AUGUST, 25));
        } catch (IllegalArgumentException e) {
            exceptionIsThrown = true;
        }
        assertTrue(exceptionIsThrown);
    }

    @Test
    public void startDateIsEqualToEndDate() throws Exception {
        boolean exceptionIsThrown = false;
        try {
            new DateRange(LocalDate.of(2017, Month.SEPTEMBER, 20),
                    LocalDate.of(2017, Month.SEPTEMBER, 20));
        } catch (IllegalArgumentException e) {
            exceptionIsThrown = true;
        }
        assertTrue(exceptionIsThrown);
    }
}