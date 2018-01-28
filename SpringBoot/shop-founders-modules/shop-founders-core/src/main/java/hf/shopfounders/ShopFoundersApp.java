package hf.shopfounders;

import hf.shopfounders.model.DaoUser;
import hf.shopfounders.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@RestController
@EnableAutoConfiguration
@ComponentScan("hf.shopfounders")
@EnableMongoRepositories("hf.shopfounders.repository")
@EntityScan("hf.shopfounders")
public class ShopFoundersApp implements CommandLineRunner {

    private Logger logger = LoggerFactory.getLogger(ShopFoundersApp.class);

    private UserRepository userRepository;

    public ShopFoundersApp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @RequestMapping(value = "/getInfo")
    public String getFounderName() {
        logger.info("ShopFoundersApp::getFounderName [ENTER]");
        return "Hidden Founders";
    }

    public static void main(String[] args) {
        SpringApplication.run(ShopFoundersApp.class);
    }

    @Bean
    public WebMvcConfigurer corsFilterConfigurer() {
        logger.info("ShopFoundersApp::corsFilterConfigurer [ENTER]");
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:4200")
                        .allowedMethods("POST", "ORIGIN", "GET")
                        .allowedHeaders("Content-Type", "X-Requested-With", "accept", "Origin",
                                "Access-Control-Request-Method", "Access-Control-Request-Headers");
            }
        };
    }

    @Override
    public void run(String... strings) throws Exception {

        logger.info("ShopFoundersApp::run [ENTER]");
        List<DaoUser> dummyUserList = Arrays.asList(
                new DaoUser("admin", "admin", "admin"),
                new DaoUser("user", "shopfounders", "admin"));
        logger.info("ShopFoundersApp::run cleaning UserRepository");
        userRepository.deleteAll();
        logger.info("ShopFoundersApp::run inserting dummy List<DaoUsers>");
        userRepository.save(dummyUserList);
    }
}
