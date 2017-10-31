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

package com.itfsw.query.builder.supports.parser.sql;

import com.itfsw.query.builder.supports.model.IGroup;
import com.itfsw.query.builder.supports.model.enums.EnumCondition;
import com.itfsw.query.builder.supports.model.sql.Operation;

import java.util.List;

/**
 * ---------------------------------------------------------------------------
 *
 * ---------------------------------------------------------------------------
 * @author: hewei
 * @time:2017/10/31 18:21
 * ---------------------------------------------------------------------------
 */
public class DefaultGroupParser extends AbstractGroupParser {

    public boolean canParse(IGroup group) {
        for (EnumCondition condition : EnumCondition.values()){
            if (condition.value().equals(group.getCondition())){
                return true;
            }
        }
        return false;
    }

    public Operation parse(IGroup group, List<Object> rules) {
        // AND
        if (EnumCondition.AND.value().equals(group.getCondition())){

        }

        return null;
    }
}
