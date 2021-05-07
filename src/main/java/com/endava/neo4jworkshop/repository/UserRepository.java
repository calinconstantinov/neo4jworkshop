package com.endava.neo4jworkshop.repository;

import com.endava.neo4jworkshop.model.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends GraphNodeRepository<User> {

    User findByName(String name);
}
