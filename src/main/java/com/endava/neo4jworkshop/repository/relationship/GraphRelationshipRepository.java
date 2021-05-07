package com.endava.neo4jworkshop.repository.relationship;

import com.endava.neo4jworkshop.model.relationship.GraphRelationship;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface GraphRelationshipRepository<T extends GraphRelationship> extends Neo4jRepository<T, Long> {

}
