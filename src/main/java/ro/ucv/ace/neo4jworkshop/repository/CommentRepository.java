package ro.ucv.ace.neo4jworkshop.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;
import ro.ucv.ace.neo4jworkshop.model.Comment;

import java.util.Set;

@Repository
public interface CommentRepository extends Neo4jRepository<Comment, Long> {

    Set<Comment> findByCommenter_Name(String userName);
}
