package ro.ucv.ace.neo4jworkshop.model;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.neo4j.ogm.annotation.Index;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;
import ro.ucv.ace.neo4jworkshop.model.relationship.Like;

import java.util.*;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NodeEntity
public class User {

  @Setter(AccessLevel.NONE)
  private Long id;

  @EqualsAndHashCode.Include
  @Index(unique = true)
  private Integer uuid;

  @Relationship(type = "HAS_CREDENTIALS")
  private Credentials credentials;

  @Relationship(type = "FRIENDS_WITH", direction = Relationship.UNDIRECTED)
  private Set<User> friends = new LinkedHashSet<>();

  @Relationship(type = "LIKES_POST")
  private Set<Like> postLikes = new LinkedHashSet<>();

  @Index
  private String name;

  public void addFriend(User... oneOrMoreFriends) {
    friends.addAll(Arrays.asList(oneOrMoreFriends));
    Arrays.stream(oneOrMoreFriends).forEach(u -> u.getFriends().add(this));
  }

  public void likePost(Like... oneOreMorePostLikes) {
    postLikes.addAll(Arrays.asList(oneOreMorePostLikes));
  }
}
