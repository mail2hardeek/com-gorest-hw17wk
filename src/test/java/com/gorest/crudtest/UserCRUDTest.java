package com.gorest.crudtest;

import com.gorest.model.UserPojo;
import com.gorest.utils.TestUtils;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class UserCRUDTest extends TestUtils {

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "https://gorest.co.in/public/v2";
        RestAssured.basePath="/users";
    }

    static String firstName = "Max";
    static String email = "Max22"+getRandomValue()+"asr@gmail.com";
    static String status = "active";
    static String gender = "male";
    static String bearerToken = "50edea31afe89011532d4680cedb11a3860444028939e910e2a0013e9b0ce0a1";


    @Test
    public void verifyUserCreatedSuccessfully() {

        UserPojo userPojo = new UserPojo();
        userPojo.setFirstName(firstName);
        userPojo.setStatus(status);
        userPojo.setEmail(email);
        userPojo.setGender(gender);

        Response response = given()
                .header("Authorization","Bearer "+bearerToken)
                .contentType(ContentType.JSON)
                //.header("Content-Type", "application/json")
                .when()
                .body(userPojo)
                .post();//post require 2 things
        //response.then().log().all().statusCode(201);
        response.then().statusCode(201);
        response.prettyPrint();
    }

    @Test
    public void getUser(){
        Response response = given()
                .when()
                .get();
        response.then().statusCode(200);
        response.prettyPrint();
    }

    @Test
    public void verifyUserUpdateSuccessfully(){
        UserPojo userPojo = new UserPojo();
        userPojo.setStatus(status);
        userPojo.setGender("female");

        Response response = given()
                .header("Authorization","Bearer "+bearerToken)
                .contentType(ContentType.JSON)
                .header("Accept", "application/json")
                //.header("Content-Type", "application/json")
                .when()
                .body(userPojo)
                .put("/2272632");//post require 2 things
        response.then().statusCode(201);
        response.prettyPrint();
    }

    @Test
    public void VerifyUserDeleteSuccessfully() {
        Response response = given().log().all()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer "+bearerToken)
                .when()
                .delete("/2329074");
        response.then().statusCode(201);
        response.prettyPrint();
    }
}
