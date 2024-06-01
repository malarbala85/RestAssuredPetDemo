package com.example;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.junit.Test;

import java.io.File;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

public class ImageUpload {

    @Test
    @Description("Test to upload an image and verify the response")
    public void testUploadImage() {
        // Get the project root directory
        String projectRoot = System.getProperty("user.dir");

        // Set the relative path to the image file
        File file = new File(projectRoot + "\\pets.jpg");

        // Upload the image and verify the response
        uploadImage(file);
    }

    @Step("Uploading image and verifying response")
    public void uploadImage(File file) {
        // Send the POST request to upload the image
        Response response = given()
                .header("accept", "application/json")
                .header("Content-Type", "multipart/form-data")
                .multiPart("file", file, "image/png")
                .post("https://petstore.swagger.io/v2/pet/1/uploadImage");

        // Print the response
        response.prettyPrint();

        response.then().statusCode(200)
                .body("code", equalTo(200))
                .body("message", containsString("File uploaded to"));
    }
}
