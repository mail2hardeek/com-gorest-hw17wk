package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class PostsAssertionTest {
    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "https://gorest.co.in/public/v2";
        response = given()
                .when()
                .queryParam("page", "1")
                .queryParam("per_page", "20")
                .get("/posts?page=1&per_page=25")
                .then().statusCode(200);
    }

    //1. Verify the if the total record is 25
    @Test
    public void test001() {
        response.body("size", equalTo(25));
    }

    //2. Verify the if the title of id = 40098 is equal to ”Quas arma laudantium corporis uberrime deleniti.”
    @Test
    public void test002() {
        response
                .body("[1]", hasEntry("title", "Quas arma laudantium corporis uberrime deleniti."))
                .body("[1]", hasEntry("id", 40098));
    }

    //3. Check the single user_id in the Array list (2329083)
    @Test
    public void test003() {
        response.body("user_id", hasItem(2329083));
    }

    //4. Check the multiple ids in the ArrayList (2329065, 2329062, 2329062)
    @Test
    public void test004() {
        response.body("user_id", hasItems(2329065, 2329062, 2329062));
    }

    //5. Verify the body of userid = 2272684 is equal “Cohaero at cavus. Administratio callide voluptates. Blandior conservo virga. Tunc aer vorax. Commodi vomer aeger. Clam numquam texo. Viridis aggredior anser. Utilis sollicito communis. Armarium communis considero. Vulgo asper unde. Apparatus aegre cilicium. Vulgus dapifer bardus. Supra tego demens."”
    @Test
    public void test005() {
        response.body("[0].body", equalTo("Cohaero at cavus. Administratio callide voluptates. Blandior conservo virga. Tunc aer vorax. Commodi vomer aeger. Clam numquam texo. Viridis aggredior anser. Utilis sollicito communis. Armarium communis considero. Vulgo asper unde. Apparatus aegre cilicium. Vulgus dapifer bardus. Supra tego demens."));
    }
}
