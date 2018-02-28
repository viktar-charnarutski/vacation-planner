package com.viktarx.vacationplanner.trip;

import java.time.LocalDate;
import java.time.Period;

/**
 * The class represents a time duration with start and end dates.
 */
public class DateRange {
    private final LocalDate startDate;
    private final LocalDate endDate;

    public DateRange(LocalDate startDate, LocalDate endDate) {
        checkArguments(startDate, endDate);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    private void checkArguments(LocalDate startDate, LocalDate endDate) {
        checkDates(startDate, endDate);
    }

    private void checkDates(LocalDate startDate, LocalDate endDate) {
        if (endDate.isBefore(startDate) || startDate.equals(endDate))
            throw new IllegalArgumentException(String.format("The start date %s should be after the end date %s",
                    startDate, endDate));
    }

    public LocalDate startDate() {
        return startDate;
    }

    public LocalDate endDate() {
        return endDate;
    }

    public int duration() {
        return Period.between(startDate, endDate).getDays();
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

    @Override
    public String toString() {
        return "DateRange{" +
                "startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
