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

import com.itfsw.query.builder.supports.builder.SqlBuilder;
import com.itfsw.query.builder.supports.filter.IRuleFilter;
import com.itfsw.query.builder.supports.parser.IRuleParser;
import com.itfsw.query.builder.supports.parser.sql.*;

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
public class SqlQueryBuilderFactory {
    protected List<IRuleFilter> filters;    // filters
    protected List<AbstractRuleParser> ruleParsers;   // rule parser
    protected AbstractGroupParser groupParser;

    /**
     * 构造函数
     */
    public SqlQueryBuilderFactory() {
        ruleParsers = new ArrayList<AbstractRuleParser>();
        filters = new ArrayList<IRuleFilter>();

        // -------------------------- filter -----------------------------

        // ---------------------- rule parser ----------------------------
        ruleParsers.add(new BetweenRuleParser());
        ruleParsers.add(new NotBeginsWithRuleParser());
        ruleParsers.add(new LessOrEqualRuleParser());
        ruleParsers.add(new INRuleParser());
        // ---------------------- group parser ---------------------------
        groupParser = new DefaultGroupParser();
    }

    public SqlBuilder builder(String queryStr) {
        List<AbstractRuleParser> parsers = new ArrayList<AbstractRuleParser>();

        for (IRuleParser parser: ruleParsers) {
            if (parser instanceof AbstractRuleParser){
                parsers.add((AbstractRuleParser) parser);
            }
        }

        return new SqlBuilder(queryStr, filters, parsers, groupParser);
    }
}
