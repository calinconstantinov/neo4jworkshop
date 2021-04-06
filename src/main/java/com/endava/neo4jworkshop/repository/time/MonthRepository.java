package com.endava.neo4jworkshop.repository.time;

import com.endava.neo4jworkshop.model.time.Month;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface MonthRepository extends Neo4jRepository<Month, Long> {

    Month findByUuid(String uuid);
}
