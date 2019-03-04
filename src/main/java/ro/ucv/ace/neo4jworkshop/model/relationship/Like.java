package ro.ucv.ace.neo4jworkshop.model.relationship;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.neo4j.ogm.annotation.*;
import ro.ucv.ace.neo4jworkshop.model.Post;
import ro.ucv.ace.neo4jworkshop.model.User;

@Getter
@Setter
@RelationshipEntity(type = "LIKES_POST")
public class Like {

  @Setter(AccessLevel.NONE)
  @Id
  @GeneratedValue
  private Long relationshipId;

  @StartNode
  private User user;

  @EndNode
  private Post post;

  private Long timestamp;
}
