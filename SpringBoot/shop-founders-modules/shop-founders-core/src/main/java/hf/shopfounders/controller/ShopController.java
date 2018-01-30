package hf.shopfounders.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import hf.shopfounders.dao.DaoShopLikes;
import hf.shopfounders.exception.BaseErrorCode;
import hf.shopfounders.dao.DaoShop;
import hf.shopfounders.model.*;
import hf.shopfounders.repository.ShopLikesRepository;
import hf.shopfounders.repository.ShopRepository;
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

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/shop")
public class ShopController {

    private static final Logger logger = LoggerFactory.getLogger(ShopController.class);

    @Autowired
    private ShopRepository shopRepository;
    @Autowired
    private ShopLikesRepository likesRepository;

    /**
     * Rest end-point
     * for retrieving list of all the shops
     *
     * @return ResponseEntity<GetAllShopsResponse>
     *     {@link GetAllShopsResponse} as a response to Http Get Request
     */
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

    /**
     * Rest end-point
     * to like/dislike a shop
     *
     * @param request {@link ShopLikeRequest} to like/dislike a shop
     *
     * @return ResponseEntity<ShopLikeResponse>
     *     {@link ShopLikeResponse} as a response to {@link ShopLikeRequest}
     */
    @PostMapping(value = "/processLikes", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ShopLikeResponse> processLikes(@RequestBody ShopLikeRequest request) {
        logger.info("ShopLikesController::processLikes request recieved:" + request.toString());
        final DaoShopLikes daoShopLikes = new ObjectMapper().convertValue(request, DaoShopLikes.class);
        daoShopLikes.setLikeDate(new Date());
        logger.info("request parsed to DaoShopLikes" + daoShopLikes.toString());
        try {
            logger.info("attempting to insert data in DB");
            likesRepository.save(daoShopLikes);
        }catch (DuplicateKeyException e) {
            logger.trace(e.getMessage());
            final String errorMessage = BaseErrorCode.getString(BaseErrorCode.CODE2000);
            ShopLikeResponse likeResponse = new ShopLikeResponse(
                    daoShopLikes.getUserId(),
                    daoShopLikes.getShopId(),
                    daoShopLikes.getLikeType(),
                    new ShopLikeStatus(false, errorMessage));
            return new ResponseEntity<ShopLikeResponse>(likeResponse, new HttpHeaders(), HttpStatus.OK);
        }
        logger.info(daoShopLikes.getLikeType() + " added successfully");
        ShopLikeResponse likeResponse = new ShopLikeResponse(
                daoShopLikes.getUserId(),
                daoShopLikes.getShopId(),
                daoShopLikes.getLikeType(),
                new ShopLikeStatus(true, "Shop like status updated"));
        logger.info("sending response: " + likeResponse.toString());
        return new ResponseEntity<ShopLikeResponse>(likeResponse, new HttpHeaders(), HttpStatus.OK);
    }

    /**
     * Rest end-point to get nearby shops
     *
     * @param username username
     *
     * @return ResponseEntity<NearbyShopsResponse>
     *     {@link NearbyShopsResponse} as a response
     */
    @PostMapping(value = "/getNearbyShops", consumes = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<NearbyShopsResponse> getNearbyShops(@RequestBody String username) {
        logger.info("AuthenticationController::getAllShops request recieved:"+ username);
        List<DaoShop> shopList = null;
        try{
            ArgAssert.assertNotEmpty(username, "username");
            shopList = shopRepository.findAll();
            ArgAssert.assertNotEmpty(shopList, "shop list");
            logger.info(shopList.size()+" shop(s) found");
            List<DaoShopLikes> likesList = likesRepository.findAll();
            likesList.removeIf(p->!p.getUserId().equals(username));
            shopList.removeIf(x->
                    !likesList.stream().anyMatch(y->y.getShopId().equals(x.get_id()))
            );
            ArgAssert.assertNotEmpty(shopList, "likes list");
            logger.info(shopList.size()+" shop(s) filtered in");
            //shopList.stream().forEach(p -> logger.info(p.toString()));
        }catch(NullPointerException e) {
            logger.info("error while processing list(s)");
            final NearbyShopsResponse response = new NearbyShopsResponse(
                    null, new NearbyShopsStatus(
                    false, BaseErrorCode.getString(BaseErrorCode.CODE2002)));
            logger.info("sending response: " + response.toString());
            return new ResponseEntity<NearbyShopsResponse>(response, HttpStatus.OK);
        }catch(IllegalArgumentException e) {
            logger.info(e.getMessage());
            final NearbyShopsResponse response = new NearbyShopsResponse(
                    null, new NearbyShopsStatus(false, e.getMessage()));
            logger.info("sending response: " + response.toString());
            return new ResponseEntity<NearbyShopsResponse>(response, HttpStatus.OK);
        }catch(Exception e) {
            logger.error("Error from MongoDB");
            final NearbyShopsResponse response = new NearbyShopsResponse(
                    null, new NearbyShopsStatus(
                    false, BaseErrorCode.getString(BaseErrorCode.CODE2001)));
            logger.info("sending response: " + response.toString());
            return new ResponseEntity<NearbyShopsResponse>(response, HttpStatus.OK);
        }
        final String message = shopList.size() + " shop(s) found";
        final NearbyShopsResponse response = new NearbyShopsResponse(
                shopList, new NearbyShopsStatus(true, message));
        logger.info("sending response: " + response.toString());
        return new ResponseEntity<NearbyShopsResponse>(response, new HttpHeaders(), HttpStatus.OK);
    }
}
