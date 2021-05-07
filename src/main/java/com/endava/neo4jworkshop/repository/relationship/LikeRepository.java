package com.endava.neo4jworkshop.repository.relationship;

import com.endava.neo4jworkshop.model.relationship.Like;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface LikeRepository extends GraphRelationshipRepository<Like> {

    Set<Like> findByUser_Name(String userName);
}
