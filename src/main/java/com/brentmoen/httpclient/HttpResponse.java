package com.brentmoen.httpclient;

public interface HttpResponse {
    int status();

    String body();

    String header(String key);
}
