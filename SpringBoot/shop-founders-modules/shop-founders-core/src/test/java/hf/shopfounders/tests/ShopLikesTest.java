package hf.shopfounders.tests;

import hf.shopfounders.config.TestConfig;
import hf.shopfounders.controller.AuthenticationController;
import hf.shopfounders.controller.ShopController;
import hf.shopfounders.dao.DaoShop;
import hf.shopfounders.dao.DaoShopLikes;
import hf.shopfounders.model.*;
import hf.shopfounders.repository.ShopLikesRepository;
import hf.shopfounders.repository.ShopRepository;
import hf.shopfounders.repository.UserRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringRunner.class)
@DataMongoTest
@SpringBootTest
@ContextConfiguration(classes=TestConfig.class)
public class ShopLikesTest {

    @Autowired
    private ShopController shopController;

    @Autowired
    private AuthenticationController authenticationController;

    @Autowired
    private ShopRepository shopRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ShopLikesRepository likesRepository;

    private static String username, password, role, shopId;

    static {
        username = "Mackey";
        password = "Mister";
        role = "admin";
    }

    @Before
    public void putDummyData() {
        // insert a dummy user
        userRepository.deleteAll();
        RegisterRequest request = new RegisterRequest(username, password, role);
        ResponseEntity<RegisterResponse> responseEntity = authenticationController.register(request);
        RegisterResponse response = responseEntity.getBody();
        //check if response is correct
        RegisterStatus status = response.getRegisterStatus();
        Assert.assertNotNull(status);
        Assert.assertEquals(true, status.getStatus());

        List<DaoShop> list = new ArrayList<>();
        //insert dummy shops
        for(int i=0; i<=10; i++) {
            list.add(new DaoShop("shop_"+i, "picture_"+i, "name_"+i, "email_"+i, "city_"+i, new DaoShopLocation("point", new double[]{ 1,2})));
        }
        shopRepository.deleteAll();
        list.stream().forEach(x-> shopRepository.save(x));
    }

    @Before
    public void getShopIdForTest() {
        List<DaoShop> shopList = shopRepository.findAll();
        Assert.assertNotNull(shopList);
        shopId = shopList.get(0).get_id();
        Assert.assertNotNull(shopId);
    }

    /*
     *
     * Unit test for Fresh User Registration
     */
    @Test
    public void aNewUserRegisterTest() {
        //clear user repository
        userRepository.deleteAll();
        //create a regsiter request
        RegisterRequest request = new RegisterRequest(username, password, role);
        ResponseEntity<RegisterResponse> responseEntity = authenticationController.register(request);
        RegisterResponse response = responseEntity.getBody();
        //check if response is correct
        RegisterStatus status = response.getRegisterStatus();
        // assertions on response
        Assert.assertEquals(request.getUsername(), response.getUsername());
        Assert.assertNotNull(status);
        Assert.assertEquals(true, status.getStatus());
    }

    /*
     *
     * Unit test for User Registration
     * but with different password
     */
    @Test
    public void bUserReRegisterTest() {
        RegisterRequest request = new RegisterRequest(username, "Crackey", role);
        ResponseEntity<RegisterResponse> responseEntity = authenticationController.register(request);
        RegisterResponse response = responseEntity.getBody();
        //check if response is correct
        RegisterStatus status = response.getRegisterStatus();
        // assertions on response
        Assert.assertEquals(request.getUsername(), response.getUsername());
        Assert.assertNotNull(status);
        Assert.assertEquals(false, status.getStatus());
    }

    /*
     *
     * Unit test for a registered user login
     */
    @Test
    public void cRegisteredUserLoginTest() {
        LoginRequest request = new LoginRequest(username, password, role);
        ResponseEntity<LoginResponse> responseEntity = authenticationController.login(request);
        LoginResponse response = responseEntity.getBody();
        LoginStatus status = response.getLoginStatus();
        Assert.assertNotNull(status);
        Assert.assertEquals(true, status.getStatus());
    }

