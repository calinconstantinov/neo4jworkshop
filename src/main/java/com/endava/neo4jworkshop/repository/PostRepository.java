package com.endava.neo4jworkshop.repository;

import com.endava.neo4jworkshop.model.Post;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface PostRepository extends GraphNodeRepository<Post> {

    Set<Post> findByPoster_Name(String posterName);
}
