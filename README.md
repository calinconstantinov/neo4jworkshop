# neo4jworkshop

Spring Boot Spring Data Java Neo4j Workshop

**(Optional) Start a local container**  
docker run -d -e NEO4J_dbms_security_procedures_unrestricted=apoc.\\\* -e NEO4J_apoc_export_file_enabled=true -e NEO4J_apoc_import_file_enabled=true -e=NEO4J_AUTH=none -e=NEO4J_ACCEPT_LICENSE_AGREEMENT=yes --publish=7474:7474 --publish=7687:7687 discsports/neo4j-apoc

**Sample DB:**
https://gist.github.com/calinconstantinov/2324c8fed78774ef28b7cc9ab7bdaeaa

**Sample Queries**
1. _Retrieve a user_  
MATCH (n:User)  
WHERE n.name='Calin'  
RETURN n 

2. _Matching credentials_  
MATCH (n:User)-[:HAS_CREDENTIALS]-(c)  
WHERE n.name='Calin'  
RETURN n, c

3. _Match all users_  
MATCH (n:User)  
RETURN n

4. _Get Calin's friends_  
MATCH (n:User)-[:FRIENDS_WITH]-(friend)  
WHERE n.name = 'Calin'  
RETURN n, friend

5. _Get all friendships once_  
MATCH (n:User)-[:FRIENDS_WITH]-(friend)  
WHERE n.uuid > friend.uuid  
RETURN n, friend

6. _Get friendships lists_  
MATCH (n:User)-[:FRIENDS_WITH]-(friend)  
RETURN n, collect(friend.name), count(friend) AS numberOfFriends  
ORDER BY numberOfFriends DESC

7. _Include credentials with friendships lists_  
MATCH (c:Credentials)<-[:HAS_CREDENTIALS]-(n:User)-[:FRIENDS_WITH]-(friend)  
RETURN n, count(friend)

8. _Optional matching credentials with friendships lists_  
MATCH (n:User)-[:FRIENDS_WITH]-(friend)  
OPTIONAL MATCH (n)-[]-(c:Credentials)  
RETURN c, n, count(friend)  

20. _Get a sequence of days:_  
MATCH (firstHour:Hour)-[:NEXT_HOUR*..5]->(h)  
WHERE firstHour.uuid = '2019031500'  
WITH firstHour + collect(h) as dayList  
UNWIND dayList as day  
RETURN day
