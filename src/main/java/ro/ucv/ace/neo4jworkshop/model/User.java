package ro.ucv.ace.neo4jworkshop.model;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.neo4j.ogm.annotation.Index;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;
import ro.ucv.ace.neo4jworkshop.model.relationship.Like;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@NodeEntity
@ToString(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
public class User extends GraphEntity {

    @Index
    String name;

    //not how it should be done
    @Relationship(type = "HAS_CREDENTIALS")
    Credentials credentials;

    //not how it should be done
    @ToString.Exclude
    @Relationship(type = "FRIENDS_WITH", direction = Relationship.UNDIRECTED)
    Set<User> friends = new LinkedHashSet<>();

    @Relationship(type = "LIKES_POST")
    Set<Like> postLikes = new LinkedHashSet<>();

    public void addFriend(User... oneOrMoreFriends) {
        friends.addAll(Arrays.asList(oneOrMoreFriends));
        Arrays.stream(oneOrMoreFriends).forEach(u -> u.getFriends().add(this));
    }

    public void likePost(Like... oneOreMorePostLikes) {
        postLikes.addAll(Arrays.asList(oneOreMorePostLikes));
    }
}
