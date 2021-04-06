package com.endava.neo4jworkshop.repository.time;

import com.endava.neo4jworkshop.model.time.Day;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DayRepository extends Neo4jRepository<Day, Long> {

    Day findByUuid(String uuid);
}
