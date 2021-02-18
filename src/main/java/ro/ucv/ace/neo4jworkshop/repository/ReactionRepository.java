package ro.ucv.ace.neo4jworkshop.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;
import ro.ucv.ace.neo4jworkshop.model.Reaction;

import java.util.Set;

@Repository
public interface ReactionRepository extends Neo4jRepository<Reaction, Long> {

    Set<Reaction> findByReactionType_Name(String reactionTypeName);

    Set<Reaction> findByComment_Uuid(String commendUuid);
}
