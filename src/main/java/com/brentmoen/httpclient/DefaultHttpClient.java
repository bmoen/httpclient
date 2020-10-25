package com.brentmoen.httpclient;

import java.util.Objects;

class DefaultHttpClient implements HttpClient {
    private final java.net.http.HttpClient client;
    private String baseUrl = "";

    DefaultHttpClient(java.net.http.HttpClient client) {
        this.client = Objects.requireNonNull(client);
    }

    @Override
    public HttpClient setBaseUrl(String baseUrl) {
        this.baseUrl = Objects.requireNonNull(baseUrl);
        return this;
    }

    @Override
    public HttpRequest request() {
        return new DefaultHttpRequest(client, baseUrl);
    }

    public static void main(String[] args) {
        HttpClient client = HttpClientFactory.getInstance().create();
        client.setBaseUrl("https://example.com");

        HttpResponse response = client.request()
            .method(HttpMethod.GET)
            .header("Accept", "application/json")
            .path("/api/exampleroute")
            .query("param", "value")
            .send();

        if (response.status() == 200) {
            System.out.println(response.body());
        }
    }
}
