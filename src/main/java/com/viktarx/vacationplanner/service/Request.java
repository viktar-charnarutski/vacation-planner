package com.viktarx.vacationplanner.service;

/**
 * HTTP main request.
 */
public interface Request {

    String get(String params);

    String post(String params);

}
