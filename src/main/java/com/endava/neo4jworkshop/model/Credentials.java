package com.endava.neo4jworkshop.model;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

@Getter
@Setter
@ToString(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
@NodeEntity
public class Credentials extends GraphNode {

    String email;

    @ToString.Exclude
    String password;

    @ToString.Exclude
    @Relationship(type = "HAS_CREDENTIALS", direction = Relationship.INCOMING)
    User user;
}
