package com.viktarx.service;

import com.viktarx.agent.TripOption;

import java.time.LocalDate;
import java.util.Set;

/**
 * Structure implementation of a crawling service.
 */
public abstract class CrawlTripService implements TripService {
    @Override
    public Set<TripOption> tripOptions(String departureCity, String destinationCity, LocalDate startDate, LocalDate endDate) {
        return parsedTripOptionsFromWebPageContent(webPageContent(departureCity, destinationCity, startDate, endDate));
    }

    abstract String webPageContent(String departureCity, String destinationCity, LocalDate startDate, LocalDate endDate);

    abstract Set<TripOption> parsedTripOptionsFromWebPageContent(String webPageContent);

}