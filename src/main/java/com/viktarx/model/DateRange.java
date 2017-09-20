package com.viktarx.model;

import java.time.LocalDate;
import java.time.Period;

/**
 *
 */
public class DateRange {
    private final LocalDate startDate;
    private final LocalDate endDate;

    public DateRange(LocalDate startDate, LocalDate endDate) {
        checkArguments(startDate, endDate);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    private static void checkArguments(LocalDate startDate, LocalDate endDate) {
        checkDates(startDate, endDate);
    }

    public LocalDate startDate() {
        return startDate;
    }

    public LocalDate endDate() {
        return endDate;
    }

    public int duration() {
        return Period.between(endDate, startDate).getDays();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DateRange)) return false;

        DateRange dateRange = (DateRange) o;

        if (!startDate.equals(dateRange.startDate)) return false;
        return endDate.equals(dateRange.endDate);
    }

    @Override
    public int hashCode() {
        int result = startDate.hashCode();
        result = 31 * result + endDate.hashCode();
        return result;
    }

    private static void checkDates(LocalDate startDate, LocalDate endDate) {
        if (startDate.isBefore(endDate) || startDate.equals(endDate))
            throw new IllegalArgumentException(String.format("The start date %s should be after the end date %s",
                    startDate, endDate));
    }
}
