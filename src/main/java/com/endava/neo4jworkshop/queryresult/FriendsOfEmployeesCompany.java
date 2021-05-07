package com.endava.neo4jworkshop.queryresult;

import com.endava.neo4jworkshop.model.Company;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import org.springframework.data.neo4j.annotation.QueryResult;

@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@QueryResult
public class FriendsOfEmployeesCompany {

    Company company;

    Integer friendsOfEmployees;
}
