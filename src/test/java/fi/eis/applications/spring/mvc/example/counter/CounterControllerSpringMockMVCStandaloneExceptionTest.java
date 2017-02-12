package fi.eis.applications.spring.mvc.example.counter;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.HttpClientErrorException;

public class CounterControllerSpringMockMVCStandaloneExceptionTest {

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
    public void testNPE() throws Exception {
        Mockito.when(counterService.count(ArgumentMatchers.notNull()))
            .thenThrow(new NullPointerException());
        mvc.perform(
                MockMvcRequestBuilders.post("/add")
                    .accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("{\"int1\":1, \"int2\":2}"))
            .andExpect(status().isInternalServerError())
            .andExpect(content().string("{\"message\":\"internal_error\"}"));
    }
    @Test
    public void testHTTPException() throws Exception {
        Mockito.when(counterService.count(ArgumentMatchers.notNull()))
            .thenThrow(new HttpClientErrorException(HttpStatus.BAD_REQUEST));
        mvc.perform(
                MockMvcRequestBuilders.post("/add")
                    .accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("{\"int1\":1, \"int2\":2}"))
            .andExpect(status().isBadRequest())
            .andExpect(content().string("{\"message\":\"BAD_REQUEST\"}"));
    }
}