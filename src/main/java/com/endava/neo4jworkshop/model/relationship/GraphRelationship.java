package com.endava.neo4jworkshop.model.relationship;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public abstract class GraphRelationship {

    @Setter(AccessLevel.NONE)
    Long id;
}
