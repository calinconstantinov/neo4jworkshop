package ro.ucv.ace.neo4jworkshop.repository.queryresult;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.neo4j.annotation.QueryResult;
import ro.ucv.ace.neo4jworkshop.model.Company;

@Getter
@Setter
@QueryResult
@ToString
public class FriendsOfEmployeesCompany {

  private Company company;

  private Integer friendsOfEmployees;
}
