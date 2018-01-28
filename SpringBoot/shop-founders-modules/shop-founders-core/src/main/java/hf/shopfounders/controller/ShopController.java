package hf.shopfounders.controller;

import hf.shopfounders.exception.BaseErrorCode;
import hf.shopfounders.model.DaoShop;
import hf.shopfounders.model.GetAllShopsResponse;
import hf.shopfounders.model.GetAllShopsStatus;
import hf.shopfounders.model.LoginResponse;
import hf.shopfounders.repository.ShopRepository;
import hf.shopfounders.validation.ArgAssert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/shop")
public class ShopController {

    private static final Logger logger = LoggerFactory.getLogger(ShopController.class);

    @Autowired
    private ShopRepository shopRepository;

    @GetMapping("/getAllShops")
    public ResponseEntity<GetAllShopsResponse> getAllShops() {
        logger.info("AuthenticationController::getAllShops request recieved:");
        List<DaoShop> shopList;
        try{
        logger.info("searching for shops in DB");
        shopList = shopRepository.findAll();
        logger.info(shopList.size()+" shop(s) found");
        }catch(IllegalArgumentException e) {
            logger.info("User not found in DB");
            final GetAllShopsResponse response= createGetAllShopsResponse(null,
                    BaseErrorCode.getString(BaseErrorCode.CODE1005), false);
            logger.info("sending response: " + response.toString());
            return new ResponseEntity<GetAllShopsResponse>(response, HttpStatus.OK);
        }catch(Exception e) {
            logger.error("Error from MongoDB");
            final GetAllShopsResponse response= createGetAllShopsResponse(null,
                    BaseErrorCode.getString(BaseErrorCode.CODE2001), false);
            logger.info("sending response: " + response.toString());
            return new ResponseEntity<GetAllShopsResponse>(response, HttpStatus.OK);
        }
        final String message = shopList.size() + " shop(s) found";
        return new ResponseEntity<GetAllShopsResponse>(
                createGetAllShopsResponse(shopList, message, true),
                new HttpHeaders(),
                HttpStatus.OK);
    }

    public GetAllShopsResponse createGetAllShopsResponse(List<DaoShop> shopList, String message, boolean status) {
        GetAllShopsResponse response = new GetAllShopsResponse();
        GetAllShopsStatus shopsStatus = new GetAllShopsStatus();
        shopsStatus.setMessage(message);
        shopsStatus.setStatus(status);
        if(status) {
            ArgAssert.assertNotEmpty(shopList, "shopList");
            response.setShops(shopList);
        }
        response.setGetAllShopsStatus(shopsStatus);
        return response;
    }
}
