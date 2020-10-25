
# Introduction

Defines an API for an HTTP Client. Ships with a default implementation based on [java.net.http.HttpClient](https://docs.oracle.com/en/java/javase/11/docs/api/java.net.http/java/net/http/HttpClient.html).

# Example Usage

```java
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
```
