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

package com.itfsw.query.builder.supports.factory;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itfsw.query.builder.supports.model.JsonRule;
import com.itfsw.query.builder.supports.AbstractBuilder;

import java.io.IOException;

/**
 * ---------------------------------------------------------------------------
 *
 * ---------------------------------------------------------------------------
 * @author: hewei
 * @time:2017/10/30 15:39
 * ---------------------------------------------------------------------------
 */
public abstract class AbstractQueryBuilderFactory {
    private static ObjectMapper mapper; // object mapper

    static {
        // object mapper
        mapper = new ObjectMapper();

        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    /**
     * 获取构建对象
     * @param query 查询信息
     * @return
     */
    public abstract AbstractBuilder parse(String query) throws IOException;

    /**
     * 解析规则
     * @param query
     * @return
     */
    protected JsonRule parseRule(String query) throws IOException {
        return mapper.readValue(query, JsonRule.class);
    }
}
