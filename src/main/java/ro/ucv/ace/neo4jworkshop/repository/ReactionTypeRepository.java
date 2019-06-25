package ro.ucv.ace.neo4jworkshop.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import ro.ucv.ace.neo4jworkshop.model.ReactionType;

public interface ReactionTypeRepository extends Neo4jRepository<ReactionType, Long> {

}