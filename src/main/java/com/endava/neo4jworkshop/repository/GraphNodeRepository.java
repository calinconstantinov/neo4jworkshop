package com.endava.neo4jworkshop.repository;

import com.endava.neo4jworkshop.model.GraphNode;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface GraphNodeRepository<T extends GraphNode> extends Neo4jRepository<T, Long> {

    T findByUuid(String uuid);
}
