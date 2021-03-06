CREATE INDEX ON :Month(value);
CREATE INDEX ON :Day(value);
CREATE INDEX ON :User(name);
CREATE INDEX ON :Hour(value);
CALL db.index.fulltext.createNodeIndex('user_names',['User'],['name']);
CREATE CONSTRAINT ON (node:Year) ASSERT (node.value) IS UNIQUE;
CREATE CONSTRAINT ON (node:Authentication) ASSERT (node.uuid) IS UNIQUE;
CREATE CONSTRAINT ON (node:Weekday) ASSERT (node.value) IS UNIQUE;
CREATE CONSTRAINT ON (node:ReactionType) ASSERT (node.name) IS UNIQUE;
CREATE CONSTRAINT ON (node:Day) ASSERT (node.uuid) IS UNIQUE;
CREATE CONSTRAINT ON (node:Month) ASSERT (node.uuid) IS UNIQUE;
CREATE CONSTRAINT ON (node:Hour) ASSERT (node.uuid) IS UNIQUE;
CREATE CONSTRAINT ON (node:ReactionType) ASSERT (node.uuid) IS UNIQUE;
CREATE CONSTRAINT ON (node:Comment) ASSERT (node.uuid) IS UNIQUE;
CREATE CONSTRAINT ON (node:Post) ASSERT (node.uuid) IS UNIQUE;
CREATE CONSTRAINT ON (node:User) ASSERT (node.uuid) IS UNIQUE;
CREATE CONSTRAINT ON (node:Reaction) ASSERT (node.uuid) IS UNIQUE;
CREATE CONSTRAINT ON (node:Company) ASSERT (node.uuid) IS UNIQUE;
CREATE CONSTRAINT ON (node:Year) ASSERT (node.uuid) IS UNIQUE;
CREATE CONSTRAINT ON (node:Weekday) ASSERT (node.uuid) IS UNIQUE;
