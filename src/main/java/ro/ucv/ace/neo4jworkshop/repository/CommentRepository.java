package ro.ucv.ace.neo4jworkshop.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import ro.ucv.ace.neo4jworkshop.model.Comment;

public interface CommentRepository extends Neo4jRepository<Comment, Long> {

}
