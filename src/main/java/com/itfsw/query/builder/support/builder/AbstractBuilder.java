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

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itfsw.query.builder.exception.ParserNotFoundException;
import com.itfsw.query.builder.support.filter.IRuleFilter;
import com.itfsw.query.builder.support.model.IGroup;
import com.itfsw.query.builder.support.model.IRule;
import com.itfsw.query.builder.support.model.JsonRule;

import java.io.IOException;
import java.util.List;

/**
 * ---------------------------------------------------------------------------
 * 构造类
 * ---------------------------------------------------------------------------
 * @author: hewei
 * @time:2017/10/30 15:44
 * ---------------------------------------------------------------------------
 */
public abstract class AbstractBuilder {
    private static ObjectMapper mapper; // object mapper
    protected List<IRuleFilter> filters;    // filters

    static {
        // object mapper
        mapper = new ObjectMapper();

        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    /**
     * 执行构建
     * @param query
     * @return
     */
    public Object build(String query) throws IOException, ParserNotFoundException{
        JsonRule rule = mapper.readValue(query, JsonRule.class);
        return parse(rule);
    }

    /**
     * 构建
     * @param rule
     * @return
     */
    protected Object parse(JsonRule rule){
        // filter
        doFilter(rule);

        // parse
        if (rule.isGroup()) {
            return parseGroup(rule);
        } else {
            return parseRule(rule);
        }
    }

    /**
     * 构建group
     * @param group
     * @return
     */
    protected abstract Object parseGroup(IGroup group);

    /**
     * 构建
     * @param rule
     * @return
     */
    protected abstract Object parseRule(IRule rule);

    /**
     * 执行过滤
     * @param rule
     */
    private void doFilter(JsonRule rule) {
        for (IRuleFilter filter : filters) {
            filter.doFilter(rule);
        }
    }
}
