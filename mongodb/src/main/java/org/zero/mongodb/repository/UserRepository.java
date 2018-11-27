package org.zero.mongodb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.zero.mongodb.dto.User;

public interface UserRepository extends MongoRepository<User, Long> {

    User findByUsername(String username);

}