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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MMM-dd");
        String initResponse = responseForGetWithParams(paramsForGet(departureCity, destinationCity, startDate.format(formatter), endDate.format(formatter)));
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
    String paramsForPost(String... params) {
        return params[0] + "&poll=true&pollNumber=0&applyFilters=true&filterState=&useViewStateFilterState=true&pageNumber=1&append=false&sortMode=rank&ascending=true&priceType=totaltaxes&requestReason=POLL&isSecondPhase=false&textAdPageLocations=bottom%2Cright&displayAdPageLocations=upper-right%2Cright&existingAds=false&ajaxts=1506481172251&scriptsMetadata=6D1f1E5CmDIIQQ2I1r1IDNHvgDwVR1uUFX1o1I1MxHuQj1%26QBBDOMa33I1C27B19B23oCSc1Pw%3D%3D&stylesMetadata=10gOG34QI1I14BQ10H1*e%26MDB1DgTgQUO%25QD1Cg1YEI1g1t1gMEBQBUN75C35ZILw2I1O64g1%3D%3D";
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
