package ro.ucv.ace.neo4jworkshop.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;
import ro.ucv.ace.neo4jworkshop.model.time.Year;

@Repository
public interface YearRepository extends Neo4jRepository<Year, Long> {

    Year findByUuid(String uuid);
}
