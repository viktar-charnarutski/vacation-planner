package com.viktarx.agent;

import com.viktarx.service.TripService;

import java.util.HashSet;
import java.util.Set;

/**
 * The class represents a functionality of checking available trip offers proposed by a certain agents.
 */
public class TravelAgent implements TravelResearch {

    private final TripService service;

    public TravelAgent(TripService service) {
        this.service = service;
    }

    public Set<VacationTrip> offerTripsForVacationPlan(VacationPlan vacationPlan) {
        Set<VacationTrip> flightHotelBundles = new HashSet<>();
        for (DateRange dateRange : vacationPlan.optionsForDateRanges()) {
            flightHotelBundles.addAll(
                    this.service.trips(
                            vacationPlan.departure(), vacationPlan.destination(), dateRange.startDate(), dateRange.endDate()
                    )
            );
        }
        return flightHotelBundles;
    }
}
