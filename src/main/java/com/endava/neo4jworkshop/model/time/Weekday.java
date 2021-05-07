package com.endava.neo4jworkshop.model.time;

import com.endava.neo4jworkshop.model.GraphNode;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.neo4j.ogm.annotation.Index;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

@Getter
@Setter
@ToString(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
@NodeEntity
public class Weekday extends GraphNode {

    @Index(unique = true)
    Long value;

    @Relationship(type = "NEXT_WEEKDAY")
    Weekday nextWeekday;
}
