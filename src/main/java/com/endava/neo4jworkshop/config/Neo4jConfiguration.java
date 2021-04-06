package com.endava.neo4jworkshop.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.annotation.EnableNeo4jAuditing;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

@Configuration
@EnableNeo4jAuditing
@EnableNeo4jRepositories("com.endava.neo4jworkshop.repository")
@EntityScan({"com.endava.neo4jworkshop.model", "com.endava.neo4jworkshop.queryresult"})
public class Neo4jConfiguration {

}
