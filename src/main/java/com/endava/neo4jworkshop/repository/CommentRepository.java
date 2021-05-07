package com.endava.neo4jworkshop.repository;

import com.endava.neo4jworkshop.model.Comment;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface CommentRepository extends GraphNodeRepository<Comment> {

    Set<Comment> findByCommenter_Name(String userName);
}
