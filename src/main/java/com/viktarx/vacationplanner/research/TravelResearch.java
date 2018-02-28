package com.viktarx.vacationplanner.research;

import com.viktarx.vacationplanner.trip.VacationPlan;
import com.viktarx.vacationplanner.trip.VacationTrip;

import java.util.Set;

/**
 * FlightHotelBundle search functionality description.
 */
public interface TravelResearch {

    Set<VacationTrip> offerTripsForVacationPlan(VacationPlan vacationPlan);

}
