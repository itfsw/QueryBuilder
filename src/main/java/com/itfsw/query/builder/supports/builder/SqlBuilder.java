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

package com.itfsw.query.builder.supports.builder;

import com.itfsw.query.builder.exception.ParserNotFoundException;
import com.itfsw.query.builder.supports.filter.IRuleFilter;
import com.itfsw.query.builder.supports.model.IGroup;
import com.itfsw.query.builder.supports.model.IRule;
import com.itfsw.query.builder.supports.model.JsonRule;
import com.itfsw.query.builder.supports.model.sql.Operation;
import com.itfsw.query.builder.supports.parser.AbstractGroupParser;
import com.itfsw.query.builder.supports.parser.AbstractRuleParser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * ---------------------------------------------------------------------------
 * sql 构造
 * ---------------------------------------------------------------------------
 * @author: hewei
 * @time:2017/10/30 15:44
 * ---------------------------------------------------------------------------
 */
public class SqlBuilder extends AbstractBuilder {
    protected String queryStr;   // 查询字符串
    protected List<IRuleFilter> filters;    // filters
    protected List<AbstractRuleParser> ruleParsers;   // rule parser
    protected AbstractGroupParser groupParser;

    private Operation result;   // 结果

    /**
     * 构造函数
     * @param queryStr
     * @param filters
     * @param ruleParsers
     * @param groupParser
     */
    public SqlBuilder(String queryStr, List<IRuleFilter> filters, List<AbstractRuleParser> ruleParsers, AbstractGroupParser groupParser) {
        this.queryStr = queryStr;
        this.filters = filters;
        this.ruleParsers = ruleParsers;
        this.groupParser = groupParser;
    }

    /**
     * 构建
     * @return
     * @throws IOException
     * @throws ParserNotFoundException
     */
    public void build() throws IOException, ParserNotFoundException {
        JsonRule rule = mapper.readValue(queryStr, JsonRule.class);
        result = parse(rule);
    }

    /**
     * 获取查询语句
     * @return
     */
    public String getQuery() {
        return null;
    }


    /**
     * 解析
     * @param rule
     * @return
     */
    private Operation parse(JsonRule rule) {
        // filter
        doFilter(rule);

        // parse
        if (rule.isGroup()) {
            return parseGroup(rule);
        } else {
            return parseRule(rule);
        }
    }

    private Operation parseGroup(IGroup group) {
        List<Operation> operations = new ArrayList<Operation>();
        for (JsonRule item : group.getRules()) {
            operations.add(parse(item));
        }

        if (groupParser.canParse(group)) {
            return groupParser.parse(group, operations);
        } else {
            throw new ParserNotFoundException("Can't found group parser!");
        }
    }

    private Operation parseRule(IRule rule) {
        for (AbstractRuleParser parser : ruleParsers) {
            if (parser.canParse(rule)) {
                return parser.parse(rule);
            }
        }

        throw new ParserNotFoundException("Can't found rule parser!");
    }

    private void doFilter(JsonRule rule) {
        for (IRuleFilter filter : filters) {
            filter.doFilter(rule);
        }
    }
}
