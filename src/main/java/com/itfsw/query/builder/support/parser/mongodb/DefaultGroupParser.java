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

import com.itfsw.query.builder.support.model.IGroup;
import com.itfsw.query.builder.support.model.JsonRule;
import com.itfsw.query.builder.support.model.enums.EnumCondition;
import com.itfsw.query.builder.support.parser.IGroupParser;
import com.itfsw.query.builder.support.parser.JsonRuleParser;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;

/**
 * ---------------------------------------------------------------------------
 *
 * ---------------------------------------------------------------------------
 * @author: hewei
 * @time:2017/11/9 13:18
 * ---------------------------------------------------------------------------
 */
public class DefaultGroupParser implements IGroupParser {
    /**
     * 解析
     * @param group
     * @param parser
     * @return
     */
    @Override
    public Object parse(IGroup group, JsonRuleParser parser) {
        // rules
        BasicDBList operates = new BasicDBList();
        for (JsonRule jsonRule : group.getRules()) {
            operates.add(parser.parse(jsonRule));
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
}
