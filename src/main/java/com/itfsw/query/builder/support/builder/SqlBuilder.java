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
import com.itfsw.query.builder.support.model.result.SqlQueryResult;
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
    private List<AbstractSqlRuleParser> parsers;   // rule parser

    /**
     * 构造函数
     * @param filters
     * @param parsers
     */
    public SqlBuilder(List<IRuleFilter> filters, List<AbstractSqlRuleParser> parsers) {
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
    public SqlQueryResult build(String query) throws IOException, ParserNotFoundException {
        Operation result = (Operation) super.build(query);

        // sql
        StringBuffer sql = new StringBuffer(result.getOperate());
        sql.delete(sql.length() - 2, sql.length());
        sql.delete(0, 2);

        return new SqlQueryResult(sql.toString(), (List<Object>) result.getValue());
    }

    /**
     * group
     * @param group
     * @return
     */
    @Override
    protected Object parseGroup(IGroup group) {
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
            Operation operation = (Operation) parse(group.getRules().get(i));

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

    /**
     * rule
     * @param rule
     * @return
     */
    @Override
    protected Object parseRule(IRule rule) {
        for (AbstractSqlRuleParser parser : parsers) {
            if (parser.canParse(rule)) {
                return parser.parse(rule);
            }
        }

        throw new ParserNotFoundException("Can't found rule parser for:" + rule );
    }
}
