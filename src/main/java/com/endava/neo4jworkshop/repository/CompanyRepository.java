package com.endava.neo4jworkshop.repository;

import com.endava.neo4jworkshop.model.Company;
import com.endava.neo4jworkshop.queryresult.FriendsOfEmployeesCompany;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyRepository extends GraphNodeRepository<Company> {

    @Query("MATCH (c:Company)-[:EMPLOYED]->(u:User)-[:FRIENDS_WITH]-(u2:User)<-[:EMPLOYED]-(c2:Company) " +
            "WHERE c.uuid <> c2.uuid AND c.name = $0 " +
            "RETURN c2 AS company, count(DISTINCT u2) AS friendsOfEmployees ORDER BY friendsOfEmployees DESC")
    List<FriendsOfEmployeesCompany> findFriendsOfEmployeesCompanies(String companyName);
}
