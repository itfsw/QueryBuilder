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

package com.itfsw.query.builder;

import com.itfsw.query.builder.support.builder.SqlBuilder;
import com.itfsw.query.builder.support.parser.AbstractSqlRuleParser;
import com.itfsw.query.builder.support.parser.IRuleParser;
import com.itfsw.query.builder.support.parser.sql.*;

import java.util.ArrayList;
import java.util.List;

/**
 * ---------------------------------------------------------------------------
 *
 * ---------------------------------------------------------------------------
 * @author: hewei
 * @time:2017/10/31 17:03
 * ---------------------------------------------------------------------------
 */
public class SqlQueryBuilderFactory extends AbstractQueryBuilderFactory {

    /**
     * 构造函数
     */
    public SqlQueryBuilderFactory() {
        super();

        // ---------------------- rule parser ----------------------------
        parsers.add(new EqualRuleParser());
        parsers.add(new NotEqualRuleParser());
        parsers.add(new INRuleParser());
        parsers.add(new NotInRuleParser());
        parsers.add(new LessRuleParser());
        parsers.add(new LessOrEqualRuleParser());
        parsers.add(new GreaterRuleParser());
        parsers.add(new GreaterOrEqualRuleParser());
        parsers.add(new BetweenRuleParser());
        parsers.add(new NotBetweenRuleParser());
        parsers.add(new BeginsWithRuleParser());
        parsers.add(new NotBeginsWithRuleParser());
        parsers.add(new ContainsRuleParser());
        parsers.add(new NotContainsRuleParser());
        parsers.add(new EndsWithRuleParser());
        parsers.add(new NotEndsWithRuleParser());
        parsers.add(new IsEmptyRuleParser());
        parsers.add(new IsNotEmptyRuleParser());
        parsers.add(new IsNullRuleParser());
        parsers.add(new IsNotNullRuleParser());

    }

    /**
     * 获取builder
     * @return
     */
    public SqlBuilder builder() {
        List<AbstractSqlRuleParser> sqlRuleParsers = new ArrayList<>();
        for (IRuleParser ruleParser : parsers){
            if (ruleParser instanceof AbstractSqlRuleParser){
                sqlRuleParsers.add((AbstractSqlRuleParser) ruleParser);
            }
        }

        return new SqlBuilder(filters, sqlRuleParsers);
    }
}
