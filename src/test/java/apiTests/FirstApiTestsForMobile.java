package apiTests;

import api.HttpRequestBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.net.http.HttpResponse;

import org.json.JSONObject;


public class FirstApiTestsForMobile {
    public static final String BASE_MOBILE_URL = "https://5d67b6b46847d40014f663fc.mockapi.io/api/cars";

    @Test(description = "Create car and verify the response")
    void createCarViaApi() {
        HttpResponse res;
        try {
            res = HttpRequestBuilder.makePostRequest(BASE_MOBILE_URL, prepareCarPayload("Audi", "A5").toString());
            System.out.println(res);
        } catch (Exception e) {
            System.out.println("Something bad happen to the api cal ");
            throw new RuntimeException(e);
        }
        Assert.assertEquals(res.headers().allValues("content-type").get(0), "application/json", "The headers doesn't contains json type");
        Assert.assertEquals(res.statusCode(), 201, "The status code is not what we expected");
    }

    @Test(description = "Get car and verify the response contains keys")
    void getCarsViaApi() {
        HttpResponse res;
        String getCarsByModel = "?manufacturer=";
        String [] expectedKeys = {"model", "manufacturer", "id"};
        try {
            res = HttpRequestBuilder.makeGetRequest(BASE_MOBILE_URL + getCarsByModel + "Audi");
            Assert.assertEquals(res.statusCode(), 200, "The status code is not what was expected");
            JsonArray carsInTheResponse = JsonParser.parseString((String) res.body()).getAsJsonArray();
            HttpRequestBuilder.verifyContaingRequiredKeys(carsInTheResponse, expectedKeys);
            System.out.println(res);
        } catch (Exception e) {
            System.out.println("Something bad happen to the api cal ");
            throw new RuntimeException(e);
        }
    }
    JSONObject prepareCarPayload(String manufacturer, String model) {
        JSONObject body = new JSONObject();
        body.put(manufacturer, model);
        return body;
    }


}
