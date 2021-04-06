package com.endava.neo4jworkshop.repository;

import com.endava.neo4jworkshop.model.relationship.Like;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface LikeRepository extends Neo4jRepository<Like, Long> {

    Set<Like> findByUser_Name(String userName);
}
