package com.endava.neo4jworkshop.repository;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
@SuppressWarnings("rawtypes")
public interface HelperNeo4jRepository extends Neo4jRepository {

    @Query("MATCH (n) DETACH DELETE n")
    void deleteData();
}
