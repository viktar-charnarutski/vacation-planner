package com.viktarx.service;

import com.viktarx.agent.TripOption;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

/**
 *
 */
public final class TravelerService implements TripService {

    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MMM-dd");

    private final Request request;

    public TravelerService(Request request) {
        this.request = request;
    }

    @Override
    public Set<TripOption> tripOptions(String departure, String destination, LocalDate startDate, LocalDate endDate) {
        return tripOptionsFromJson(
                this.request.get(
                        travelerParams(departure, destination, startDate, endDate)
                )
        );
    }

    private static Set<TripOption> tripOptionsFromJson(String json) {
        // TODO introduce JSON - TripOption object conversion
        return new HashSet<>();
    }

    private static String travelerParams(String departure, String destination, LocalDate startDate, LocalDate endDate) {
        return "?departure=" + departure + "&destination=" + destination +
                "&start=" + formatter.format(startDate) +
                "&end=" + formatter.format(endDate);
    }
}
