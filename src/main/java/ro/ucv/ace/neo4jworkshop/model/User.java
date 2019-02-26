package ro.ucv.ace.neo4jworkshop.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.neo4j.ogm.annotation.Index;
import org.neo4j.ogm.annotation.NodeEntity;

@Getter
@Setter
@NodeEntity(label = "User")
public class User {

	@Setter(AccessLevel.NONE)
	private Long id;

	@Index(unique = true)
	private Integer uuid;

	private String name;

}
