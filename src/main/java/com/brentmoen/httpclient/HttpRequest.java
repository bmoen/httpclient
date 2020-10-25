package com.brentmoen.httpclient;

public interface HttpRequest {
    HttpRequest method(HttpMethod method);

    HttpRequest path(String path);

    HttpRequest header(String key, String value);

    HttpRequest query(String key, String value);

    HttpRequest body(String body);

    HttpResponse send();
}
