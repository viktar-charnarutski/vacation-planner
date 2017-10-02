package com.viktarx.service;

import com.viktarx.agent.TripOption;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Set;

/**
 * Structure implementation of a crawling service.
 */
abstract class CrawlTripService implements TripService {

    @Override
    public Set<TripOption> tripOptions(String departureCity, String destinationCity, LocalDate startDate, LocalDate endDate) {
        return parsedTripOptionsFromRawResponse(rawDataFor(departureCity, destinationCity, startDate, endDate));
    }

    abstract String rawDataFor(String departureCity, String destinationCity, LocalDate startDate, LocalDate endDate);

    abstract Set<TripOption> parsedTripOptionsFromRawResponse(String response);

    abstract String serviceUrl();

    abstract String paramsForGet (String departureCity, String destinationCity, LocalDate startDate, LocalDate endDate);

    String responseForGetWithParams(String urlParameters) {
        HttpURLConnection connection;
        StringBuilder response;
        try {
            URL targetUrl = new URL(serviceUrl() + urlParameters);
            connection = (HttpURLConnection) targetUrl.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("User-Agent", userAgent());
            response = parsedResponse(connection);
        } catch (IOException e) {
            throw new IllegalStateException(String.format("Failed to communicate with %s due to: %s",
                    serviceUrl(), e.getCause()));
        }
        return response.toString();
    }

    String responseForPostWithParams(String urlParameters) {
        HttpURLConnection connection;
        StringBuilder response;
        try {
            URL obj = new URL(serviceUrl());
            connection = (HttpURLConnection) obj.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("User-Agent", userAgent());

            connection.setDoOutput(true);
            OutputStream os = connection.getOutputStream();
            os.write(urlParameters.getBytes());
            os.flush();
            os.close();

            response = parsedResponse(connection);
        }  catch (IOException e) {
            throw new IllegalStateException(String.format("Failed to communicate with %s due to: %s",
                    serviceUrl(), e.getCause()));
        }
        return response.toString();
    }

    private StringBuilder parsedResponse(HttpURLConnection connection) throws IOException {
        StringBuilder response = new StringBuilder();
        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
        } else {
            throw new IllegalStateException(String.format("Failed to get response from %s. Response code: %d",
                    serviceUrl(), responseCode));
        }
        return response;
    }

    DateTimeFormatter searchDateFormatter() {
        return DateTimeFormatter.ofPattern("uuuu-MMM-dd");
    }

    String paramsForPost(String... params) {
        StringBuilder mergedParams = new StringBuilder();
        if (params.length > 0) {
            mergedParams = new StringBuilder(params[0]);
            for (int i = 1; i < params.length; i++) mergedParams.append("&").append(params[i]);
        }
        return mergedParams.toString();
    }

    String userAgent() {
        return "Mozilla/5.0";
    }
}