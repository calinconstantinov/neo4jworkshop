package com.endava.neo4jworkshop.model;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

@Getter
@Setter
@NodeEntity
@ToString(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
public class Reaction extends GraphEntity {

    Long timestamp;

    @Relationship(type = "AT_COMMENT")
    Comment comment;

    @Relationship(type = "REACTED", direction = Relationship.INCOMING)
    User reactingUser;

    @Relationship(type = "OF_TYPE")
    ReactionType reactionType;
}
