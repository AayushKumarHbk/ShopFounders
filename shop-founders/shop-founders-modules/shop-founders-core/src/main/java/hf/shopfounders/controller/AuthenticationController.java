package hf.shopfounders.controller;

import hf.shopfounders.model.LoginRequest;
import hf.shopfounders.model.LoginResponse;
import hf.shopfounders.model.RegisterRequest;
import hf.shopfounders.model.RegisterResponse;
import hf.shopfounders.repository.UserRepositories;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

    @Autowired
    private UserRepositories appUserRepository;

    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        logger.info("request received: "+request.toString());
        LoginResponse response = new LoginResponse();
        response.setLoginstatus(false);
        return new ResponseEntity<LoginResponse>(response, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RegisterResponse> register(@RequestBody RegisterRequest request) {
        logger.info("request received: "+request.toString());
        RegisterResponse response = new RegisterResponse();
        response.setRegisterStatus(true);
        logger.info("sending response: "+response.toString());
        return new ResponseEntity<RegisterResponse>(response, new HttpHeaders(), HttpStatus.OK);
    }
}
