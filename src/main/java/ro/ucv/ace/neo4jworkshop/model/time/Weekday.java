package ro.ucv.ace.neo4jworkshop.model.time;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.neo4j.ogm.annotation.Index;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;
import ro.ucv.ace.neo4jworkshop.model.GraphEntity;

@Getter
@Setter
@NodeEntity
@ToString(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
public class Weekday extends GraphEntity {

    @Index(unique = true)
    Long value;

    @Relationship(type = "NEXT_WEEKDAY")
    Weekday nextWeekday;
}
