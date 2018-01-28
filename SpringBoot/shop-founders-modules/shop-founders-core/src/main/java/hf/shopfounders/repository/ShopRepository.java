package hf.shopfounders.repository;

import hf.shopfounders.model.DaoShop;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopRepository extends MongoRepository<DaoShop, String> {
}
