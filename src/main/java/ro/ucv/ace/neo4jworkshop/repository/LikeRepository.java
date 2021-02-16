package ro.ucv.ace.neo4jworkshop.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;
import ro.ucv.ace.neo4jworkshop.model.relationship.Like;

import java.util.Set;

@Repository
public interface LikeRepository extends Neo4jRepository<Like, Long> {

    Set<Like> findByUser_Name(String userName);
}
