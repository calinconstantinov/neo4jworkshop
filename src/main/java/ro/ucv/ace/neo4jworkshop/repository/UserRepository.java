package ro.ucv.ace.neo4jworkshop.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import ro.ucv.ace.neo4jworkshop.model.User;

public interface UserRepository extends Neo4jRepository<User, Long> {

  User findByUuid(Integer uuid);

  User findByName(String name);
}
