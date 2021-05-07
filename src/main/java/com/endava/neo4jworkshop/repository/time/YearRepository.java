package com.endava.neo4jworkshop.repository.time;

import com.endava.neo4jworkshop.model.time.Year;
import com.endava.neo4jworkshop.repository.GraphNodeRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface YearRepository extends GraphNodeRepository<Year> {

}
