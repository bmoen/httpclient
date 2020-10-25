package com.brentmoen.httpclient;

import java.util.List;
import java.util.Map;
import java.util.Objects;

class DefaultHttpResponse implements HttpResponse {
    private final java.net.http.HttpResponse<String> response;
    private Map<String, List<String>> headers;

    DefaultHttpResponse(java.net.http.HttpResponse<String> response) {
        this.response = Objects.requireNonNull(response);
    }

    @Override
    public int status() {
        return response.statusCode();
    }

    @Override
    public String body() {
        return response.body();
    }

    @Override
    public String header(String key) {
        if (headers == null) {
            for (Map.Entry<String, List<String>> entry : response.headers().map().entrySet()) {
                headers.put(entry.getKey(), entry.getValue());
            }
        }

        List<String> value = headers.get(key);

        if (value == null || value.size() == 0) {
            return null;
        }

        return value.get(0);
    }
}
