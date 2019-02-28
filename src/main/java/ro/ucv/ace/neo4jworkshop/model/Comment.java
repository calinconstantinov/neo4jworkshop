package ro.ucv.ace.neo4jworkshop.model;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.neo4j.ogm.annotation.Index;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NodeEntity
public class Comment {

  @Setter(AccessLevel.NONE)
  private Long relationshipId;

  @EqualsAndHashCode.Include
  @Index(unique = true)
  private Integer uuid;

  private String content;

  @Relationship(type = "POSTED_BY", direction = Relationship.INCOMING)
  private User poster;

  @Relationship(type = "ON_POST")
  private Post post;
}
