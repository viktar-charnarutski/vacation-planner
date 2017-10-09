package com.viktarx.service;

import com.viktarx.agent.VacationTrip;

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
    public Set<VacationTrip> trips(String departure, String destination, LocalDate startDate, LocalDate endDate) {
        return tripsFromJson(
                this.request.get(
                        travelerParams(departure, destination, startDate, endDate)
                )
        );
    }

    private static Set<VacationTrip> tripsFromJson(String json) {
        // TODO introduce JSON - FlightHotelBundle object conversion
        return new HashSet<>();
    }

    private static String travelerParams(String departure, String destination, LocalDate startDate, LocalDate endDate) {
        return "/?departure=" + departure + "&destination=" + destination +
                "&start=" + formatter.format(startDate) +
                "&end=" + formatter.format(endDate);
    }
}
