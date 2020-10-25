package com.brentmoen.httpclient;

import org.apache.commons.lang3.StringUtils;

import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;

class DefaultHttpRequest implements HttpRequest {
    private final java.net.http.HttpClient client;
    private final String baseUrl;
    private HttpMethod method;
    private String path;
    private String body = "";
    private final Map<String, String> headers = new HashMap<>();
    private final Map<String, String> query = new HashMap<>();

    DefaultHttpRequest(java.net.http.HttpClient client, String baseUrl) {
        this.client = Objects.requireNonNull(client);
        this.baseUrl = Objects.requireNonNull(baseUrl);
    }

    @Override
    public HttpRequest method(HttpMethod method) {
        this.method = method;
        return this;
    }

    @Override
    public HttpRequest path(String path) {
        this.path = path;
        return this;
    }

    @Override
    public HttpRequest header(String key, String value) {
        headers.put(key, value);
        return this;
    }

    @Override
    public HttpRequest body(String body) {
        this.body = Objects.requireNonNull(body);
        return this;
    }

    @Override
    public HttpRequest query(String key, String value) {
        query.put(key, value);
        return this;
    }

    @Override
    public HttpResponse send() {
        List<String> queryParts = new ArrayList<>();

        for (Map.Entry<String, String> entry : query.entrySet()) {
            queryParts.add(entry.getKey() + "=" + URLEncoder.encode(entry.getValue(), StandardCharsets.UTF_8));
        }

        String url = baseUrl + path;

        if (!queryParts.isEmpty()) {
            url = url + "?" + StringUtils.join(queryParts, "&");
        }

        java.net.http.HttpRequest.Builder builder = java.net.http.HttpRequest.newBuilder()
            .uri(URI.create(url));

        for (Map.Entry<String, String> entry : headers.entrySet()) {
            builder.header(entry.getKey(), entry.getValue());
        }

        switch (method) {
            case GET -> builder.GET();
            case DELETE -> builder.DELETE();
            case POST -> builder.POST(java.net.http.HttpRequest.BodyPublishers.ofString(body));
            case PUT -> builder.PUT(java.net.http.HttpRequest.BodyPublishers.ofString(body));
            case PATCH -> builder.method("PATCH", java.net.http.HttpRequest.BodyPublishers.ofString(body));
        }

        try {
            return new DefaultHttpResponse(client.send(builder.build(), java.net.http.HttpResponse.BodyHandlers.ofString()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
