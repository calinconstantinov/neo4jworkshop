package com.endava.neo4jworkshop.model;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;
import com.endava.neo4jworkshop.model.time.Hour;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@NodeEntity
@ToString(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
public class Comment extends GraphEntity {

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
