package hf.shopfounders.repository;

import hf.shopfounders.dao.DaoShop;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository holding dummy shop data
 */
@Repository
public interface ShopRepository extends MongoRepository<DaoShop, String> {
}
