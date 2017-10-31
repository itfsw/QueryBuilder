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

import java.util.ArrayList;
import java.util.List;

/**
 * ---------------------------------------------------------------------------
 * 抽象构造工厂
 * ---------------------------------------------------------------------------
 * @author: hewei
 * @time:2017/10/30 15:39
 * ---------------------------------------------------------------------------
 */
public abstract class AbstractQueryBuilderFactory {
    protected List<IRuleFilter> filters;   // 过滤器
    protected List<IRuleParser> ruleParsers;  // 解析器
    protected IGroupParser groupParser;   // group 解析器

    /**
     * 构造函数
     */
    public AbstractQueryBuilderFactory() {
        this.filters = new ArrayList<IRuleFilter>();
        this.ruleParsers = new ArrayList<IRuleParser>();
    }

    /**
     * 获取构建对象
     * @param queryStr
     * @return
     */
    public abstract AbstractBuilder builder(String queryStr);

    /**
     * 添加filter
     * @param filter
     */
    public void addFilter(IRuleFilter filter) {
        filters.add(filter);
    }

    /**
     * 添加Filter
     * @param filter
     * @param index
     */
    public void addFilter(IRuleFilter filter, Integer index) {
        filters.add(index, filter);
    }

    /**
     * 添加解析器
     * @param parser
     */
    public void addRuleParser(IRuleParser parser) {
        ruleParsers.add(parser);
    }

    /**
     * 添加解析器
     * @param parser
     * @param index
     */
    public void addRuleParser(IRuleParser parser, Integer index) {
        ruleParsers.add(index, parser);
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
