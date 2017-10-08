package com.viktarx.service;

import com.viktarx.agent.Trip;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Crawling service implementation for the Kayak provider.
 */
class KayakTripService extends CrawlTripService {

    @Override
    String serviceUrl() {
        return "https://www.kayak.com";
    }

    @Override
    String postUri() {
        return "/s/horizon/packages/results/PackageSearchPoll";
    }

    @Override
    String rawDataFor(String departureCity, String destinationCity, LocalDate startDate, LocalDate endDate) {
        String kayakCustomParamsForGet = paramsForGet(departureCity, destinationCity, startDate, endDate);
        String initResponse = responseForGetWithParams(kayakCustomParamsForGet);

        String searchIdParam = parsedSearchIdFromRawPageContext(initResponse).orElse("");
        return responseForPostWithParams(paramsForPost(searchIdParam));
    }

    // TODO make sure that passed departure / destination values are unified, e.g. Jamaica-U119, SFO
    @Override
    String paramsForGet(String departureCity, String destinationCity, LocalDate startDate, LocalDate endDate) {
        return "/packages/" +
                destinationCity +
                "/" +
                startDate.format(searchDateFormatter()) +
                "/" +
                endDate.format(searchDateFormatter()) +
                "/-1,-1/2/0,0,0/" +
                departureCity;
    }

    @Override
    String paramsForPost(String... params) {
        return params[0] + "&poll=true&pollNumber=1&applyFilters=true&filterState=&useViewStateFilterState=true&pageNumber=1&append=false&sortMode=rank&ascending=true&priceType=totaltaxes&lastPollTotalCount=641&requestReason=POLL&isSecondPhase=false&ajaxts=1507086724124&scriptsMetadata=6D1fQE5U1BDDiE3Be1QBv%3Bs1fCGYB*QZdCQ1i1D1f*SI1hwMEMYxr34BQ26C21EE19I1B1FTUBe1H&stylesMetadata=10gPG35EC1C13C1V10D%26cvYGBwg1gDsJKHboBgBQ1MCE3Xg1CD1%26QqGg75C1g26Dg1w3I1MoG%262E1H64Q1%3D%3D";
    }

    Optional<String> parsedSearchIdFromRawPageContext(String rawPageContent) {
        Optional<String> result = Optional.empty();
        Pattern pattern = Pattern.compile("(\\{\"searchId\":\"+)(\\w+)(\"})");
        Matcher matcher = pattern.matcher(rawPageContent);
        if (matcher.find()) {
            result = Optional.of("searchId=" + matcher.group(2));
        }
        return result;
    }

    @Override
    Set<Trip> parsedTripsFromRawResponse(String response) {
        Set<Trip> trips = new HashSet<>();
        try {
            Trip trip = new Trip(LocalDate.now(), LocalDate.now().plusDays(5), 1099,
                    new URL("https://www.example.com"));
            trips.add(trip);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return trips;
    }

}
