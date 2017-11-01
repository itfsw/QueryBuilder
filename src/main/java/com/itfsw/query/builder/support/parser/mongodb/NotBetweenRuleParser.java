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
import com.mongodb.BasicDBObject;

import java.util.List;

/**
 * ---------------------------------------------------------------------------
 *
 * ---------------------------------------------------------------------------
 * @author: hewei
 * @time:2017/11/1 16:32
 * ---------------------------------------------------------------------------
 */
public class NotBetweenRuleParser extends AbstractMongodbRuleParser {
    public boolean canParse(IRule rule) {
        return EnumOperator.NOT_BETWEEN.equals(rule.getOperator());
    }

    public BasicDBObject parse(IRule rule) {
        List<Object> values = (List<Object>) rule.getValue();
        BasicDBObject operate = new BasicDBObject();
        operate.append("$lt", values.get(0));
        operate.append("$gt", values.get(1));
        return new BasicDBObject(rule.getField(), operate);
    }
}
