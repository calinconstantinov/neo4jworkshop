package ro.ucv.ace.neo4jworkshop.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import ro.ucv.ace.neo4jworkshop.model.Reaction;

import java.util.Set;

public interface ReactionRepository extends Neo4jRepository<Reaction, Long> {

  Set<Reaction> findByType_Name(String typeName);

  Set<Reaction> findByComment_Uuid(Integer commendUuid);
}
