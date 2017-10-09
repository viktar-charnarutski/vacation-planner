package com.viktarx.agent;

import java.time.LocalDate;

/**
 * Functional structure for vacation objects.
 */
public interface VacationTrip {

    String departure();

    String destination();

    LocalDate startDate();

    LocalDate endDate();

    Price price();

}
