package com.endava.neo4jworkshop.repository;

import com.endava.neo4jworkshop.model.Credentials;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CredentialsRepository extends Neo4jRepository<Credentials, Long> {

    Credentials findByUser_Name(String name);
}
