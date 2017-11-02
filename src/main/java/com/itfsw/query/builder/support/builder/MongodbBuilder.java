/*
 * Copyright (c) 2017.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.itfsw.query.builder.support.builder;

import com.itfsw.query.builder.exception.ParserNotFoundException;
import com.itfsw.query.builder.support.filter.IRuleFilter;
import com.itfsw.query.builder.support.model.IGroup;
import com.itfsw.query.builder.support.model.IRule;
import com.itfsw.query.builder.support.model.JsonRule;
import com.itfsw.query.builder.support.model.enums.EnumCondition;
import com.itfsw.query.builder.support.model.result.MongodbQueryResult;
import com.itfsw.query.builder.support.parser.AbstractMongodbRuleParser;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

import java.io.IOException;
import java.util.List;
import java.util.Set;

/**
 * ---------------------------------------------------------------------------
 *
 * ---------------------------------------------------------------------------
 * @author: hewei
 * @time:2017/10/31 16:37
 * ---------------------------------------------------------------------------
 */
public class MongodbBuilder extends AbstractBuilder {
    private List<AbstractMongodbRuleParser> parsers;   // rule parser

    /**
     * 构造函数
     * @param filters
     * @param parsers
     */
    public MongodbBuilder(List<IRuleFilter> filters, List<AbstractMongodbRuleParser> parsers) {
        this.filters = filters;
        this.parsers = parsers;
    }

    /**
     * 构建
     * @param query
     * @return
     * @throws IOException
     * @throws ParserNotFoundException
     */
    @Override
    public MongodbQueryResult build(String query) throws IOException, ParserNotFoundException {
        DBObject result = (DBObject) super.build(query);

        // 移除外层
        Set<String> keys = result.keySet();
        if (keys.size() == 1){
            Object item = result.get(keys.iterator().next());
            if (item instanceof BasicDBList && ((BasicDBList) item).size() == 1){
                result = (DBObject) ((BasicDBList) item).get(0);
            }
        }

        return new MongodbQueryResult(query, result);
    }

    /**
     * group
     * @param group
     * @return
     */
    @Override
    protected Object parseGroup(IGroup group) {
        // rules
        BasicDBList operates = new BasicDBList();
        for (JsonRule rule : group.getRules()) {
            operates.add(parse(rule));
        }

        // AND or OR
        BasicDBObject andOrObj = new BasicDBObject();
        andOrObj.append(EnumCondition.AND.equals(group.getCondition()) ? "$and" : "$or", operates);

        // Not
        if (group.getNot() != null && group.getNot()) {
            BasicDBList list = new BasicDBList();
            list.add(andOrObj);
            return new BasicDBObject("$nor", list);
        }
        return andOrObj;
    }

    /**
     * rule
     * @param rule
     * @return
     */
    @Override
    protected Object parseRule(IRule rule) {
        for (AbstractMongodbRuleParser parser : parsers) {
            if (parser.canParse(rule)) {
                return parser.parse(rule);
            }
        }

        throw new ParserNotFoundException("Can't found rule parser for:" + rule);
    }
}
