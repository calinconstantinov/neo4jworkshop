package ro.ucv.ace.neo4jworkshop.repository;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import ro.ucv.ace.neo4jworkshop.model.Company;
import ro.ucv.ace.neo4jworkshop.repository.queryresult.FriendsOfEmployeesCompany;

import java.util.List;

public interface CompanyRepository extends Neo4jRepository<Company, Long> {

  @Query("MATCH (c:Company)-[:EMPLOYED]->(u:User)-[:FRIENDS_WITH]-(u2:User)<-[:EMPLOYED]-(c2:Company) " +
      "WHERE c.uuid <> c2.uuid AND c.name = {0} " +
      "RETURN c2 as company, count(DISTINCT u2) as friendsOfEmployees ORDER BY friendsOfEmployees DESC")
  List<FriendsOfEmployeesCompany> findFriendsOfEmployeesCompanies(String companyName);
}
