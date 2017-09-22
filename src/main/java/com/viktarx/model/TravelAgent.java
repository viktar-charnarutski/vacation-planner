package com.viktarx.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 *
 */
public class TravelAgent implements TravelResearch {


    public Set<TripOption> offerTripOptionsForVacationPlan(VacationPlan vacationPlan) {
        Set<TripOption> tripOptions = new HashSet<>();
        for (DateRange dateRange : vacationPlan.optionsForDateRanges()) {
            tripOptions.addAll(optionsAvailableForDates(vacationPlan.departureCity(), vacationPlan.destinationCity(),
                    dateRange.startDate(), dateRange.endDate()));
        }
        return tripOptions;
    }

    private Set<TripOption> optionsAvailableForDates(String departureCity, String destinationCity, LocalDate startDate, LocalDate endDate) {
        // TODO call third party service
        return null;
    }
}
