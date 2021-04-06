package com.endava.neo4jworkshop.repository.time;

import com.endava.neo4jworkshop.model.time.Year;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface YearRepository extends Neo4jRepository<Year, Long> {

    Year findByUuid(String uuid);
}
