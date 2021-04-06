package com.endava.neo4jworkshop.repository.time;

import com.endava.neo4jworkshop.model.time.Hour;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface HourRepository extends Neo4jRepository<Hour, Long> {

    Hour findByUuid(String uuid);
}
