# neo4jworkshop
Spring Boot Spring Data Neo4j Workshop

Sample DB:
https://gist.github.com/calinconstantinov/2324c8fed78774ef28b7cc9ab7bdaeaa

**Get a sequence of days:**  
MATCH (firstHour:Hour)-[:NEXT_HOUR*..5]->(h) 
WHERE firstHour.uuid = '2019031500' 
WITH firstHour + collect(h) as dayList
UNWIND dayList as day
RETURN day