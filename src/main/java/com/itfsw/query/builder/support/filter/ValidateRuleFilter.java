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

package com.itfsw.query.builder.support.filter;

import com.itfsw.query.builder.exception.FilterException;
import com.itfsw.query.builder.support.model.IGroup;
import com.itfsw.query.builder.support.model.IRule;
import com.itfsw.query.builder.support.model.JsonRule;
import com.itfsw.query.builder.support.model.enums.EnumOperator;

import java.util.List;

/**
 * ---------------------------------------------------------------------------
 * 验证数据
 * ---------------------------------------------------------------------------
 * @author: hewei
 * @time:2017/11/1 11:32
 * ---------------------------------------------------------------------------
 */
public class ValidateRuleFilter implements IRuleFilter {
    /**
     * 执行拦截器
     * @param jsonRule
     * @throws FilterException
     */
    public void doFilter(JsonRule jsonRule) throws FilterException {
        if (jsonRule.isGroup()) {
            IGroup group = jsonRule.toGroup();
            if (group.getRules().isEmpty()) {
                throw new FilterException("group's rules can not be empty for: " + group + "!");
            }
        } else {
            IRule rule = jsonRule.toRule();
            // field
            if (rule.getField() == null || rule.getField().trim().equals("")) {
                throw new FilterException("rule's field can not be empty for:" + rule + "!");
            }
            // must be list
            if (EnumOperator.IN.equals(rule.getOperator()) || EnumOperator.NOT_IN.equals(rule.getOperator())
                    || EnumOperator.BETWEEN.equals(rule.getOperator()) || EnumOperator.NOT_BETWEEN.equals(rule.getOperator())) {
                // list
                if (!(rule.getValue() instanceof List)) {
                    throw new FilterException("rule's value must be Array for:" + rule + "!");
                }

                // size
                if (EnumOperator.BETWEEN.equals(rule.getOperator()) || EnumOperator.NOT_BETWEEN.equals(rule.getOperator())) {
                    List list = (List) rule.getValue();
                    if (list.size() != 2) {
                        throw new FilterException("rule's value size must be 2 for:" + rule + "!");
                    }
                }
            }
        }
    }
}
