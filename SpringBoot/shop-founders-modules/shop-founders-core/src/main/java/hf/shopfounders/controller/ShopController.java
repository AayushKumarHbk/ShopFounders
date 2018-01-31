package hf.shopfounders.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.MongoClient;
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
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Update.update;
import static org.springframework.data.mongodb.core.query.Query.query;
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
            ArgAssert.assertNotEmpty(shopList, "shopList");
            logger.info(shopList.size()+" shop(s) found");
        }catch(IllegalArgumentException e) {
            logger.info("User not found in DB");
            final GetAllShopsResponse response = new GetAllShopsResponse(
                    null, new GetAllShopsStatus(
                    false, BaseErrorCode.getString(BaseErrorCode.CODE1005)));
            logger.info("sending response: " + response.toString());
            return new ResponseEntity<GetAllShopsResponse>(response, HttpStatus.OK);
        }catch(Exception e) {
            logger.error("Error from MongoDB");
            final GetAllShopsResponse response = new GetAllShopsResponse(
                    null, new GetAllShopsStatus(
                    false, BaseErrorCode.getString(BaseErrorCode.CODE2001)));
            logger.info("sending response: " + response.toString());
            return new ResponseEntity<GetAllShopsResponse>(response, HttpStatus.OK);
        }
        final String message = shopList.size() + " shop(s) found";
        final GetAllShopsResponse response = new GetAllShopsResponse(shopList, new GetAllShopsStatus(true, message));
        return new ResponseEntity<GetAllShopsResponse>(response, new HttpHeaders(), HttpStatus.OK);
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
            DaoShopLikes retrievedDocument = likesRepository.findByUserIdAndAndShopId(
                    daoShopLikes.getUserId(), daoShopLikes.getShopId());
            MongoOperations mongoOperations = new MongoTemplate(new SimpleMongoDbFactory(new MongoClient(), "shops"));
            mongoOperations.upsert(query(where("userId").is(daoShopLikes.getUserId()))
                            .addCriteria(where("shopId").is(daoShopLikes.getShopId())),
                    update("likeType", daoShopLikes.getLikeType()),
                    DaoShopLikes.class);
        }catch (Exception e) {
            logger.trace(e.getMessage());
            final String errorMessage = BaseErrorCode.getString(BaseErrorCode.CODE2001);
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
     * @param request username
     *
     * @return ResponseEntity<NearbyShopsResponse>
     *     {@link NearbyShopsResponse} as a response to {@link PreferredShopsRequest}
     */
    @PostMapping(value = "/preferredShops", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PreferredShopsResponse> preferredShops(@RequestBody PreferredShopsRequest request) {
        logger.info("AuthenticationController::getAllShops request received:"+ request);
        final String username = request.getUsername();
        List<DaoShop> shopList = null;
        try{
            ArgAssert.assertNotEmpty(username, "username");
            shopList = shopRepository.findAll();
            ArgAssert.assertNotEmpty(shopList, "shop list");
            logger.info(shopList.size()+" shop(s) found");
            List<DaoShopLikes> likesList = likesRepository.findAll();
            likesList.removeIf(p->!p.getUserId().equals(username) || p.getLikeType() == 0);
            likesList.removeIf(p->p.getLikeType() == 0);
            shopList.removeIf(x->
                    !likesList.stream().anyMatch(y->y.getShopId().equals(x.get_id()))
            );
            ArgAssert.assertNotEmpty(shopList, "likes list");
            logger.info(shopList.size()+" shop(s) filtered in");
            //shopList.stream().forEach(p -> logger.info(p.toString()));
        }catch(NullPointerException e) {
            logger.info("error while processing list(s)");
            final PreferredShopsResponse response = new PreferredShopsResponse(
                    null, new PreferredShopsStatus(
                    false, BaseErrorCode.getString(BaseErrorCode.CODE2002)));
            logger.info("sending response: " + response.toString());
            return new ResponseEntity<PreferredShopsResponse>(response, HttpStatus.OK);
        }catch(IllegalArgumentException e) {
            logger.info(e.getMessage());
            final PreferredShopsResponse response = new PreferredShopsResponse(
                    null, new PreferredShopsStatus(false, e.getMessage()));
            logger.info("sending response: " + response.toString());
            return new ResponseEntity<PreferredShopsResponse>(response, HttpStatus.OK);
        }catch(Exception e) {
            logger.error("Error from MongoDB");
            final PreferredShopsResponse response = new PreferredShopsResponse(
                    null, new PreferredShopsStatus(
                    false, BaseErrorCode.getString(BaseErrorCode.CODE2001)));
            logger.info("sending response: " + response.toString());
            return new ResponseEntity<PreferredShopsResponse>(response, HttpStatus.OK);
        }
        final String message = shopList.size() + " shop(s) found";
        final PreferredShopsResponse response = new PreferredShopsResponse(
                shopList, new PreferredShopsStatus(true, message));
        logger.info("sending response: " + response.toString());
        return new ResponseEntity<PreferredShopsResponse>(response, new HttpHeaders(), HttpStatus.OK);
    }
}
