package com.example;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class createpet {

    @Test
    @Description("Test to create a new pet and verify the response")
    public void testCreatePet() {
        // Create JSON object for the request
        JSONObject petJson = new JSONObject();
        petJson.put("id", 0);

        JSONObject categoryJson = new JSONObject();
        categoryJson.put("id", 0);
        categoryJson.put("name", "string");
        petJson.put("category", categoryJson);

        petJson.put("name", "Pug");

        JSONArray photoUrlsJson = new JSONArray();
        photoUrlsJson.put("string");
        petJson.put("photoUrls", photoUrlsJson);

        JSONArray tagsJson = new JSONArray();
        JSONObject tagJson = new JSONObject();
        tagJson.put("id", 0);
        tagJson.put("name", "string");
        tagsJson.put(tagJson);
        petJson.put("tags", tagsJson);

        petJson.put("status", "available");

        // Create the pet and verify the response
        createPet(petJson);
    }

    @Step("Creating pet and verifying response")
    public void createPet(JSONObject petJson) {
        // Send the POST request to create the pet
        Response response = given()
                .header("accept", "application/json")
                .header("Content-Type", "application/json")
                .body(petJson.toString())
                .post("https://petstore.swagger.io/v2/pet");

        // Print the response
        response.prettyPrint();

        // Validate the response status code and content
        response.then().statusCode(200)
                .body("name", equalTo("Pug"))
                .body("status", equalTo("available"));
    }
}

