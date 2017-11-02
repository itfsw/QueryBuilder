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

import com.itfsw.query.builder.support.builder.AbstractBuilder;
import com.itfsw.query.builder.support.filter.DatetimeConvertFilter;
import com.itfsw.query.builder.support.filter.DefaultValueConvertFilter;
import com.itfsw.query.builder.support.filter.IRuleFilter;
import com.itfsw.query.builder.support.filter.ValidateFilter;
import com.itfsw.query.builder.support.parser.IRuleParser;

import java.util.ArrayList;
import java.util.List;

/**
 * ---------------------------------------------------------------------------
 *
 * ---------------------------------------------------------------------------
 * @author: hewei
 * @time:2017/11/2 14:49
 * ---------------------------------------------------------------------------
 */
public abstract class AbstractQueryBuilderFactory {
    protected List<IRuleFilter> filters;    // filters
    protected List<IRuleParser> ruleParsers;   // rule parser

    /**
     * 构造函数
     */
    public AbstractQueryBuilderFactory() {
        this.filters = new ArrayList<>();
        this.ruleParsers = new ArrayList<>();

        // -------------------------- filter -----------------------------
        filters.add(new ValidateFilter());
        filters.add(new DefaultValueConvertFilter());
        filters.add(new DatetimeConvertFilter());
    }

    /**
     * 获取builder
     * @return
     */
    public abstract AbstractBuilder builder();

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
}
