# QueryBuilder
这个是[jQuery QueryBuilder](https://github.com/mistic100/jQuery-QueryBuilder)插件的JAVA版本后台实现。
    
[![jQuery QueryBuilder](screenshot.png)](http://querybuilder.js.org/index.html)

### Document  
[中文](README.md)&nbsp;&nbsp;|&nbsp;&nbsp;[English](README-EN.md)  

---------------------------------------
Maven引用：  
```xml
<dependency>
  <groupId>com.itfsw</groupId>
  <artifactId>QueryBuilder</artifactId>
  <version>1.0.0</version>
</dependency>
```
---------------------------------------
#### 基础使用
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
            System.out.println(cursor.curr().get("username"));
        }
    }
}
```
#### 进阶使用
项目提供了自定义RuleFilter和RuleParser功能。其中RuleFilter进行对rule的验证和数据过滤等工作，而RuleParser则可以进行自定义规则的解析。
自定义RuleFilter和RuleParser使用Factory相应的addXXX、addXXXBefore、addXXXAt、addXXXAfter进行替换添加。

--------------------------------------------------------------------------------------------------
##### 1.自定义RuleFilter  
自定义类实现[IRuleFilter](src/main/java/com/itfsw/query/builder/support/filter/IRuleFilter.java)接口  
系统自带：
* [ValidateFilter](src/main/java/com/itfsw/query/builder/support/filter/ValidateFilter.java)：提供基础的Rule验证，如一些必要条件和数据验证；
* [DefaultValueConvertFilter](src/main/java/com/itfsw/query/builder/support/filter/DefaultValueConvertFilter.java)：一些基础数据类型的转换；
* [DatetimeConvertFilter](src/main/java/com/itfsw/query/builder/support/filter/DatetimeConvertFilter.java)：日期类型的数据转换，建议用户替换默认配置（"yyyy-MM-dd HH:mm:ss"）；
* [SqlInjectionAttackFilter](src/main/java/com/itfsw/query/builder/support/filter/SqlInjectionAttackFilter.java)：基础sql注入拦截；

##### 2.自定义RuleParser
根据数据库的不同，分别实现[AbstractMongodbRuleParser](src/main/java/com/itfsw/query/builder/support/parser/AbstractMongodbRuleParser.java)或者[AbstractSqlRuleParser](src/main/java/com/itfsw/query/builder/support/parser/AbstractSqlRuleParser.java)  

系统自带：
>operator("equal","not_equal","in","not_in","less","less_or_equal","greater","greater_or_equal","between","not_between","begins_with","not_begins_with","contains","not_contains","ends_with","not_ends_with","is_empty","is_not_empty","is_null","is_not_null")对应的Sql和Mongodb实现。