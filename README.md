# Neo4j Workshop

Spring Boot Spring Data Java Neo4j Workshop

— _Tested with Neo4j 4.0.11_ —

**Start a local container**  
docker run -p 7474:7474 -p 7687:7687 --name neo4j-apoc -e NEO4J_AUTH=neo4j/admin -e NEO4J_dbms_security_procedures_unrestricted=apoc.\\\* -e NEO4J_apoc_export_file_enabled=true -e NEO4J_apoc_import_file_enabled=true -e NEO4J_apoc_import_file_use__neo4j__config=true -e NEO4JLABS_PLUGINS=\[\"apoc\"\] neo4j:4.0.11

**Database setup**
1. _GraphML import_  
CALL apoc.import.graphml('https://raw.githubusercontent.com/calinconstantinov/neo4jworkshop/develop/src/main/resources/db/sample.graphml', {readLabels: true})

1. _Cypher import (schema)_  
Copy the one in: 
https://raw.githubusercontent.com/calinconstantinov/neo4jworkshop/develop/src/main/resources/db/schema.cyp

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

**GRAph Style Sheet customization**
1. _Export GRASS file_  
:style

1. _Import GRASS file_  
Drag and drop the one in:
https://raw.githubusercontent.com/calinconstantinov/neo4jworkshop/develop/src/main/resources/db/style.grass
1. _Reset GRASS_  
:style reset

**Database export**  
Change 'file' to a locally writable file.

1. _Export CSV_  
CALL apoc.export.csv.all('file', {}) 

1. _Export Cypher Schema_   
CALL apoc.export.cypher.schema('file', {format:'plain'}) 

1. _Export ALL Cypher_  
CALL apoc.export.cypher.all('file', {format:'plain'}) 

1. _Export GraphML_  
CALL apoc.export.graphml.all('file', {useTypes:true}) 

**Sample queries**
1. _Retrieve a user_  
MATCH (n:User)  
WHERE n.name='Calin'  
RETURN n 

1. _Matching credentials_  
MATCH (n:User)-[:HAS_CREDENTIALS]-(c)  
WHERE n.name='Calin'  
RETURN n, c

1. _Retrieving all users_  
MATCH (n:User)  
RETURN n

1. _Retrieve Calin's friends_  
MATCH (n:User)-[:FRIENDS_WITH]-(friend)  
WHERE n.name = 'Calin'  
RETURN n, friend

1. _Retrieve all friendship pairs once_  
MATCH (n:User)-[:FRIENDS_WITH]->(friend)  
WHERE ID(n) > ID(friend)  
RETURN n, friend
   
1. _Remove duplicate friendship relationships_
MATCH (u1:User)-[friendship:FRIENDS_WITH]->(u2:User) 
WHERE ID(u1) > ID(u2) 
DELETE friendship

1. _Retrieve friendships lists_  
MATCH (n:User)-[:FRIENDS_WITH]-(friend)  
RETURN n.name, collect(friend.name), count(friend) AS numberOfFriends  
ORDER BY numberOfFriends DESC

1. _Include authentication with friendships lists_  
MATCH (a:Authentication)<-[:HAS_AUTHENTICATION]-(n:User)-[:FRIENDS_WITH]-(friend)  
RETURN n.name, count(friend)

1. _Optional matching authentication with friendships lists_  
MATCH (n:User)-[:FRIENDS_WITH]-(friend)  
OPTIONAL MATCH (n)-[]-(a:Authentication)  
RETURN a.email, n.name, count(friend)  

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

1. _Matching comments on Calin's posts_  
MATCH (n:User)  
WHERE n.name = 'Calin'  
WITH n  
MATCH (commenter:User)-[:COMMENTED]-(c:Comment)-[:ON_POST]->(p:Post)-[:POSTED_BY]->(n)  
RETURN n, p, c, commenter

1. _Now match all the way to reactions_   
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

1. _Finally match all social data related to 'Calin'_  
MATCH (user:User)  
WHERE user.name = 'Calin'  
WITH user  
MATCH (user)<-[:POSTED_BY]-(post:Post)<-[:ON_POST]-(comment:Comment)<-[:COMMENTED]-(commenter:User)
OPTIONAL MATCH (comment)<-[:AT_COMMENT]-(reaction:Reaction)-[:OF_TYPE]->(reactionType:ReactionType)  
OPTIONAL MATCH (reaction)<-[:REACTED]-(reacter:User)  
OPTIONAL MATCH (replier:User)-[:COMMENTED]->(reply:Comment)-[:REPLIED_TO]->(comment)  
RETURN user, post, comment, commenter, reaction, reactionType, reacter, reply, replier

1. _But where do Calin's friends work?_  
MATCH (user:User)  
WHERE user.name = 'Calin'  
WITH user  
MATCH (user)-[:FRIENDS_WITH]-(friend)<-[:EMPLOYED]-(company:Company)  
RETURN friend.name, company.name

1. _Match all data_  
MATCH (n)  
RETURN n

**Handling time**
1. _Time data model_  
MATCH (y:Year)-[:HAS_MONTH]->(m:Month)-[:HAS_DAY]->(d:Day)-[:HAS_HOUR]->(h:Hour)   
RETURN y, m, d, h

1. _Ordering time_  
MATCH (y:Year)-[:HAS_MONTH]->(m:Month)-[:HAS_DAY]->(d:Day)-[:HAS_HOUR]->(h:Hour)   
RETURN y.uuid, m.uuid, d.uuid, h.uuid  
ORDER BY y.uuid ASC, m.uuid ASC, d.uuid ASC, h.uuid ASC
 
1. _Get a sequence of hours:_  
MATCH (firstHour:Hour)-[:NEXT_HOUR*..5]->(h)  
WHERE firstHour.uuid = '2021051522'  
WITH firstHour + collect(h) as dayList  
UNWIND dayList as day  
RETURN day

1. _Get a sequence of hours including corresponding days:_  
MATCH (firstHour:Hour)-[:NEXT_HOUR*..5]->(h)  
WHERE firstHour.uuid = '2021051522'  
WITH firstHour + collect(h) as hourList  
UNWIND hourList as hour  
WITH hour
MATCH (hour)<-[:HAS_HOUR]-(day:Day)  
RETURN day, hour  

**Data analysis**
1. _Friendship relationships between companies_  
MATCH (c:Company)-[:EMPLOYED]->(u:User)-[:FRIENDS_WITH]-(u2:User)<-[:EMPLOYED]-(c2:Company)  
WHERE c.uuid <> c2.uuid AND c.name = 'Endava'  
RETURN c2.name as company, count(DISTINCT u2) as friendsOfEmployees  
ORDER BY friendsOfEmployees DESC

1. _Computing Post Power_  
   1. _Adding specific Post Power node_  
MATCH (post:Post)  
WITH collect(post) as posts  
FOREACH (post in posts |  
CREATE (pP:PostPower {postPower: 0})  
MERGE (pP)<-[:HAS_POWER]-(post))

   1. _Computing and attaching Post Power value_  
MATCH (post:Post)-[:POSTED_BY]->(poster:User)  
WITH post, poster  
OPTIONAL MATCH (post)<-[like:LIKES_POST]-(liker:User)  
WHERE poster.uuid <> liker.uuid  
WITH post, poster, count(like) as likes  
OPTIONAL MATCH (post)<-[:ON_POST]-(comment:Comment)<-[:COMMENTED]-(commenter:User)   
WHERE poster.uuid <> commenter.uuid  
WITH post, poster, likes, count(comment) as comments  
OPTIONAL MATCH (post)<-[:ON_POST]-(comment)<-[:AT_COMMENT]-(reaction:Reaction)<-[:REACTED]-(reactor:User)  
WHERE poster.uuid <> reactor.uuid  
WITH post, poster, likes, comments, count(reaction) as reactions  
OPTIONAL MATCH (post)<-[:ON_POST]-(comment)<-[:REPLIED_TO]-(reply:Comment)<-[:COMMENTED]-(replier:User)  
WHERE poster.uuid <> replier.uuid  
WITH post, poster, likes, comments, reactions, count(reply) as replies  
WITH poster.name as posterName, post, likes, comments, reactions, replies, 1 * likes + 2 * reactions + 3 * comments + 4 * replies AS postPower  
MATCH (post)-[:HAS_POWER]->(pp:PostPower)
SET pp.postPower = postPower
RETURN posterName, post.content, likes, comments, reactions, replies, postPower
ORDER BY postPower DESC

1. _BFFs <3_  
MATCH (user:User)  
WHERE user.name = 'Calin'  
WITH user  
MATCH (user)-[:FRIENDS_WITH]-(friend)  
WITH user, friend  
OPTIONAL MATCH (user)<-[:POSTED_BY]-(post:Post)<-[l:LIKES_POST]-(friend)
WITH user, friend, count(l) as likes  
OPTIONAL MATCH (user)-[:COMMENTED]->(comment:Comment)<-[:AT_COMMENT]-(reaction:Reaction)-[:REACTED]-(friend)  
WITH user, friend, likes, count(reaction) as reactions  
OPTIONAL MATCH (user)<-[:POSTED_BY]-(post:Post)<-[:ON_POST]-(comment:Comment)<-[:COMMENTED]-(friend)  
WITH user, friend, likes, reactions, count(comment) as comments, reactions + 2 * likes + 3 * count(comment) as friendshipPower  
WHERE friendshipPower <> 0  
RETURN friend.name, likes, reactions, comments, friendshipPower  
ORDER BY friendshipPower DESC

**Full-text indexes powered by the Apache Lucene**
1. _Create full-text index_  
CALL db.index.fulltext.createNodeIndex("user_names",["User"],["name"]);

1. _Term query example_  
CALL db.index.fulltext.queryNodes("user_names", "Calin Mihai") YIELD node, score 
RETURN node.name, score
   
1. _Fuzzy query example_  
CALL db.index.fulltext.queryNodes("user_names", "clin~") YIELD node, score
RETURN node.name, score
   