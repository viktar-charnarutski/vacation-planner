package com.viktarx.model;

import org.junit.Test;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

/**
 *  Tests for the TravelAgentTest class.
 */
public class TravelAgentTest {
    @Test
    public void offerTripOptionsForVacationPlan() throws Exception {
        VacationPlan vacationPlan = new VacationPlan(LocalDate.now().plusDays(5), LocalDate.now().plusDays(10), 2,
                "San Francisco, CA", "New York, NY");
        Set<TripOption> tripOptions = new TravelAgent().offerTripOptionsForVacationPlan(vacationPlan);
        assertTrue(tripOptions != null && !tripOptions.isEmpty());
    }

}