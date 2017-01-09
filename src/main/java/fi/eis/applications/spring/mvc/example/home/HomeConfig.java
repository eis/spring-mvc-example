package fi.eis.applications.spring.mvc.example.home;

import org.springframework.context.annotation.Bean;

public class HomeConfig {
    @Bean
    public HomeController homeController() {
        return new HomeController();
    }
}
