package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class UserAssertionTest {
    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "https://gorest.co.in";
        RestAssured.basePath = "/public/v2/users";
        response = given()
                .when()
                .get("")
                .then().statusCode(200);
        // response.log().all();// to print all data on the console

    }

    //1. Verify the if the total record is 10
    @Test
    public void test001() {
        response.body("size", equalTo(10));
    }

    //2. Verify the if the name of id = 2329061 is equal to ”Vishwamitra Abbott”
    @Test
    public void test002() {
        response
                .body("[2]", hasEntry("id", 2329061))
                .body("[2]", hasEntry("name", "Vishwamitra Abbott"));

    }

    //3. Check the single ‘Name’ in the Array list (Kirti Pothuvaal,)
    @Test
    public void test003() {
        response.body("name", hasItem("Kirti Pothuvaal"));

    }

    //4. Check the multiple ‘Names’ in the ArrayList (Chandraswaroopa Guneta", "Kirti Pothuvaal", "Vishwamitra Abbott)
    @Test
    public void test004() {

        response.body("name", hasItems("Chandraswaroopa Guneta", "Kirti Pothuvaal", "Vishwamitra Abbott"));
    }

    //5. Verify the emai of userid = 2329059 is equal “bharadwaj_daksha@hettinger-legros.example”
    @Test
    public void test005() {
        response
                .body("[4]", hasEntry("id", 2329059))
                .body("[4]", hasEntry("email", "bharadwaj_daksha@hettinger-legros.example"));
    }

    //6. Verify the status is “inactive” of user name is “Chandraswaroopa Guneta”
    @Test
    public void test006() {
        response
                .body("[0]", hasEntry("name", "Chandraswaroopa Guneta"))
                .body("[0]", hasEntry("status", "inactive"));
    }

    //7. Verify the Gender = male of user name is “Chandraswaroopa Guneta”
    @Test
    public void test007() {
        response
                .body("[0]", hasEntry("name", "Chandraswaroopa Guneta"))
                .body("[0]", hasEntry("gender", "female"));
    }
}