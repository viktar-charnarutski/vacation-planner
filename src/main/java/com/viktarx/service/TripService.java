package com.viktarx.service;

import com.viktarx.agent.Trip;

import java.time.LocalDate;
import java.util.Set;

/**
 * Trip service's functionality description.
 */
public interface TripService {

    Set<Trip> trips(String departure, String destination, LocalDate startDate, LocalDate endDate);

}
