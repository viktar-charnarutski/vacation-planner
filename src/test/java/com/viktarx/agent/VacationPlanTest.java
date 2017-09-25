package com.viktarx.agent;

import org.junit.Test;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * Tests for the VacationPlan class.
 */
public class VacationPlanTest {
    @Test
    public void possibleStartDate() throws Exception {
        assertEquals(LocalDate.now().plusDays(5), new VacationPlan(LocalDate.now().plusDays(5), LocalDate.now().plusDays(10), 2,
                "San Francisco, CA", "New York, NY").possibleStartDate());
    }

    @Test
    public void possibleEndDate() throws Exception {
        assertEquals(LocalDate.now().plusDays(10), new VacationPlan(LocalDate.now().plusDays(5), LocalDate.now().plusDays(10), 2,
                "San Francisco, CA", "New York, NY").possibleEndDate());
    }

    @Test
    public void minDurationInDays() throws Exception {
        assertTrue(2 == new VacationPlan(LocalDate.now().plusDays(5), LocalDate.now().plusDays(10), 2,
                "San Francisco, CA", "New York, NY").minDurationInDays());
    }

    @Test
    public void departureCity() throws Exception {
        assertEquals("San Francisco, CA", new VacationPlan(LocalDate.now().plusDays(5), LocalDate.now().plusDays(10), 2,
                "San Francisco, CA", "New York, NY").departureCity());
    }

    @Test
    public void destinationCity() throws Exception {
        assertEquals("New York, NY", new VacationPlan(LocalDate.now().plusDays(5), LocalDate.now().plusDays(10), 2,
                "San Francisco, CA", "New York, NY").destinationCity());
    }

    @Test
    public void optionsForDateRanges() throws Exception {
        VacationPlan vacationPlan = new VacationPlan(LocalDate.now().plusDays(5), LocalDate.now().plusDays(10), 2,
                "San Francisco, CA", "New York, NY");

        Set<DateRange> optionsForDateRanges = new HashSet<>();
        optionsForDateRanges.add(new DateRange(LocalDate.now().plusDays(5), LocalDate.now().plusDays(7)));
        optionsForDateRanges.add(new DateRange(LocalDate.now().plusDays(5), LocalDate.now().plusDays(8)));
        optionsForDateRanges.add(new DateRange(LocalDate.now().plusDays(5), LocalDate.now().plusDays(9)));
        optionsForDateRanges.add(new DateRange(LocalDate.now().plusDays(5), LocalDate.now().plusDays(10)));

        optionsForDateRanges.add(new DateRange(LocalDate.now().plusDays(6), LocalDate.now().plusDays(8)));
        optionsForDateRanges.add(new DateRange(LocalDate.now().plusDays(6), LocalDate.now().plusDays(9)));
        optionsForDateRanges.add(new DateRange(LocalDate.now().plusDays(6), LocalDate.now().plusDays(10)));

        optionsForDateRanges.add(new DateRange(LocalDate.now().plusDays(7), LocalDate.now().plusDays(9)));
        optionsForDateRanges.add(new DateRange(LocalDate.now().plusDays(7), LocalDate.now().plusDays(10)));

        optionsForDateRanges.add(new DateRange(LocalDate.now().plusDays(8), LocalDate.now().plusDays(10)));

        assertEquals(optionsForDateRanges, vacationPlan.optionsForDateRanges());
    }

}