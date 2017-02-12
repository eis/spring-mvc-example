package fi.eis.applications.spring.mvc.example.hello;

import fi.eis.applications.spring.mvc.example.home.HomeController;
import io.restassured.RestAssured;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.standaloneSetup;
import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertEquals;

public class HomeRestAssuredStandaloneTest {

    @Test
    public void testIndex() {
        MvcResult res = RestAssuredMockMvc
            .given()
                .log().ifValidationFails()
                .standaloneSetup(new HomeController())
            .when()
                .get("/")
            .then()
                .log().ifValidationFails()
                .statusCode(200)
                .extract().response().mvcResult();
        assertEquals("home", res.getModelAndView().getViewName());
    }
}
