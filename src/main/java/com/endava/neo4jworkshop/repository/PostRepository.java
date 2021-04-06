package com.endava.neo4jworkshop.repository;

import com.endava.neo4jworkshop.model.Post;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface PostRepository extends Neo4jRepository<Post, Long> {

    Set<Post> findByPoster_Name(String posterName);
}
