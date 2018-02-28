package com.viktarx.vacationplanner.agent;

import java.time.LocalDate;
import java.time.Period;
import java.util.*;

/**
 * The class contains information about vacation's plan.
 */
public class VacationPlan {

    private final LocalDate possibleStartDate;
    private final LocalDate possibleEndDate;
    private final int minDurationInDays;
    private final String departure;
    private final String destination;

    private final Set<DateRange> optionsForDateRanges = new HashSet<>();

    public VacationPlan(LocalDate possibleStartDate, LocalDate possibleEndDate, int minDurationInDays, String departure,
                        String destination) {
        checkArguments(possibleStartDate, possibleEndDate, minDurationInDays, departure, destination);
        this.possibleStartDate = possibleStartDate;
        this.possibleEndDate = possibleEndDate;
        this.minDurationInDays = minDurationInDays;
        this.departure = departure;
        this.destination = destination;
    }

    public LocalDate possibleStartDate() {
        return possibleStartDate;
    }

    public LocalDate possibleEndDate() {
        return possibleEndDate;
    }

    public int minDurationInDays() {
        return minDurationInDays;
    }

    public String departure() {
        return departure;
    }

    public String destination() {
        return destination;
    }

    public final Set<DateRange> optionsForDateRanges() {
        if (optionsForDateRanges.size() == 0) calculateOptionsForDateRanges();
        return optionsForDateRanges;
    }

    private void calculateOptionsForDateRanges() {
        for (int firstDay = 0; firstDay <= maxPossibleDurationInDays(); firstDay++)
            for (int lastDay = firstDay + minDurationInDays; lastDay <= maxPossibleDurationInDays(); lastDay++)
                optionsForDateRanges.add(new DateRange(possibleStartDate.plusDays(firstDay), possibleStartDate.plusDays(lastDay)));
    }

    private int maxPossibleDurationInDays() {
        return Period.between(possibleStartDate, possibleEndDate).getDays();
    }

    private void checkArguments(LocalDate possibleStartDate, LocalDate possibleEndDate, int minLongevityInDays, String departureCity,
                                String destinationCity) {
        checkDates(possibleStartDate, possibleEndDate);
        checkMinDurationInDays(minLongevityInDays, possibleStartDate, possibleEndDate);
        checkLocations(departureCity, destinationCity);
    }

    private void checkLocations(String departure, String destination) {
        if (locationsAreTheSame(departure, destination))
            throw new IllegalArgumentException(String.format("Departure %s should be different from destinations %s",
                    departure, destination));
    }

    private boolean locationsAreTheSame(String departureCity, String destinationCity) {
        return departureCity.equals(destinationCity);
    }

    private void checkMinDurationInDays(int minDurationInDays, LocalDate possibleStartDate, LocalDate possibleEndDate) {
        if (!minDurationFitsDateRange(minDurationInDays, possibleStartDate, possibleEndDate))
            throw new IllegalArgumentException(String.format("Minimum duration in days %d should be less than end date %s and start date %s difference",
                    minDurationInDays, possibleStartDate, possibleEndDate));
    }

    private boolean minDurationFitsDateRange(int longevityInDays, LocalDate possibleStartDate, LocalDate possibleEndDate) {
        return Period.between(possibleStartDate, possibleEndDate).getDays() > longevityInDays;
    }

    private void checkDates(LocalDate possibleStartDate, LocalDate possibleEndDate) {
        if (possibleEndDate.isBefore(possibleStartDate) || possibleStartDate.equals(possibleEndDate))
            throw new IllegalArgumentException(String.format("The start date %s should be after the end date %s",
                    possibleStartDate, possibleEndDate));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VacationPlan)) return false;

        VacationPlan that = (VacationPlan) o;

        if (minDurationInDays != that.minDurationInDays) return false;
        if (possibleStartDate != null ? !possibleStartDate.equals(that.possibleStartDate) : that.possibleStartDate != null)
            return false;
        if (possibleEndDate != null ? !possibleEndDate.equals(that.possibleEndDate) : that.possibleEndDate != null)
            return false;
        if (departure != null ? !departure.equals(that.departure) : that.departure != null)
            return false;
        return destination != null ? destination.equals(that.destination) : that.destination == null;
    }

    @Override
    public int hashCode() {
        int result = possibleStartDate != null ? possibleStartDate.hashCode() : 0;
        result = 31 * result + (possibleEndDate != null ? possibleEndDate.hashCode() : 0);
        result = 31 * result + minDurationInDays;
        result = 31 * result + (departure != null ? departure.hashCode() : 0);
        result = 31 * result + (destination != null ? destination.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "VacationPlan{" +
                "possibleStartDate=" + possibleStartDate +
                ", possibleEndDate=" + possibleEndDate +
                ", minDurationInDays=" + minDurationInDays +
                ", departure='" + departure + '\'' +
                ", destination='" + destination + '\'' +
                '}';
    }
}
