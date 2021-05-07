package com.endava.neo4jworkshop.model;

import com.endava.neo4jworkshop.model.relationship.Like;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.neo4j.ogm.annotation.Index;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@ToString(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
@NodeEntity
@Slf4j
public class User extends GraphNode {

    @Index
    String name;

    //not how it should be done
    @Relationship(type = "HAS_AUTHENTICATION")
    Authentication authentication;

    //not how it should be done
    @ToString.Exclude
    @Relationship(type = "FRIENDS_WITH", direction = Relationship.UNDIRECTED)
    Set<User> friends = new LinkedHashSet<>();

    @Relationship(type = "LIKES_POST")
    Set<Like> postLikes = new LinkedHashSet<>();

    public void addFriend(User... oneOrMoreFriends) {
        //friends.addAll(Arrays.asList(oneOrMoreFriends));

        for (var oneFriend : oneOrMoreFriends) {
            boolean added = friends.add(oneFriend);
            if (added) {
                // log.info("Added {} to {}", this.name, oneFriend.name);
                log.info("User {} - {} -> List: {}", this.name, this.getId(), friends.stream().map(user -> user.name + " -- " + user.getId()).collect(Collectors.toList()));
            }
        }

        Arrays.stream(oneOrMoreFriends).forEach(u -> {
            boolean added = u.getFriends().add(this);
            if (added) {
                // log.info("Added {} to {}", this.name, u.name);
                log.info("User {} - {} -> List: {}", u.name, u.getId(), u.getFriends().stream().map(user -> user.name + " -- " + user.getId()).collect(Collectors.toList()));
            }
        });
    }

    public void likePost(Like... oneOreMorePostLikes) {
        postLikes.addAll(Arrays.asList(oneOreMorePostLikes));
    }
}
