package ro.ucv.ace.neo4jworkshop.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.neo4j.ogm.annotation.NodeEntity;

@Getter
@Setter
@NodeEntity(label = "User")
public class User {

	@Getter(AccessLevel.NONE)
	private Long id;

	private Long uuid;

	private String name;

}
