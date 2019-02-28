package ro.ucv.ace.neo4jworkshop.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import ro.ucv.ace.neo4jworkshop.model.Post;

import java.util.Set;

public interface PostRepository extends Neo4jRepository<Post, Long> {

  Set<Post> findByPoster_Name(String name);
}
