package ro.ucv.ace.neo4jworkshop.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;
import ro.ucv.ace.neo4jworkshop.model.time.Day;

@Repository
public interface DayRepository extends Neo4jRepository<Day, Long> {

    Day findByUuid(String uuid);
}
