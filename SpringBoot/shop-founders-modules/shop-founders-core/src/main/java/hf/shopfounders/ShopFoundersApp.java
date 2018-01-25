package hf.shopfounders;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
@RestController
@EnableAutoConfiguration
@ComponentScan("hf.shopfounders")
@EnableMongoRepositories("hf.shopfounders.repository")
@EntityScan("hf.shopfounders")
public class ShopFoundersApp implements CommandLineRunner {

    @RequestMapping(value = "/getInfo")
    public String getFounderName() {
        return "Hidden Founders";
    }

    public static void main(String[] args) {
        SpringApplication.run(ShopFoundersApp.class);
    }

    @Bean
    public WebMvcConfigurer corsFilterConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/auth/*")
                        .allowedOrigins("http://localhost:4200")
                        .allowedMethods("POST", "ORIGIN", "GET")
                        .allowedHeaders("Content-Type", "X-Requested-With", "accept", "Origin",
                                "Access-Control-Request-Method", "Access-Control-Request-Headers");
            }
        };
    }

    @Override
    public void run(String... strings) throws Exception {

    }
}
