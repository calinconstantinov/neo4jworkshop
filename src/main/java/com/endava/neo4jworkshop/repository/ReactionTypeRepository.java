package com.endava.neo4jworkshop.repository;

import com.endava.neo4jworkshop.model.ReactionType;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReactionTypeRepository extends Neo4jRepository<ReactionType, Long> {

}
