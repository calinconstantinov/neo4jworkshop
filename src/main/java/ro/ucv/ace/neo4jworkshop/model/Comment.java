package ro.ucv.ace.neo4jworkshop.model;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.neo4j.ogm.annotation.Index;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;
import ro.ucv.ace.neo4jworkshop.model.time.Hour;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NodeEntity
public class Comment {

  @Setter(AccessLevel.NONE)
  private Long id;

  @EqualsAndHashCode.Include
  @Index(unique = true)
  private Integer uuid;

  @Relationship(type = "COMMENTED", direction = Relationship.INCOMING)
  private User commenter;

  @Setter(AccessLevel.NONE)
  @Relationship(type = "REPLIED TO", direction = Relationship.INCOMING)
  private Set<Comment> replies = new LinkedHashSet<>();

  @Relationship(type = "WRITTEN_AT")
  private Hour hour;

  private String content;

  public void addReply(Comment reply) {
    replies.add(reply);
  }
}
