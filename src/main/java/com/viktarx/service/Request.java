package com.viktarx.service;

/**
 * HTTP main request.
 */
public interface Request {

    String get(String params);

    String post(String params);

}
