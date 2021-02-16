package ro.ucv.ace.neo4jworkshop.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import ro.ucv.ace.neo4jworkshop.model.time.Hour;

public interface HourRepository extends Neo4jRepository<Hour, Long> {

    Hour findByUuid(String uuid);
}
