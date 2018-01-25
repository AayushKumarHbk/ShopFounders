package hf.shopfounders.repository;

import hf.shopfounders.model.DaoUser;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepositories extends MongoRepository<DaoUser, String> {

}
