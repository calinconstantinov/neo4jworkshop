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
public class Credentials extends GraphEntity {

    String email;

    @ToString.Exclude
    String password;

    @ToString.Exclude
    @Relationship(type = "HAS_CREDENTIALS", direction = Relationship.INCOMING)
    User user;
}
