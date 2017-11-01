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
import com.itfsw.query.builder.support.model.sql.Operation;
import com.itfsw.query.builder.support.parser.AbstractSqlRuleParser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
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
    protected List<AbstractSqlRuleParser> ruleParsers;   // rule parser
    private Operation result;

    /**
     * 构造函数
     * @param queryStr
     * @param filters
     * @param ruleParsers
     */
    public SqlBuilder(String queryStr, List<IRuleFilter> filters, List<AbstractSqlRuleParser> ruleParsers) {
        this.queryStr = queryStr;
        this.filters = filters;
        this.ruleParsers = ruleParsers;
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
        StringBuffer query = new StringBuffer(result.getOperate());
        query.delete(0, 2);
        query.delete(query.length() - 2, query.length());
        return query.toString();
    }

    /**
     * 获取参数
     * @return
     */
    public List<Object> getParams() {
        return (List<Object>) result.getValue();
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
        StringBuffer operate = new StringBuffer();

        // NOT
        if (group.getNot() != null && group.getNot()) {
            operate.append("( NOT ");
        }

        if (group.getRules().size() > 0) {
            operate.append("( ");
        }

        // rules
        List<Object> params = new ArrayList<Object>();
        for (int i = 0; i < group.getRules().size(); i++) {
            Operation operation = parse(group.getRules().get(i));

            // operate
            operate.append(operation.getOperate());
            if (i < group.getRules().size() - 1) {
                operate.append(EnumCondition.AND.equals(group.getCondition()) ? " AND " : " OR ");
            }
            // params
            if (operation.getHasValue()) {
                if (operation.getValue() instanceof List) {
                    params.addAll((Collection<?>) operation.getValue());
                } else {
                    params.add(operation.getValue());
                }
            }
        }

        if (group.getRules().size() > 0) {
            operate.append(" )");
        }
        if (group.getNot() != null && group.getNot()) {
            operate.append(" )");
        }

        return new Operation(operate, params);
    }

    private Operation parseRule(IRule rule) {
        for (AbstractSqlRuleParser parser : ruleParsers) {
            if (parser.canParse(rule)) {
                return parser.parse(rule);
            }
        }

        throw new ParserNotFoundException("Can't found rule parser for:" + rule + "!");
    }

    private void doFilter(JsonRule rule) {
        for (IRuleFilter filter : filters) {
            filter.doFilter(rule);
        }
    }
}
