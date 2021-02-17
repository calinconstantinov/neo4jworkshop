package ro.ucv.ace.neo4jworkshop.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;
import ro.ucv.ace.neo4jworkshop.model.User;

@Repository
public interface UserRepository extends Neo4jRepository<User, Long> {

    User findByUuid(String uuid);

    User findByName(String name);
}
