package com.viktarx.agent;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * The class represents a functionality of checking available trip offers proposed by a certain agents.
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

    // TODO: a service implementation is pending
    private Set<TripOption> optionsAvailableForDates(String departureCity, String destinationCity, LocalDate startDate, LocalDate endDate) {
        Set<TripOption> tripOptions = new HashSet<>();
        try {
            tripOptions.add(new TripOption(LocalDate.now(), LocalDate.now().plusDays(5), 1099,
                    new URL("https://www.example.com")));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return tripOptions;
    }
}
