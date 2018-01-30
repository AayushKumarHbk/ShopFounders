package hf.shopfounders.repository;

import hf.shopfounders.dao.DaoUser;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<DaoUser, String> {

    public DaoUser findDaoUserByUsername(String username);

}
