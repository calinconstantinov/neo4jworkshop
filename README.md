# neo4jworkshop

Spring Boot Spring Data Java Neo4j Workshop

**Start a local container**  
docker run -e NEO4J_dbms_security_procedures_unrestricted=apoc.\\\* -e NEO4J_apoc_export_file_enabled=true -e NEO4J_apoc_import_file_enabled=true -e=NEO4J_AUTH=none -e=NEO4J_ACCEPT_LICENSE_AGREEMENT=yes --publish=7474:7474 --publish=7687:7687 discsports/neo4j-apoc

**Sample DB**
https://raw.githubusercontent.com/calinconstantinov/neo4jworkshop/develop/src/main/resources/db/sample.graphml

**Database setup**
1. _GraphML import_  
CALL apoc.import.graphml('https://raw.githubusercontent.com/calinconstantinov/neo4jworkshop/develop/src/main/resources/db/sample.graphml', {readLabels: true, batchSize: 10000, storeNodeIds: false}) 

1. _Schema: Constraints_  
CALL db.constraints

1. _Schema: Indexes_  
CALL db.indexes

1. _Schema: Meta-Graph_  
CALL apoc.meta.graph 

1. _APOC Schema_  
CALL apoc.meta.schema

1. _Delete all data_  
MATCH (n)   
DETACH DELETE n 

1. _Delete schema_  
CALL apoc.schema.assert({},{}) 

**Sample Queries**
1. _Retrieve a user_  
MATCH (n:User)  
WHERE n.name='Calin'  
RETURN n 

1. _Matching credentials_  
MATCH (n:User)-[:HAS_CREDENTIALS]-(c)  
WHERE n.name='Calin'  
RETURN n, c

1. _Match all users_  
MATCH (n:User)  
RETURN n

1. _Get Calin's friends_  
MATCH (n:User)-[:FRIENDS_WITH]-(friend)  
WHERE n.name = 'Calin'  
RETURN n, friend

1. _Get all friendships once_  
MATCH (n:User)-[:FRIENDS_WITH]-(friend)  
WHERE n.uuid > friend.uuid  
RETURN n, friend

1. _Get friendships lists_  
MATCH (n:User)-[:FRIENDS_WITH]-(friend)  
RETURN n, collect(friend.name), count(friend) AS numberOfFriends  
ORDER BY numberOfFriends DESC

1. _Include credentials with friendships lists_  
MATCH (c:Credentials)<-[:HAS_CREDENTIALS]-(n:User)-[:FRIENDS_WITH]-(friend)  
RETURN n, count(friend)

1. _Optional matching credentials with friendships lists_  
MATCH (n:User)-[:FRIENDS_WITH]-(friend)  
OPTIONAL MATCH (n)-[]-(c:Credentials)  
RETURN c, n, count(friend)  

1. _Matching Calin's posts_  
MATCH (n:User)  
WHERE n.name = 'Calin'  
WITH n  
MATCH (p:Post)-[:POSTED_BY]->(n)  
RETURN n, p

1. _Matching Calin's posts' likes_  
MATCH (n:User)<-[:POSTED_BY]-(p:Post)<-[like:LIKES_POST]-(liker:User)  
WHERE n.name = 'Calin'  
RETURN n, p, like, liker

1. _Matching non-narcissistic likes_  
MATCH (n:User)<-[:POSTED_BY]-(p:Post)<-[like:LIKES_POST]-(liker:User)  
WHERE n.name = 'Calin' AND n.uuid <> liker.uuid  
RETURN n.name, p.content, like.timestamp, liker.name

1. _Match comments on Calin's posts_  
MATCH (n:User)  
WHERE n.name = 'Calin'  
WITH n  
MATCH (commenter:User)-[:COMMENTED]-(c:Comment)-[:ON_POST]->(p:Post)-[:POSTED_BY]->(n)  
RETURN n, p, c, commenter

