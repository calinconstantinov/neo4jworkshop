package com.endava.neo4jworkshop.repository;

import com.endava.neo4jworkshop.model.User;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends Neo4jRepository<User, Long> {

    User findByUuid(String uuid);

    User findByName(String name);
}
