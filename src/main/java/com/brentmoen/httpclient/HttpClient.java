package com.brentmoen.httpclient;

public interface HttpClient {
    HttpClient setBaseUrl(String baseUrl);

    HttpRequest request();
}
