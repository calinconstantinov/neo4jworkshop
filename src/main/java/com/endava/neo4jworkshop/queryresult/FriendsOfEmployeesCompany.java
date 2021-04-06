package com.endava.neo4jworkshop.queryresult;

import com.endava.neo4jworkshop.model.Company;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.neo4j.annotation.QueryResult;

@Getter
@Setter
@QueryResult
@ToString
public class FriendsOfEmployeesCompany {

  private Company company;

  private Integer friendsOfEmployees;
}