1. _Match all the way to reactions_   
MATCH (user:User)  
WHERE user.name = 'Calin'  
WITH user  
MATCH (user)<-[:POSTED_BY]-(post:Post)<-[:ON_POST]-(comment:Comment)<-[:COMMENTED]-(commenter:User),  
(comment)<-[:AT_COMMENT]-(reaction:Reaction)-[:OF_TYPE]->(reactionType:ReactionType),  
(reaction)<-[:REACTED]-(reacter:User)  
RETURN user, post, comment, commenter, reaction, reactionType, reacter

1. _Debugging an incorrect query_  
PROFILE  
MATCH (user:User)    
WHERE user.name = 'Calin'  
WITH user  
MATCH (user)<-[:POSTED_BY]-(post:Post)<-[:ON_POST]-(comment:Comment)<-[:COMMENTED]-(commenter:User),  
(comment)<-[:AT_COMMENT]-(reaction:Reaction)-[:OF_TYPE]->(reactionType:ReactionType),  
(r)<-[:REACTED]-(reacter:User)  
RETURN user, post, comment, commenter, reaction, reactionType, reacter

1. _Include comments with no reactions_  
MATCH (user:User)  
WHERE user.name = 'Calin'  
WITH user  
MATCH (user)<-[:POSTED_BY]-(post:Post)<-[:ON_POST]-(comment:Comment)<-[:COMMENTED]-(commenter:User)
OPTIONAL MATCH (comment)<-[:AT_COMMENT]-(reaction:Reaction)-[:OF_TYPE]->(reactionType:ReactionType),  
(reaction)<-[:REACTED]-(reacter:User)  
RETURN user, post, comment, commenter, reaction, reactionType, reacter

1. _Match all social data related to 'Calin'  
MATCH (user:User)  
WHERE user.name = 'Calin'  
WITH user  
MATCH (user)<-[:POSTED_BY]-(post:Post)<-[:ON_POST]-(comment:Comment)<-[:COMMENTED]-(commenter:User)
OPTIONAL MATCH (comment)<-[:AT_COMMENT]-(reaction:Reaction)-[:OF_TYPE]->(reactionType:ReactionType)  
OPTIONAL MATCH (reaction)<-[:REACTED]-(reacter:User)  
OPTIONAL MATCH (replier:User)-[:COMMENTED]->(reply:Comment)-[:REPLIED_TO]->(comment)  
RETURN user, post, comment, commenter, reaction, reactionType, reacter, reply, replier

1. Where do Calin's friends work?  
MATCH (user:User)  
WHERE user.name = 'Calin'  
WITH user  
MATCH (user)-[:FRIENDS_WITH]-(friend)<-[:EMPLOYED]-(company:Company)  
RETURN friend.name, company.name

1. Match all data  
MATCH (n)  
RETURN n

**Handling time**
1. _Time data model_  
MATCH (y:Year)-[:HAS_MONTH]->(m:Month)-[:HAS_DAY]->(d:Day)-[:HAS_HOUR]->(h:Hour)   
RETURN y, m, d, h

1. _Ordering time_  
MATCH (y:Year)-[:HAS_MONTH]->(m:Month)-[:HAS_DAY]->(d:Day)-[:HAS_HOUR]->(h:Hour)   
RETURN y.year, m.month, d.day, h.hour  
ORDER BY y.year ASC, m.month ASC, d.day ASC, h.hour ASC
 
1. _Get a sequence of hours:_  
MATCH (firstHour:Hour)-[:NEXT_HOUR*..5]->(h)  
WHERE firstHour.uuid = '2019031522'  
WITH firstHour + collect(h) as dayList  
UNWIND dayList as day  
RETURN day

1. _Get a sequence of days including days:_  
MATCH (firstHour:Hour)-[:NEXT_HOUR*..5]->(h)  
WHERE firstHour.uuid = '2019031522'  
WITH firstHour + collect(h) as hourList  
UNWIND hourList as hour  
WITH hour
MATCH (hour)<-[:HAS_HOUR]-(day:Day)  
RETURN day, hour  

**Data analysis**
