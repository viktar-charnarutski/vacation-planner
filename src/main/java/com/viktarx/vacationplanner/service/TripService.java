package com.viktarx.vacationplanner.service;

import com.viktarx.vacationplanner.trip.VacationTrip;

import java.time.LocalDate;
import java.util.Set;

/**
 * FlightHotelBundle service's functionality description.
 */
public interface TripService {

    Set<VacationTrip> trips(String departure, String destination, LocalDate startDate, LocalDate endDate);

}
