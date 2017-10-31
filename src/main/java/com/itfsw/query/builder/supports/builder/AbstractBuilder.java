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

package com.itfsw.query.builder.supports.builder;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itfsw.query.builder.exception.ParserNotFoundException;
import com.itfsw.query.builder.supports.filter.IRuleFilter;
import com.itfsw.query.builder.supports.parser.IGroupParser;
import com.itfsw.query.builder.supports.parser.IRuleParser;

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

    private String queryStr;   // 查询字符串
    protected List<IRuleFilter> filters;    // filters
    protected List<IRuleParser> ruleParsers;   // rule parser
    protected IGroupParser groupParser;

    static {
        // object mapper
        mapper = new ObjectMapper();

        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    /**
     * 构造函数
     * @param queryStr
     * @param filters
     * @param ruleParsers
     * @param groupParser
     */
    public AbstractBuilder(String queryStr, List<IRuleFilter> filters, List<IRuleParser> ruleParsers, IGroupParser groupParser) {
        this.queryStr = queryStr;
        this.filters = filters;
        this.ruleParsers = ruleParsers;
        this.groupParser = groupParser;
    }

    /**
     * 执行构建
     * @return
     */
    public abstract boolean build() throws IOException, ParserNotFoundException;
}
