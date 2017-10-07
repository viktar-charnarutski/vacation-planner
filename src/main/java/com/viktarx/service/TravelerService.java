package com.viktarx.service;

import com.viktarx.agent.TripOption;

import java.time.LocalDate;
import java.util.Set;

/**
 *
 */
public final class TravelerService implements TripService {

    private final Request request;

    public TravelerService(Request request) {
        this.request = request;
    }

    @Override
    public Set<TripOption> tripOptions(String departureCity, String destinationCity, LocalDate startDate, LocalDate endDate) {
        return tripOptionsFromJson(
                this.request.get(
                        travelerParams(departureCity, destinationCity, startDate, endDate)
                )
        );
    }

    private static Set<TripOption> tripOptionsFromJson(String json) {
        return null;
    }

    private static String travelerParams(String departureCity, String destinationCity, LocalDate startDate, LocalDate endDate) {
        return null;
    }
}
