package com.endava.neo4jworkshop.repository.time;

import com.endava.neo4jworkshop.model.time.Weekday;
import com.endava.neo4jworkshop.repository.GraphNodeRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeekdayRepository extends GraphNodeRepository<Weekday> {

}
