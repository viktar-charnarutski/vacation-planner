package com.viktarx.service;

import com.viktarx.agent.TripOption;

import java.time.LocalDate;
import java.util.Set;

/**
 * Trip service's functionality description.
 */
interface TripService {

    Set<TripOption> tripOptions(String departure, String destination, LocalDate startDate, LocalDate endDate);

}
