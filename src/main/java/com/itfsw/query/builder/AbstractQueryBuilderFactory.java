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

import com.itfsw.query.builder.supports.builder.AbstractBuilder;
import com.itfsw.query.builder.supports.filter.IRuleFilter;
import com.itfsw.query.builder.supports.parser.IGroupParser;
import com.itfsw.query.builder.supports.parser.IRuleParser;

import java.util.List;

/**
 * ---------------------------------------------------------------------------
 *
 * ---------------------------------------------------------------------------
 * @author: hewei
 * @time:2017/10/30 15:39
 * ---------------------------------------------------------------------------
 */
public abstract class AbstractQueryBuilderFactory {
    private List<IRuleFilter> filters;   // 过滤器
    private List<IRuleParser> ruleParsers;  // 解析器
    private IGroupParser groupParser;   // group 解析器

    /**
     * 获取构建对象
     * @param query
     * @return
     */
    public abstract AbstractBuilder builder(String query);

    /**
     * 添加filter
     * @param filter
     */
    public void addFilter(IRuleFilter filter){
        filters.add(filter);
    }

    /**
     * 添加Filter
     * @param filter
     * @param index
     */
    public void addFilter(IRuleFilter filter, Integer index){
        filters.add(index, filter);
    }

    /**
     * 添加解析器
     * @param parser
     */
    public void addRuleParser(IRuleParser parser){
        ruleParsers.add(parser);
    }

    /**
     * Setter method for property <tt>groupParser</tt>.
     * @param groupParser value to be assigned to property groupParser
     * @author hewei
     */
    public void setGroupParser(IGroupParser groupParser) {
        this.groupParser = groupParser;
    }

    /**
     * Getter method for property <tt>filters</tt>.
     * @return property value of filters
     * @author hewei
     */
    public List<IRuleFilter> getFilters() {
        return filters;
    }

    /**
     * Getter method for property <tt>ruleParsers</tt>.
     * @return property value of ruleParsers
     * @author hewei
     */
    public List<IRuleParser> getRuleParsers() {
        return ruleParsers;
    }

    /**
     * Getter method for property <tt>groupParser</tt>.
     * @return property value of groupParser
     * @author hewei
     */
    public IGroupParser getGroupParser() {
        return groupParser;
    }
}
