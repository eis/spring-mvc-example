package fi.eis.applications.spring.mvc.example.counter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CounterConfig {
    @Bean
    public CounterService counterService() {
        return new CounterService();
    }
    @Bean
    public CounterController counterController(CounterService counterService) {
        return new CounterController(counterService);
    }
}
