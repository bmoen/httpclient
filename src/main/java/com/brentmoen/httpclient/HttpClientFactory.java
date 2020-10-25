package com.brentmoen.httpclient;

public interface HttpClientFactory {
    static HttpClientFactory instance = new DefaultHttpClientFactory();

    static HttpClientFactory getInstance() {
        return instance;
    }

    HttpClient create();
}
