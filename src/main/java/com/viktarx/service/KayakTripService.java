package com.viktarx.service;

import com.viktarx.agent.TripOption;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Crawling service implementation for the Kayak provider.
 */
public class KayakTripService extends CrawlTripService {

    @Override
    String serviceUrl() {
        return "https://www.kayak.com";
    }

    @Override
    String rawDataFor(String departureCity, String destinationCity, LocalDate startDate, LocalDate endDate) {
        String initResponse = responseForGetWithParams(
                paramsForGet(departureCity, destinationCity, startDate.format(searchDateFormatter()), endDate.format(searchDateFormatter())));
        String urlParameters = parsedSearchIdFromRawPageContext(initResponse).orElse("");
        return responseForPostWithParams(paramsForPost(urlParameters));
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
    String paramsForGet(String... params) {
        return "/packages/Jamaica-U119/2017-10-10/2017-10-17/-1,-1/2/0,0,0/SFO";
    }

    @Override
    Set<TripOption> parsedTripOptionsFromRawResponse(String response) {
        System.out.println(response);
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


}
