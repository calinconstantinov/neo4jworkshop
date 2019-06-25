package ro.ucv.ace.neo4jworkshop.service;

import org.neo4j.ogm.session.Session;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class TimeSetupService {

  private final Session session;

  public TimeSetupService(Session session) {
    this.session = session;
  }

  //inspired from: https://markhneedham.com/blog/2014/04/19/neo4j-cypher-creating-a-time-tree-down-to-the-day/
  public void setupTime() {
    String buildTimeNodes = "WITH range(2019, 2019) AS years, range(3,3) AS months, range(0,23) AS hours " +
        "FOREACH(year IN years | " +
        "  MERGE (y:Year {year: year, uuid: toString(year)}) " +
        "  FOREACH(month IN months | " +
        "    CREATE (m:Month {month: month}) " +
        "    MERGE (y)-[:HAS_MONTH]->(m) " +
        "    FOREACH(day IN range(15,16) | " +
        "      CREATE (d:Day {day: day}) " +
        "      MERGE (m)-[:HAS_DAY]->(d) " +
        "      FOREACH(hour IN hours | " +
        "        CREATE (h:Hour {hour: hour}) " +
        "        MERGE (d)-[:HAS_HOUR]->(h) " +
        "      ) " +
        "    ) " +
        "  ) " +
        ");";
    session.query(buildTimeNodes, Collections.emptyMap());

    String linkDays = "MATCH (year:Year)-[:HAS_MONTH]->(month:Month)-[:HAS_DAY]->(day:Day) " +
        "WITH year,month,day " +
        "ORDER BY year.year, month.month, day.day " +
        "WITH collect(day) AS days " +
        "FOREACH(i IN range(0, size(days) - 2) | " +
        "    FOREACH(day1 IN [days[i]] | " +
        "        FOREACH(day2 IN [days[i + 1]] | " +
        "            MERGE (day1)-[:NEXT_DAY]->(day2) " +
        "        ) " +
        "    ) " +
        ");";
    session.query(linkDays, Collections.emptyMap());

    String linkHours = "MATCH (year:Year)-[:HAS_MONTH]->(month:Month)-[:HAS_DAY]->(day:Day)-[:HAS_HOUR]->(hour:Hour) " +
        "WITH year, month, day, hour " +
        "ORDER BY year.year, month.month, day.day, hour.hour " +
        "WITH collect(hour) AS hours " +
        "FOREACH(i IN range(0, size(hours) - 2) | " +
        "    FOREACH(hour1 IN [hours[i]] | " +
        "        FOREACH(hour2 IN [hours[i + 1]] | " +
        "            MERGE (hour1)-[:NEXT_HOUR]->(hour2) " +
        "        ) " +
        "    ) " +
        ");";
    session.query(linkHours, Collections.emptyMap());

    String monthUuid = "MATCH (y:Year)-[:HAS_MONTH]->(m:Month) " +
        "WITH y, m, " +
        "(CASE " +
        "   WHEN m.month < 10 THEN y.uuid + '0' + m.month " +
        "   ELSE y.uuid + m.month END) " +
        "as uuid " +
        "SET m.uuid = uuid";
    session.query(monthUuid, Collections.emptyMap());

    String dayUuid = "MATCH (m:Month)-[:HAS_DAY]->(d:Day) " +
        "WITH m, d, " +
        "(CASE " +
        "   WHEN d.day < 10 THEN m.uuid + '0' + d.day " +
        "   ELSE m.uuid + d.day END) " +
        "as uuid " +
        "SET d.uuid = uuid";
    session.query(dayUuid, Collections.emptyMap());

    String hourUuid = "MATCH (d:Day)-[:HAS_HOUR]->(h:Hour) " +
        "WITH d, h, " +
        "(CASE " +
        "   WHEN h.hour < 10 THEN d.uuid + '0' + h.hour " +
        "   ELSE d.uuid + h.hour END) " +
        "as uuid " +
        "SET h.uuid = uuid";
    session.query(hourUuid, Collections.emptyMap());
  }
}