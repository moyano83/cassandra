# Concepts:
- Partition Key: A partition Key groups rows together in logically related bundles. Mandatory for any cassandra table.
- Clustering column: Id column(s) used to determine the order of the columns within a partition. Clustering columns are optional.
- Static column: Any column that is declared ``STATIC`` has one value per partition key, it allow rows that share a partition key value to share other data as well. It's illegal to declare a static column in a table with no clustering columns.
- Secondary index: allows efficient searching on low cardinality fields. Single column only and only equality searches are allowed. The syntax is 
``CREATE INDEX ON "table" ("index_name");``

# NOTES

- clustering batch: Multiple write statements can be sent in a single batch, Cassandra guarantees that if any statement in a batch succeeds, all of it will.
- Conditional inserts: provides a way to ensure lightweight transaction in insert statements by adding ``IF NOT EXISTS`` at the end of the statement. The result contains an applied column that tell us if the insert was successful or not, it is also possible to put another condition after the IF, for example ``IF value = 'XXX'``

### Collections
Collections are available in Cassandra, insert operations are upserts.
- Adding values:
  To add a value to a set we use the syntax ``{'value'}`` 
  To add it to a list ``['value']``
- Searching for values:
  To search in a List, we can access the element by ``list_name[0]``
- Deletion 
  To delete a value in a set we can do the following: ``UPDATE "table" SET set_column=set_column -{value}``
  In a list we can do the above with brackets or do it using an index ``DELETE set_column[0] FROM set_index WHERE ...``
- It is possible to put a secondary index in a collection column and search on it using the sintax:
``SELECT * FROM "table" WHERE "index_name" CONTAINS 'value'``
  On a map collection, this allows to search in the values of the map, to create the secondary index on the keys and search on them, simply use:
	
``
CREATE INDEX ON "table" (KEYS "index_name");
SELECT * FROM "table" WHERE "index_name" CONTAINS KEY value';
``
- Limitations with collections:
* Any given collection can contain no more than 64 Kb of data
* You cannot read collections when selecting multiple rows using a ``WHERE…IN`` clause, but it is legal to retrieve collections in range queries.
* 
