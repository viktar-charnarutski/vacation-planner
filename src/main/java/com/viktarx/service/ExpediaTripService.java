package com.viktarx.service;

import com.viktarx.agent.TripOption;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * Crawling service implementation for the Expedia provider.
 */
public class ExpediaTripService extends CrawlTripService {

    @Override
    String urlParameters(String departureCity, String destinationCity, LocalDate startDate, LocalDate endDate) {
        return "/Hotel-Search?packageType=fh&searchProduct=hotel&c=72efb192-ab66-4016-b191-ac4483269a61&adults=2&destination=Boston+(and+vicinity),+Massachusetts,+United+States+of+America&ttla=BOS&startDate=11/23/2017&endDate=11/26/2017&sort=recommended";
    }

    @Override
    Set<TripOption> parsedTripOptionsFromWebPageContent(String webPageContent) {
        Set<TripOption> tripOptions = new HashSet<>();
        try {
            TripOption tripOption = new TripOption(LocalDate.now(), LocalDate.now().plusDays(5), 1099,
                    new URL("https://www.example.com"));
            tripOptions.add(tripOption);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return tripOptions;
    }

    @Override
    String serviceUrl() {
        return "https://www.expedia.com";
    }
}
