package ro.ucv.ace.neo4jworkshop.model;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.neo4j.ogm.annotation.Index;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

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

  private String name;

  @Relationship(type = "FRIENDS_WITH", direction = Relationship.UNDIRECTED)
  private Set<User> friends = new LinkedHashSet<>();

  @Relationship(type = "LIKES_POST", direction = Relationship.UNDIRECTED)
  private Set<Like> postLikes = new LinkedHashSet<>();

  public void addFriend(User... oneOrMoreFriends) {
    friends.addAll(Arrays.asList(oneOrMoreFriends));
    Arrays.stream(oneOrMoreFriends).forEach(u -> u.getFriends().add(this));
  }

  public void likePost(Like... oneOreMorePostLikes) {
    postLikes.addAll(Arrays.asList(oneOreMorePostLikes));
  }
}
