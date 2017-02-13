package fi.eis.applications.spring.mvc.example.hello;

import static org.hamcrest.Matchers.containsString;

import org.junit.Before;
import org.junit.Test;

import io.restassured.RestAssured;

public class HomeRestAssuredAcceptance {
    @Before
    public void setUp() {
        // port for test to connect to
        RestAssured.port = 8080;
    }    

    @Test
    public void testIndex() {
        io.restassured.RestAssured
            .given()
                .log().ifValidationFails()
            .when()
                .get("/spring-mvc-example")
            .then()
                .log().ifValidationFails()
                .statusCode(200)
                .content(containsString("Hello world!"));
    }
}
