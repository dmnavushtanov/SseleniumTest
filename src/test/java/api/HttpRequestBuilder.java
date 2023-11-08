package api;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.testng.Assert;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HttpRequestBuilder {

    public static HttpResponse makePostRequest(String url, String jsonBody) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                .build();
        HttpResponse<String> rs = client.send(request, HttpResponse.BodyHandlers.ofString());
        return rs;
    }

    public static HttpResponse makeGetRequest(String url) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .GET()
                .build();
        HttpResponse<String> rs = client.send(request, HttpResponse.BodyHandlers.ofString());
        return rs;
    }


    public static void verifyContaingRequiredKeys(JsonArray res, String[] keys) {
        for (int i = 0; i < res.size(); i++) {
            JsonObject jsonObject = (JsonObject) res.get(i);

            boolean containsAllKeys = true;
            for (String key : keys) {
                if (!jsonObject.has(key)) {
                    containsAllKeys = false;
                    break;
                }
            }
            Assert.assertTrue(containsAllKeys, "Some keys are missing please check the response");
        }
    }
}
