package com.endava.neo4jworkshop.model;

import com.endava.neo4jworkshop.model.time.Hour;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@ToString(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
@NodeEntity
public class Comment extends GraphNode {

    String content;

    @Relationship(type = "WRITTEN_AT")
    Hour hour;

    @Relationship(type = "COMMENTED", direction = Relationship.INCOMING)
    User commenter;

    @Setter(AccessLevel.NONE)
    @Relationship(type = "REPLIED_TO", direction = Relationship.INCOMING)
    Set<Comment> replies = new LinkedHashSet<>();

    public void addReply(Comment reply) {
        replies.add(reply);
    }
}
