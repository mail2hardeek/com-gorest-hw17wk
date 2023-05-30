package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class PostExtractionTest {
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
    //1. Extract the title
    @Test
    public void test001() {
        List<String> title = response.extract().path("title");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The titles are: " + title);
        System.out.println("------------------End of Test---------------------------");
    }
    //2. Extract the total number of record
    @Test
    public void test0002(){
        List<String> totalRecords = response.extract().path("records");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The total number of records are: " + totalRecords.size());
        System.out.println("------------------End of Test---------------------------");
    }
    //3. Extract the body of 15the record
    @Test
    public void test3() {
        String body = response.extract().path("[14].body");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Body of 15th Record : " +  body);
        System.out.println("------------------End of Test---------------------------");
    }
    //4. Extract the user_id of all the records
    @Test
    public void test4() {
        List<?> allOfTheRecordsIds = response.extract().path("user_id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The user_id of all the records : " + allOfTheRecordsIds);
        System.out.println("------------------End of Test---------------------------");
    }
    //5. Extract the title of all the records
    @Test
    public void test5() {
        List<?> title = response.extract().path("title");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The titles of all the records : " + title);
        System.out.println("------------------End of Test---------------------------");
    }
    //6. Extract the title of all records whose user_id = 2329068
    @Test
    public void test6() {
        List<?> allRecords = response.extract().path("findAll{it.user_id==2329068}.body");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The title of all records whose user_id = 2272684 : " + allRecords);
        System.out.println("------------------End of Test---------------------------");
    }
    //7. Extract the body of all records whose id = 40099
    @Test

    public void test7() {
        List<?> allRecordWhoseID2670 = response.extract().path("findAll{it.id==40099}.body");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("the body of all records whose id = 39305 : " + allRecordWhoseID2670);
        System.out.println("------------------End of Test---------------------------");

    }
}
