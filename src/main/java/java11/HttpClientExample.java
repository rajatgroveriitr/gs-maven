package java11;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class HttpClientExample {
    public static void main(String[] args) {
        //syncHttpCommunication();
        asyncHttpCommunication();
    }

    private static void syncHttpCommunication() {
        HttpClient httpClient = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(10))
                .build();

        try {
            String urlEndpoint = "https://postman-echo.com/get";
            URI uri = URI.create(urlEndpoint + "?foo1=bar1&foo2=bar2");
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(uri)
                    .build();
            HttpResponse<String> response = httpClient.send(request,
                    HttpResponse.BodyHandlers.ofString());
            System.out.println("Status code: " + response.statusCode());
            System.out.println("Headers: " + response.headers().allValues("content-type"));
            System.out.println("Body: " + response.body());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    private static void asyncHttpCommunication() {
        final List<URI> uris = Stream.of("https://www.google.com/", "https://www.github.com/", "https://www.yahoo.com/")
                .map(URI::create).collect(toList());

        HttpClient httpClient = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(10))
                .followRedirects(HttpClient.Redirect.ALWAYS)
                .build();

        CompletableFuture[] futures = uris.stream()
                .map(uri -> verifyUri(httpClient, uri))
                .toArray(CompletableFuture[]::new);

        CompletableFuture.allOf(futures).join();
    }

    private static CompletableFuture<Void> verifyUri(HttpClient httpClient, URI uri) {
        HttpRequest request = HttpRequest.newBuilder()
                .timeout(Duration.ofSeconds(5))
                .uri(uri)
                .build();

        return httpClient.sendAsync(request,HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::statusCode)
                .thenApply(statusCode -> statusCode == 200)
                .exceptionally(ex -> false)
                .thenAccept(valid ->
                {
                    if (valid) {
                        System.out.println("[SUCCESS] Verified " + uri);
                    } else {
                        System.out.println("[FAILURE] Could not " + "verify " + uri);
                    }
                });
    }
}
