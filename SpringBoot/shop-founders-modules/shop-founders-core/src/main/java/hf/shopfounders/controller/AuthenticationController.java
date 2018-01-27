package hf.shopfounders.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import hf.shopfounders.exception.BaseErrorCode;
import hf.shopfounders.model.*;
import hf.shopfounders.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

    @Autowired
    private UserRepository userRepository;

    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        logger.info("request received: " + request.toString());
        LoginResponse response = new LoginResponse();
        LoginStatus loginStatus = new LoginStatus();
        loginStatus.setMessage("dummy message");
        loginStatus.setStatus(false);
        response.setLoginstatus(loginStatus);
        return new ResponseEntity<LoginResponse>(response, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RegisterResponse> register(@Valid @RequestBody RegisterRequest request) {
        logger.info("AuthenticationController::register request recieved:" + request.toString());

        final DaoUser daoUser = new ObjectMapper().convertValue(request, DaoUser.class);
        logger.info("request parsed to DaoUser" + daoUser.toString());
        RegisterResponse response = new RegisterResponse();
        response.setUsername(daoUser.getUsername());
        RegisterStatus registerStatus = new RegisterStatus();
        try {
            logger.info("attempting to insert data in DB");
            userRepository.save(daoUser);
        }catch (DuplicateKeyException e) {
            logger.trace(e.getMessage());
            final String errorMessage = BaseErrorCode.getString(BaseErrorCode.CODE2000);
            registerStatus.setMessage(errorMessage);
            registerStatus.setStatus(false);
            response.setRegisterStatus(registerStatus);
            return new ResponseEntity<RegisterResponse>(response, new HttpHeaders(), HttpStatus.OK);
        }
        logger.info("user registered successfully");
        registerStatus.setStatus(true);
        registerStatus.setMessage("User registered successfully");
        response.setRegisterStatus(registerStatus);
        logger.info("sending response: " + response.toString());
        return new ResponseEntity<RegisterResponse>(response, new HttpHeaders(), HttpStatus.OK);
    }
}
