package com.viktarx.model;

import java.util.Set;

/**
 * Trip search functionality description.
 */
public interface TravelResearch {

    Set<TripOption> offerTripOptionsForVacationPlan(VacationPlan vacationPlan);

}
