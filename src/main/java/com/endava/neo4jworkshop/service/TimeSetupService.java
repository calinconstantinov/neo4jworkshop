package com.endava.neo4jworkshop.service;

import lombok.RequiredArgsConstructor;
import org.neo4j.ogm.session.Session;
import org.springframework.stereotype.Service;

import java.util.Collections;

/**
 * Inspired from: https://markhneedham.com/blog/2014/04/19/neo4j-cypher-creating-a-time-tree-down-to-the-day/
 */
@Service
@RequiredArgsConstructor
public class TimeSetupService {

    private final Session session;

    public void setupTime() {
        String timeNodesCreationQuery =
                "WITH range(2019, 2021) AS years, range(1, 12) AS months, range(0, 23) AS hours " +
                        "FOREACH(year IN years | " +
                        "  MERGE (y:Year {value: year, uuid: toString(year)}) " +
                        "  FOREACH(month IN months | " +
                        "    CREATE (m:Month {value: month}) " +
                        "    MERGE (y)-[:HAS_MONTH]->(m) " +
                        "    FOREACH(day IN (CASE " +
                        "                      WHEN month IN [1, 3, 5, 7, 8, 10, 12] THEN range(1, 31) " +
                        "                      WHEN month = 2 THEN " +
                        "                        CASE " +
                        "                          WHEN year % 4 <> 0 THEN range(1,28) " +
                        "                          WHEN year % 100 <> 0 THEN range(1,29) " +
                        "                          WHEN year % 400 = 0 THEN range(1,29) " +
                        "                          ELSE range(1, 28) " +
                        "                        END " +
                        "                      ELSE range(1, 30) " +
                        "                    END) |   " +
                        "      CREATE (d:Day {value: day}) " +
                        "      MERGE (m)-[:HAS_DAY]->(d) " +
                        "      FOREACH(hour IN hours | " +
                        "        CREATE (h:Hour {value: hour}) " +
                        "        MERGE (d)-[:HAS_HOUR]->(h) " +
                        "      ) " +
                        "    ) " +
                        "  ) " +
                        ");";
        session.query(timeNodesCreationQuery, Collections.emptyMap());

        String monthUuidPopulatingQuery =
                "MATCH (y:Year)-[:HAS_MONTH]->(m:Month) " +
                        "WITH y, m, " +
                        "(CASE " +
                        "   WHEN m.value < 10 THEN y.uuid + '0' + m.value " +
                        "   ELSE y.uuid + m.value END) " +
                        "as uuid " +
                        "SET m.uuid = uuid";
        session.query(monthUuidPopulatingQuery, Collections.emptyMap());

        String dayUuidPopulatingQuery =
                "MATCH (m:Month)-[:HAS_DAY]->(d:Day) " +
                        "WITH m, d, " +
                        "(CASE " +
                        "   WHEN d.value < 10 THEN m.uuid + '0' + d.value " +
                        "   ELSE m.uuid + d.value END) " +
                        "as uuid " +
                        "SET d.uuid = uuid";
        session.query(dayUuidPopulatingQuery, Collections.emptyMap());

        String hourUuidPopulatingQuery =
                "MATCH (d:Day)-[:HAS_HOUR]->(h:Hour) " +
                        "WITH d, h, " +
                        "(CASE " +
                        "   WHEN h.value < 10 THEN d.uuid + '0' + h.value " +
                        "   ELSE d.uuid + h.value END) " +
                        "as uuid " +
                        "SET h.uuid = uuid";
        session.query(hourUuidPopulatingQuery, Collections.emptyMap());

        String linkYearsQuery =
                "MATCH (year:Year) " +
                        "WITH year " +
                        "ORDER BY year.value " +
                        "WITH collect(year) AS years " +
                        "FOREACH(i IN range(0, size(years) - 2) | " +
                        "    FOREACH(year IN [years[i]] | " +
                        "        FOREACH(nextYear IN [years[i + 1]] | " +
                        "            MERGE (year)-[:NEXT_YEAR]->(nextYear) " +
                        "        ) " +
                        "    ) " +
                        ");";
        session.query(linkYearsQuery, Collections.emptyMap());

        String linkMonthsQuery =
                "MATCH (year:Year)-[:HAS_MONTH]->(month:Month) " +
                        "WITH year, month " +
                        "ORDER BY year.value, month.value " +
                        "WITH collect(month) AS months " +
                        "FOREACH(i IN range(0, size(months) - 2) | " +
                        "    FOREACH(month IN [months[i]] | " +
                        "        FOREACH(nextMonth IN [months[i + 1]] | " +
                        "            MERGE (month)-[:NEXT_MONTH]->(nextMonth) " +
                        "        ) " +
                        "    ) " +
                        ");";
        session.query(linkMonthsQuery, Collections.emptyMap());

        String linkDaysQuery =
                "MATCH (year:Year)-[:HAS_MONTH]->(month:Month)-[:HAS_DAY]->(day:Day) " +
                        "WITH year, month, day " +
                        "ORDER BY year.value, month.value, day.value " +
                        "WITH collect(day) AS days " +
                        "FOREACH(i IN range(0, size(days) - 2) | " +
                        "    FOREACH(day IN [days[i]] | " +
                        "        FOREACH(nextDay IN [days[i + 1]] | " +
                        "            MERGE (day)-[:NEXT_DAY]->(nextDay) " +
                        "        ) " +
                        "    ) " +
                        ");";
        session.query(linkDaysQuery, Collections.emptyMap());

        String linkHoursQuery =
                "MATCH (year:Year)-[:HAS_MONTH]->(month:Month)-[:HAS_DAY]->(day:Day)-[:HAS_HOUR]->(hour:Hour) " +
                        "WITH year, month, day, hour " +
                        "ORDER BY year.value, month.value, day.value, hour.value " +
                        "WITH collect(hour) AS hours " +
                        "FOREACH(i IN range(0, size(hours) - 2) | " +
                        "    FOREACH(hour IN [hours[i]] | " +
                        "        FOREACH(nextHour IN [hours[i + 1]] | " +
                        "            MERGE (hour)-[:NEXT_HOUR]->(nextHour) " +
                        "        ) " +
                        "    ) " +
                        ");";
        session.query(linkHoursQuery, Collections.emptyMap());

        String weekdaysCreationQuery =
                "CREATE (:Weekday {value: 1, uuid: 'MONDAY'}) " +
                        "CREATE (:Weekday {value: 2, uuid: 'TUESDAY'}) " +
                        "CREATE (:Weekday {value: 3, uuid: 'WEDNESDAY'}) " +
                        "CREATE (:Weekday {value: 4, uuid: 'THURSDAY'}) " +
                        "CREATE (:Weekday {value: 5, uuid: 'FRIDAY'}) " +
                        "CREATE (:Weekday {value: 6, uuid: 'SATURDAY'}) " +
                        "CREATE (:Weekday {value: 7, uuid: 'SUNDAY'});";
        session.query(weekdaysCreationQuery, Collections.emptyMap());

        String linkWeekdaysQuery =
                "MATCH(weekday:Weekday) " +
                        "WITH weekday " +
                        "ORDER BY weekday.value " +
                        "WITH collect(weekday) AS weekdays " +
                        "FOREACH(i IN range(0, size(weekdays) - 2) | " +
                        "    FOREACH(weekday IN [weekdays[i]] | " +
                        "        FOREACH(nextWeekday IN [weekdays[i + 1]] | " +
                        "            MERGE (weekday)-[:NEXT_WEEKDAY]->(nextWeekday) " +
                        "        ) " +
                        "    ) " +
                        ")" +
                        "WITH weekdays[size(weekdays) - 1] as weekday, weekdays[0] as nextWeekday " +
                        "MERGE (weekday)-[:NEXT_WEEKDAY]->(nextWeekday)";
        session.query(linkWeekdaysQuery, Collections.emptyMap());

        String linkDaysToWeekdaysQuery =
                "MATCH (day:Day) " +
                        "WITH day, apoc.date.fields(day.uuid, 'yyyyMMdd').weekdays as dayOfWeek " +
                        "MATCH (weekday:Weekday) WHERE weekday.value = dayOfWeek " +
                        "WITH day, weekday " +
                        "MERGE (day)-[:IS_WEEKDAY]->(weekday)";
        session.query(linkDaysToWeekdaysQuery, Collections.emptyMap());
    }
}
