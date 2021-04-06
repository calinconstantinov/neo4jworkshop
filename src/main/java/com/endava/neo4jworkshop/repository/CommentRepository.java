package com.endava.neo4jworkshop.repository;

import com.endava.neo4jworkshop.model.Comment;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface CommentRepository extends Neo4jRepository<Comment, Long> {

    Set<Comment> findByCommenter_Name(String userName);
}
