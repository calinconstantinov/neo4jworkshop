package ro.ucv.ace.neo4jworkshop.model;

import lombok.*;
import org.neo4j.ogm.annotation.Index;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NodeEntity
public class Credentials {

  @Setter(AccessLevel.NONE)
  private Long id;

  @EqualsAndHashCode.Include
  @Index(unique = true)
  private Integer uuid;

  @ToString.Include
  private String email;

  @ToString.Include
  private String password;

  @Relationship(type = "HAS_CREDENTIALS", direction = Relationship.INCOMING)
  private User user;
}
