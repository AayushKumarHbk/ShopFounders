package hf.shopfounders.repository;

import hf.shopfounders.model.DaoUser;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<DaoUser, String> {

    public DaoUser findDaoUserByUsername(String username);

}
