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

package com.itfsw.query.builder.supports.filter;

import com.itfsw.query.builder.exception.FilterException;
import com.itfsw.query.builder.supports.model.JsonRule;

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
     * @param rule
     * @throws FilterException
     */
    public void doFilter(JsonRule rule) throws FilterException {
        if (rule.isGroup()){

        } else {

        }
    }
}
