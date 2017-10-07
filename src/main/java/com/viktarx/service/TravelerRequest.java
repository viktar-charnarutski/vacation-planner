package com.viktarx.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Http requests for GET and POST methods implementation for Traveler remote API.
 */
public final class TravelerRequest implements Request {

    private static String USER_AGENT = "Mozilla/5.0";

    private final String serviceUrl;

    public TravelerRequest(String serviceUrl) {
        this.serviceUrl = serviceUrl;
    }

    @Override
    public String get(String params) {
        HttpURLConnection connection;
        StringBuilder response;
        try {
            URL targetUrl = new URL(this.serviceUrl + "/trips" + params);
            connection = (HttpURLConnection) targetUrl.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("User-Agent", USER_AGENT);
            response = parsedResponse(connection);
        } catch (IOException e) {
            throw new IllegalStateException(String.format("Failed to communicate with %s due to: %s",
                    this.serviceUrl, e.getCause()));
        }
        System.out.println(response.toString());
        return response.toString();
    }

    @Override
    public String post(String params) {
        HttpURLConnection connection;
        StringBuilder response;
        try {
            URL obj = new URL(this.serviceUrl);
            connection = (HttpURLConnection) obj.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("User-Agent", USER_AGENT);

            connection.setDoOutput(true);
            OutputStream os = connection.getOutputStream();
            os.write(params.getBytes());
            os.flush();
            os.close();

            response = parsedResponse(connection);
        }  catch (IOException e) {
            throw new IllegalStateException(String.format("Failed to communicate with %s due to: %s",
                    this.serviceUrl, e.getCause()));
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
                    this.serviceUrl, responseCode));
        }
        return response;
    }
}
