package hf.shopfounders.config;

import hf.shopfounders.ShopFoundersApp;
import hf.shopfounders.controller.AuthenticationController;
import hf.shopfounders.controller.ShopController;
import hf.shopfounders.repository.ShopLikesRepository;
import hf.shopfounders.repository.TestUserRepository;
import hf.shopfounders.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@PropertySource(value="classpath:application-test.properties")
@EnableAutoConfiguration
@EnableMongoRepositories("hf.shopfounders.repository")
@ComponentScan("hf.shopfounders")
public class TestConfig {

    /*private UserRepository userRepository;
    private ShopLikesRepository likesRepository;

    *//*
     * parametrized class constructor to initialize repositories
     *//*
    public TestConfig(UserRepository userRepository,ShopLikesRepository likesRepository) {
        this.userRepository = userRepository;
        this.likesRepository = likesRepository;
    }*/

    @Bean
    AuthenticationController getAuthenticationController() {
        return new AuthenticationController();
    }

    @Bean
    ShopController getShopController() {
        return new ShopController();
    }

    @Bean
    UserRepository getDaoSynchroRepository() {
        return new TestUserRepository();
    }
}
