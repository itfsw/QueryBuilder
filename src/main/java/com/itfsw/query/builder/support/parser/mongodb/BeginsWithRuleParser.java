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

package com.itfsw.query.builder.support.parser.mongodb;

import com.itfsw.query.builder.support.model.IRule;
import com.itfsw.query.builder.support.model.enums.EnumOperator;
import com.itfsw.query.builder.support.parser.AbstractMongodbRuleParser;
import com.itfsw.query.builder.support.parser.JsonRuleParser;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

import java.util.regex.Pattern;

/**
 * ---------------------------------------------------------------------------
 *
 * ---------------------------------------------------------------------------
 * @author: hewei
 * @time:2017/11/1 16:34
 * ---------------------------------------------------------------------------
 */
public class BeginsWithRuleParser extends AbstractMongodbRuleParser {
    public boolean canParse(IRule rule) {
        return EnumOperator.BEGINS_WITH.equals(rule.getOperator());
    }

    public DBObject parse(IRule rule, JsonRuleParser parser) {
        BasicDBObject operate = new BasicDBObject("$regex", Pattern.compile("^" + rule.getValue()));
        return new BasicDBObject(rule.getField(), operate);
    }
}
