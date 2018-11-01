### Document  
[中文](README.md)&nbsp;&nbsp;|&nbsp;&nbsp;[English](README-EN.md)  

# QueryBuilder
An Java Backend for [jQuery QueryBuilder](https://github.com/mistic100/jQuery-QueryBuilder)。
    
[![jQuery QueryBuilder](screenshot.png)](http://querybuilder.js.org/index.html)

---------------------------------------
Maven引用：  
```xml
<dependency>
  <groupId>com.itfsw</groupId>
  <artifactId>QueryBuilder</artifactId>
  <version>1.0.4</version>
</dependency>
```
---------------------------------------
#### Basic Usage
```java
public class Test {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private MongoTemplate mongoTemplate;

    public void test() throws IOException {
        String json = "{\"condition\":\"OR\",\"rules\":[{\"id\":\"name\",\"field\":\"username\",\"type\":\"string\",\"input\":\"text\",\"operator\":\"equal\",\"value\":\"Mistic\"}],\"not\":false,\"valid\":true}";

        // ----------------------------------------- SQL -----------------------------------------
        // get SqlBuilder
        SqlQueryBuilderFactory sqlQueryBuilderFactory = new SqlQueryBuilderFactory();
        SqlBuilder sqlBuilder = sqlQueryBuilderFactory.builder();

        // build query
        SqlQueryResult sqlQueryResult = sqlBuilder.build(json);

        // execute
        jdbcTemplate.query(new StringBuffer("SELECT * FROM `user` WHERE ").append(sqlQueryResult.getQuery()).toString(), sqlQueryResult.getParams().toArray(), rs -> {
            System.out.println(rs.getString("username"));
        });

        // ----------------------------------------- Mongodb -----------------------------------------
        // get MongodbBuilder
        MongodbQueryBuilderFactory mongodbQueryBuilderFactory = new MongodbQueryBuilderFactory();
        MongodbBuilder mongodbBuilder = mongodbQueryBuilderFactory.builder();

        // build query
        MongodbQueryResult mongodbQueryResult = mongodbBuilder.build(json);

        // execute
        DBCursor cursor = mongoTemplate.getCollection("user").find(mongodbQueryResult.getQuery());
        while (cursor.hasNext()){
            System.out.println(cursor.next().get("username"));
        }
    }
}
```
#### Advanced
The project provides custom RuleFilter, RuleParser, and GroupParser features. In the RuleFilter, the validation of rule and data filtering are performed, while RuleParser and GroupParser can be used to resolve the custom rules.
Custom RuleFilter and RuleParser can be replaced with factory corresponding addXXX, AddXXXBefore, AddXXXAt, and AddXXXAfter.

--------------------------------------------------------------------------------------------------
##### 1.Custom RuleFilter  
Custom RuleFilter Class must be implement[IRuleFilter](src/main/java/com/itfsw/query/builder/support/filter/IRuleFilter.java)  
Provide：
* [ValidateFilter](src/main/java/com/itfsw/query/builder/support/filter/ValidateFilter.java)；
* [DefaultValueConvertFilter](src/main/java/com/itfsw/query/builder/support/filter/DefaultValueConvertFilter.java)；
* [DatetimeConvertFilter](src/main/java/com/itfsw/query/builder/support/filter/DatetimeConvertFilter.java)；
* [SqlInjectionAttackFilter](src/main/java/com/itfsw/query/builder/support/filter/SqlInjectionAttackFilter.java)；

##### 2.Custom RuleParser
Depending on the database，Custom RuleParser Class must be implement[AbstractMongodbRuleParser](src/main/java/com/itfsw/query/builder/support/parser/AbstractMongodbRuleParser.java) or [AbstractSqlRuleParser](src/main/java/com/itfsw/query/builder/support/parser/AbstractSqlRuleParser.java)  

Provide：
>operator("equal","not_equal","in","not_in","less","less_or_equal","greater","greater_or_equal","between","not_between","begins_with","not_begins_with","contains","not_contains","ends_with","not_ends_with","is_empty","is_not_empty","is_null","is_not_null")。  

##### 3.Custom GroupParser
Custom RuleFilter Class must be implement[IGroupParser](src/main/java/com/itfsw/query/builder/support/parser/IGroupParser.java)   

Provide：
>default implements has provide "AND,OR,NOT"
