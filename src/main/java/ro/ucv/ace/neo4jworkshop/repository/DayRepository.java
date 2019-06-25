package ro.ucv.ace.neo4jworkshop.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import ro.ucv.ace.neo4jworkshop.model.time.Day;

public interface DayRepository extends Neo4jRepository<Day, Long> {

  Day findByUuid(String uuid);
}
