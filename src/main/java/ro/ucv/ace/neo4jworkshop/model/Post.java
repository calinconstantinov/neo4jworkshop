package ro.ucv.ace.neo4jworkshop.model;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.neo4j.ogm.annotation.Index;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NodeEntity
public class Post {

  @Setter(AccessLevel.NONE)
  private Long id;

  @EqualsAndHashCode.Include
  @Index(unique = true)
  private Integer uuid;

  @Relationship(type = "POSTED_BY")
  private User poster;

  @Relationship(type = "ON_POST", direction = Relationship.INCOMING)
  private Set<Comment> comments;

  private String content;
}
