package com.viktarx.agent;

import com.viktarx.service.TravelerRequest;
import com.viktarx.service.TravelerService;
import com.viktarx.service.TripService;
import org.junit.Test;

import java.time.LocalDate;
import java.util.Set;

import static org.junit.Assert.*;

/**
 *  Tests for the TravelAgentTest class.
 */
public class TravelAgentTest {
    @Test
    public void offerTripsForVacationPlan() throws Exception {
        VacationPlan vacationPlan = new VacationPlan(LocalDate.now().plusDays(5), LocalDate.now().plusDays(10), 2,
                "SFO", "MBJ");
        TripService service = new TravelerService(
                new TravelerRequest("https://private-89a542-vacation.apiary-mock.com")
        );
        Set<VacationTrip> trips = new TravelAgent(service).offerTripsForVacationPlan(vacationPlan);
        assertTrue(trips != null && !trips.isEmpty());
    }

}