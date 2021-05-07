package com.endava.neo4jworkshop.repository;

import com.endava.neo4jworkshop.model.Credentials;
import org.springframework.stereotype.Repository;

@Repository
public interface CredentialsRepository extends GraphNodeRepository<Credentials> {

    Credentials findByUser_Name(String name);
}
