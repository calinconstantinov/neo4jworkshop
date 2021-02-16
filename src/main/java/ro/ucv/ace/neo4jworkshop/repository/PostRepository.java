package ro.ucv.ace.neo4jworkshop.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;
import ro.ucv.ace.neo4jworkshop.model.Post;

import java.util.Set;

@Repository
public interface PostRepository extends Neo4jRepository<Post, Long> {

    Set<Post> findByPoster_Name(String posterName);
}
