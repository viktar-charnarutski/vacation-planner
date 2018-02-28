package com.viktarx.vacationplanner.agent;

import java.util.Set;

/**
 * FlightHotelBundle search functionality description.
 */
public interface TravelResearch {

    Set<VacationTrip> offerTripsForVacationPlan(VacationPlan vacationPlan);

}
