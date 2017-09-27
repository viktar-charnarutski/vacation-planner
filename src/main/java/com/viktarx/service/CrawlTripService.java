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
        return parsedTripOptionsFromRawResponse(rawDataFor(departureCity, destinationCity, startDate, endDate));
    }

    abstract String rawDataFor(String departureCity, String destinationCity, LocalDate startDate, LocalDate endDate);

    abstract String paramsForGet(String... params);

    abstract String paramsForPost(String... params);

    abstract Set<TripOption> parsedTripOptionsFromRawResponse(String response);

    abstract String serviceUrl();

    String responseForGetWithParams(String urlParameters) {
        HttpURLConnection connection;
        StringBuilder response = new StringBuilder();
        try {
            URL targetUrl = new URL(serviceUrl() + urlParameters);
            connection = (HttpURLConnection) targetUrl.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("User-Agent", "Mozilla/5.0");
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                while ((inputLine = bufferedReader.readLine()) != null) {
                    response.append(inputLine);
                }
                bufferedReader.close();
            } else {
                throw new IllegalStateException(String.format("Failed to get response from %s. Response code: %d",
                        serviceUrl(), responseCode));
            }
        } catch (IOException e) {
            throw new IllegalStateException(String.format("Failed to communicate with %s due to: %s",
                    serviceUrl(), e.getCause()));
        }
        return response.toString();
    }

    // TODO adjust post call
    String responseForPostWithParams(String urlParameters) {
        HttpURLConnection connection;
        StringBuilder response = new StringBuilder();
        try {
            URL targetUrl = new URL(serviceUrl());
            connection = (HttpURLConnection) targetUrl.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("User-Agent", "Mozilla/5.0");
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                while ((inputLine = bufferedReader.readLine()) != null) {
                    response.append(inputLine);
                }
                bufferedReader.close();
            } else {
                throw new IllegalStateException(String.format("Failed to get response from %s. Response code: %d",
                        serviceUrl(), responseCode));
            }
        } catch (IOException e) {
            throw new IllegalStateException(String.format("Failed to communicate with %s due to: %s",
                    serviceUrl(), e.getCause()));
        }
        return response.toString();
    }
}