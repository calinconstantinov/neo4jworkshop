package com.endava.neo4jworkshop.repository;

import com.endava.neo4jworkshop.model.Authentication;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthenticationRepository extends GraphNodeRepository<Authentication> {

    Authentication findByUser_Name(String name);
}
