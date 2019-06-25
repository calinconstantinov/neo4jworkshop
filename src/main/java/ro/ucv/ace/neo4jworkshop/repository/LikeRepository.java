package ro.ucv.ace.neo4jworkshop.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import ro.ucv.ace.neo4jworkshop.model.relationship.Like;

import java.util.Set;

public interface LikeRepository extends Neo4jRepository<Like, Long> {

  Set<Like> findByUser_Name(String userName);
}
