package com.viktarx.vacationplanner.trip;

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
    public void possibleStartDate() {
        assertEquals(LocalDate.now().plusDays(5), new VacationPlan(LocalDate.now().plusDays(5), LocalDate.now().plusDays(10), 2,
                "SFO", "MBJ").possibleStartDate());
    }

    @Test
    public void possibleEndDate() {
        assertEquals(LocalDate.now().plusDays(10), new VacationPlan(LocalDate.now().plusDays(5), LocalDate.now().plusDays(10), 2,
                "SFO", "MBJ").possibleEndDate());
    }

    @Test
    public void minDurationInDays() {
        assertTrue(2 == new VacationPlan(LocalDate.now().plusDays(5), LocalDate.now().plusDays(10), 2,
                "SFO", "MBJ").minDurationInDays());
    }

    @Test
    public void departureCity() {
        assertEquals("SFO", new VacationPlan(LocalDate.now().plusDays(5), LocalDate.now().plusDays(10), 2,
                "SFO", "MBJ").departure());
    }

    @Test
    public void destinationCity() {
        assertEquals("MBJ", new VacationPlan(LocalDate.now().plusDays(5), LocalDate.now().plusDays(10), 2,
                "SFO", "MBJ").destination());
    }

    @Test
    public void optionsForDateRanges() {
        VacationPlan vacationPlan = new VacationPlan(LocalDate.now().plusDays(5), LocalDate.now().plusDays(10), 2,
                "SFO", "MBJ");

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