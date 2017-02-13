package fi.eis.applications.spring.mvc.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringRunner.class)
@WebAppConfiguration 
@ContextConfiguration(classes = { WebConfig.class })
public class WebConfigTest {

    @Test
    public void whenSpringContextIsInstantiated_thenNoExceptions() {
        // When
    }
}