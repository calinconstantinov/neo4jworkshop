package ro.ucv.ace.neo4jworkshop.model;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.Set;

@Getter
@Setter
@NodeEntity
@ToString(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
public class Post extends GraphEntity {

    String content;

    @Relationship(type = "POSTED_BY")
    User poster;

    @Relationship(type = "ON_POST", direction = Relationship.INCOMING)
    Set<Comment> comments;
}
