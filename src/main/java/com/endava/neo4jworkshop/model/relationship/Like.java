package com.endava.neo4jworkshop.model.relationship;

import com.endava.neo4jworkshop.model.Post;
import com.endava.neo4jworkshop.model.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

@Getter
@Setter
@ToString(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
@RelationshipEntity(type = "LIKES_POST")
public class Like extends GraphRelationship {

    @ToString.Exclude
    @StartNode
    User user;

    //users can like their own posts
    @ToString.Exclude
    @EndNode
    Post post;

    Long timestamp;
}
