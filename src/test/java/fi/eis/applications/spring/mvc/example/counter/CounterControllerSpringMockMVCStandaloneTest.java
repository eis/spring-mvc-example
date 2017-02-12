package fi.eis.applications.spring.mvc.example.counter;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.HttpClientErrorException;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.asyncDispatch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CounterControllerSpringMockMVCStandaloneTest {

    @Mock
    CounterService counterService;

    @InjectMocks
    CounterController controllerUnderTest;    
    
    MockMvc mvc;
    
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mvc = MockMvcBuilders.standaloneSetup(controllerUnderTest)
                .setControllerAdvice(new CounterControllerAdvise())
                .build();
    }

    @Test
    public void testAdd() throws Exception {
        Mockito.when(counterService.count(ArgumentMatchers.notNull()))
                .thenReturn(new CounterResult(3));
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
        Mockito.when(counterService.count(ArgumentMatchers.notNull()))
                .thenReturn(new CounterResult(5));
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