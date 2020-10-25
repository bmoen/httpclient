package com.brentmoen.httpclient;

class DefaultHttpClientFactory implements HttpClientFactory {
    @Override
    public HttpClient create() {
        return new DefaultHttpClient(java.net.http.HttpClient.newHttpClient());
    }
}
