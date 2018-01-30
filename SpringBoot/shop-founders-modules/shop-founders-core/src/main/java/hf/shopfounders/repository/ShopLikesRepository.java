package hf.shopfounders.repository;

import hf.shopfounders.dao.DaoShopLikes;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository for storing data related to
 * likes/dislikes of a shop
 * */
@Repository
public interface ShopLikesRepository extends MongoRepository<DaoShopLikes, String> {

    public List<DaoShopLikes> findDaoShopLikesByUserId(String username);
}
