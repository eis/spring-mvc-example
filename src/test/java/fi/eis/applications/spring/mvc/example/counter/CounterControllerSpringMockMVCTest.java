package fi.eis.applications.spring.mvc.example.counter;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.asyncDispatch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import fi.eis.applications.spring.mvc.example.WebConfig;

@RunWith(SpringRunner.class)
@WebAppConfiguration 
@ContextConfiguration(classes = { WebConfig.class })
public class CounterControllerSpringMockMVCTest {

    @Autowired
    private WebApplicationContext context;
    
    MockMvc mvc;
    
    @Before
    public void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(context)
                .build();
    }

    @Test
    public void testAdd() throws Exception {
        MvcResult mvcResult = 
                mvc.perform(
                        MockMvcRequestBuilders.post("/add")
                            .accept(MediaType.APPLICATION_JSON)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("{\"int1\":1, \"int2\":2}"))
                    .andExpect(status().isOk())
                    .andExpect(request().asyncStarted())
                    .andReturn();
        mvc.perform(asyncDispatch(mvcResult))
                .andExpect(content().json("{\"value\": 3}", false));
    }
    @Test
    public void testAdd2() throws Exception {
        MvcResult mvcResult = 
                mvc.perform(
                        MockMvcRequestBuilders.post("/add")
                            .accept(MediaType.APPLICATION_JSON)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("{\"int1\":2, \"int2\":3}"))
                    .andExpect(status().isOk())
                    .andExpect(request().asyncStarted())
                    .andReturn();
        mvc.perform(asyncDispatch(mvcResult))
                .andExpect(jsonPath("value").value(5));
    }
}