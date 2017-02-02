package fi.eis.applications.spring.mvc.example.counter;

import static org.hamcrest.Matchers.equalTo;

import org.junit.Before;
import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class CounterControllerRestAssuredIT {

    
    @Before
    public void setUp() {
        // port for test to connect to
        RestAssured.port = 8080;
    }    

    @Test
    public void testAdd() throws Exception {
        io.restassured.RestAssured
            .given()
                .accept("application/json")
                .contentType(ContentType.JSON)
                .body("{\"int1\":1, \"int2\":2}")
                .log().ifValidationFails()
            .when()
                .post("/spring-mvc-example/add")
            .then()
                .log().ifValidationFails()
                .statusCode(200)
                .content("value",equalTo(3));
        
    }
    @Test
    public void testAdd2() throws Exception {
        io.restassured.RestAssured
            .given()
                .accept("application/json")
                .contentType(ContentType.JSON)
                .body("{\"int1\":2, \"int2\":3}")
                .log().ifValidationFails()
            .when()
                .post("/spring-mvc-example/add")
            .then()
                .log().ifValidationFails()
                .statusCode(200)
                .content("value",equalTo(5));
    }
}