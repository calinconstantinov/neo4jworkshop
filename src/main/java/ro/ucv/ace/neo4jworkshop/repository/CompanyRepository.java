package ro.ucv.ace.neo4jworkshop.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import ro.ucv.ace.neo4jworkshop.model.Company;
import ro.ucv.ace.neo4jworkshop.model.User;

public interface CompanyRepository extends Neo4jRepository<Company, Long> {

  User findByName(String name);
}
