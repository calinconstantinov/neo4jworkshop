package com.endava.neo4jworkshop.model.relationship;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;

@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public abstract class GraphRelationship {

    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    Long relationshipId;
}
