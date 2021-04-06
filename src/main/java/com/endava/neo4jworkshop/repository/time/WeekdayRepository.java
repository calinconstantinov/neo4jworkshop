package com.endava.neo4jworkshop.repository.time;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;
import com.endava.neo4jworkshop.model.time.Weekday;

@Repository
public interface WeekdayRepository extends Neo4jRepository<Weekday, Long> {

    Weekday findByUuid(String uuid);
}
