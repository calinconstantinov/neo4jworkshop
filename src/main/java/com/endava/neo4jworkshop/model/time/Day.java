package com.endava.neo4jworkshop.model.time;

import com.endava.neo4jworkshop.model.GraphNode;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.neo4j.ogm.annotation.Index;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.Set;

@Getter
@Setter
@ToString(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
@NodeEntity
public class Day extends GraphNode {

    @Index
    Long value;

    @Relationship(type = "HAS_HOUR")
    Set<Hour> hours;

    @Relationship(type = "NEXT_DAY")
    Day nextDay;
}
