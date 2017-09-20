package com.viktarx.model;

import java.time.LocalDate;
import java.time.Period;
import java.util.*;

/**
 *
 */
public class VacationPlan {

    private final LocalDate possibleStartDate;
    private final LocalDate possibleEndDate;
    private final int minDurationInDays;
    private final String departureCity;
    private final String destinationCity;

    private Set<DateRange> dateRanges = new HashSet<>();

    public VacationPlan(LocalDate possibleStartDate, LocalDate possibleEndDate, int minDurationInDays, String departureCity,
                        String destinationCity) {

        checkArguments(possibleStartDate, possibleEndDate, minDurationInDays, departureCity, destinationCity);

        this.possibleStartDate = possibleStartDate;
        this.possibleEndDate = possibleEndDate;
        this.minDurationInDays = minDurationInDays;
        this.departureCity = departureCity;
        this.destinationCity = destinationCity;
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

    public String departureCity() {
        return departureCity;
    }

    public String destinationCity() {
        return destinationCity;
    }

    public Set<DateRange> dateRanges() {
        if (dateRanges.size() == 0) calculateDateRanges();
        return dateRanges;
    }

    private void calculateDateRanges() {
        populateStartDates();
        populateEndDates();
    }

    private void populateStartDates() {
        for (int i = 0; i <= (maxDurationInDays() - minDurationInDays); i++) {
            dateRanges.add(new DateRange(possibleStartDate.plusDays(i), possibleEndDate));
        }
    }

    private void populateEndDates() {
        for (int i = minDurationInDays; i <= maxDurationInDays(); i++) {
            dateRanges.add(new DateRange(possibleStartDate, possibleStartDate.plusDays(i)));
        }
    }

    private int maxDurationInDays() {
        return Period.between(possibleEndDate, possibleStartDate).getDays();
    }

    private void checkArguments(LocalDate possibleStartDate, LocalDate possibleEndDate, int minLongevityInDays, String departureCity,
                                String destinationCity) {
        checkDates(possibleStartDate, possibleEndDate);
        checkMinDurationInDays(minLongevityInDays, possibleStartDate, possibleEndDate);
        checkLocations(departureCity, destinationCity);
    }

    private void checkLocations(String departureCity, String destinationCity) {
        if (locationsAreTheSame(departureCity, destinationCity))
            throw new IllegalArgumentException(String.format("Departure city %s should be different from destination city %s",
                    departureCity, destinationCity));
    }

    private boolean locationsAreTheSame(String departureCity, String destinationCity) {
        return departureCity.equals(destinationCity);
    }

    private void checkMinDurationInDays(int minDurationInDays, LocalDate possibleStartDate, LocalDate possibleEndDate) {
        if (!minDurationFitsDateRange(minDurationInDays, possibleStartDate, possibleEndDate))
            throw new IllegalArgumentException(String.format("Minimum duration in days %d should be less than end date %s and start date %s difference",
                    minDurationInDays, possibleEndDate, possibleStartDate));
    }

    private boolean minDurationFitsDateRange(int longevityInDays, LocalDate possibleStartDate, LocalDate possibleEndDate) {
        return Period.between(possibleEndDate, possibleStartDate).getDays() > longevityInDays;
    }

    private void checkDates(LocalDate possibleStartDate, LocalDate possibleEndDate) {
        if (possibleStartDate.isBefore(possibleEndDate) || possibleStartDate.equals(possibleEndDate))
            throw new IllegalArgumentException(String.format("The start date %s should be after the end date %s",
                    possibleStartDate, possibleEndDate));
    }
}
