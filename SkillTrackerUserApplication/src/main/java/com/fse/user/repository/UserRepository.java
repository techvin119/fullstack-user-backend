package com.fse.user.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.fse.user.model.Profile;

@Repository
public interface UserRepository extends MongoRepository<Profile,Integer> {

}
