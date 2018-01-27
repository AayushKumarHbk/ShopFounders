package hf.shopfounders.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import hf.shopfounders.exception.*;
import hf.shopfounders.model.*;
import hf.shopfounders.repository.UserRepository;
import hf.shopfounders.validation.ArgAssert;
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
        logger.info("AuthenticationController::register request recieved:" + request.toString());
        final DaoUser daoUser = new ObjectMapper().convertValue(request, DaoUser.class);
        logger.info("request parsed to DaoUser" + daoUser.toString());
        DaoUser searchedUser;
        try{
            logger.info("searching user in DB");
            searchedUser = userRepository.findDaoUserByUsername(daoUser.getUsername());
            ArgAssert.assertNotNull(searchedUser, "searchedUser");
        }catch(IllegalArgumentException e) {
            logger.info("User not found in DB");
            final LoginResponse loginResponse= getLoginResponsePackage(daoUser.getUsername(),
                    BaseErrorCode.getString(BaseErrorCode.CODE1005), false);
            logger.info("sending response: " + loginResponse.toString());
            return new ResponseEntity<LoginResponse>(loginResponse, HttpStatus.OK);
        }catch(Exception e) {
            logger.error("Error from MongoDB");
            final LoginResponse loginResponse= getLoginResponsePackage(daoUser.getUsername(),
                    BaseErrorCode.getString(BaseErrorCode.CODE2001), false);
            logger.info("sending response: " + loginResponse.toString());
            return new ResponseEntity<LoginResponse>(loginResponse, HttpStatus.OK);
        }
        boolean passwordMatches = false;
        if(searchedUser.getPassword().equals(daoUser.getPassword()))
            passwordMatches = true;
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setUsername(daoUser.getUsername());
        LoginStatus loginStatus = new LoginStatus();
        if(passwordMatches) {
            loginStatus.setStatus(true);
            loginStatus.setMessage("login successful");
        }else {
            loginStatus.setStatus(false);
            loginStatus.setMessage(BaseErrorCode.getString(BaseErrorCode.CODE1004));
        }
        loginResponse.setLoginStatus(loginStatus);
        logger.info("sending response: " + loginResponse.toString());
        return new ResponseEntity<LoginResponse>(loginResponse, HttpStatus.OK);
    }

    public LoginResponse getLoginResponsePackage(String username, String errorMessage, boolean status) {
        final LoginResponse loginResponse = new LoginResponse();
        loginResponse.setUsername(username);
        final LoginStatus loginStatus = new LoginStatus();
        loginStatus.setMessage(errorMessage);
        loginStatus.setStatus(false);
        loginResponse.setLoginStatus(loginStatus);
        return loginResponse;
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