    @Test
    public void dNonRegisteredUserLoginTest() {
        LoginRequest request = new LoginRequest("Barney", "Flinstones", role);
        ResponseEntity<LoginResponse> responseEntity = authenticationController.login(request);
        LoginResponse response = responseEntity.getBody();
        LoginStatus status = response.getLoginStatus();
        Assert.assertNotNull(status);
        Assert.assertEquals(false, status.getStatus());
    }

    @Test
    public void eIncorrectPasswordLoginTest() {
        LoginRequest request = new LoginRequest("Mackey", "Flinstones", role);
        ResponseEntity<LoginResponse> responseEntity = authenticationController.login(request);
        LoginResponse response = responseEntity.getBody();
        LoginStatus status = response.getLoginStatus();
        Assert.assertNotNull(status);
        Assert.assertEquals(false, status.getStatus());
    }

    /*
     *
     * Unit test for Fresh User Registration
     */
    @Test
    public void fGetAllShopsTest() {
        //clear user repository
        List<DaoShop> shopList = shopRepository.findAll();
        ResponseEntity<GetAllShopsResponse> responseEntity = shopController.getAllShops();
        GetAllShopsResponse response = responseEntity.getBody();
        //check if response is correct
        GetAllShopsStatus status = response.getGetAllShopsStatus();
        // assertions on response
        Assert.assertNotNull(status);
        Assert.assertEquals(status.getStatus(), true);
        // check if same size of sollection is being retrieved from response
        Assert.assertEquals(shopList.size(), response.getShops().size());
    }

    /*
     *
     * Unit test for User Registration
     * but with different password
     */
    @Test
    public void gLikeInsertedTest() {
        likesRepository.deleteAll();
        long listSize = likesRepository.count();
        ShopLikeRequest likeRequest = new ShopLikeRequest(username,shopId, 1);
        ResponseEntity<ShopLikeResponse> responseEntity = shopController.processLikes(likeRequest);
        ShopLikeResponse response = responseEntity.getBody();
        ShopLikeStatus status = response.getLikeStatus();
        // assertions on response
        Assert.assertNotNull(status);
        Assert.assertEquals(true, status.getStatus());
        // check if same size of collection is being retrieved from response
        Assert.assertEquals(listSize+1, likesRepository.count());
    }

    /*
     *
     * Unit test for User Registration
     * but with different password
     */
    @Test
    public void hDislike() {
        long listSize = likesRepository.count();
        ShopLikeRequest likeRequest = new ShopLikeRequest(username, shopId, 0);
        ResponseEntity<ShopLikeResponse> responseEntity = shopController.processLikes(likeRequest);
        ShopLikeResponse response = responseEntity.getBody();
        ShopLikeStatus status = response.getLikeStatus();
        // assertions on response
        Assert.assertNotNull(status);
        Assert.assertEquals(true, status.getStatus());
        // check if shop has been disliked or not
        DaoShopLikes shopLikes = likesRepository.findByUserIdAndAndShopId(username, shopId);
        Assert.assertEquals(shopLikes.getLikeType(), 0);
    }

    /*
     *
     * Unit test for a registered user login
     */
    @Test
    public void iGetNearByShops() {
        long shopCount = shopRepository.count();
        long finalShopCount =0;
        PreferredShopsRequest request = new PreferredShopsRequest(username);
        ResponseEntity<PreferredShopsResponse> responseEntity = shopController.nearbyShops(request);
        PreferredShopsResponse response = responseEntity.getBody();
        PreferredShopsStatus status = response.getPreferredShopsStatus();
        Assert.assertNotNull(status);
        Assert.assertEquals(status.getStatus(), true);
        List<DaoShopLikes> dislikesList = likesRepository.findByUserIdAndAndLikeType(username, 0);
        dislikesList.removeIf(x-> shopController.isDateMoreThanTwoHours(x.getLikeDate()));
        if(!dislikesList.isEmpty() && dislikesList.size() > 0)
            finalShopCount = shopCount - dislikesList.size();
        List<DaoShopLikes> likesList = likesRepository.findByUserIdAndAndLikeType(username, 1);
        if(!likesList.isEmpty() && likesList.size() > 0)
            finalShopCount = shopCount - likesList.size();
        Assert.assertEquals(finalShopCount, response.getShops().size());
    }
}
