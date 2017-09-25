package com.viktarx.service;

import com.viktarx.agent.TripOption;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.util.Set;

/**
 * Structure implementation of a crawling service.
 */
public abstract class CrawlTripService implements TripService {

    @Override
    public Set<TripOption> tripOptions(String departureCity, String destinationCity, LocalDate startDate, LocalDate endDate) {
        return parsedTripOptionsFromWebPageContent(responseForGet(urlParameters(departureCity, destinationCity, startDate, endDate)));
    }

    abstract String urlParameters(String departureCity, String destinationCity, LocalDate startDate, LocalDate endDate);

    abstract Set<TripOption> parsedTripOptionsFromWebPageContent(String webPageContent);

    abstract String serviceUrl();

    private String responseForGet(String urlParameters) {
        HttpURLConnection con;
        StringBuilder response = new StringBuilder();
        try {
            URL targetUrl = new URL(serviceUrl() + urlParameters);
            con = (HttpURLConnection) targetUrl.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("User-Agent", "Mozilla/5.0");
            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String inputLine;

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
            } else {
                throw new IllegalStateException(String.format("Failed to get response from %s. Response code: %d",
                        serviceUrl(), responseCode));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response.toString();
    }
}