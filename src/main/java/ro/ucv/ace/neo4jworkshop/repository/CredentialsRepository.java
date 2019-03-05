package ro.ucv.ace.neo4jworkshop.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import ro.ucv.ace.neo4jworkshop.model.Credentials;

public interface CredentialsRepository extends Neo4jRepository<Credentials, Long> {

  Credentials findByUser_Name(String name);
}
