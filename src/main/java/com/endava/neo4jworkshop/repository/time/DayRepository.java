package com.endava.neo4jworkshop.repository.time;

import com.endava.neo4jworkshop.model.time.Day;
import com.endava.neo4jworkshop.repository.GraphNodeRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DayRepository extends GraphNodeRepository<Day> {

}
