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

package com.itfsw.query.builder.support.model;

import java.util.List;

/**
 * ---------------------------------------------------------------------------
 *
 * ---------------------------------------------------------------------------
 * @author: hewei
 * @time:2017/11/1 20:34
 * ---------------------------------------------------------------------------
 */
public class SqlQueryResult {
    private String query;
    private List<Object> params;

    /**
     * 构造函数
     * @param query
     * @param params
     */
    public SqlQueryResult(String query, List<Object> params) {
        this.query = query;
        this.params = params;
    }

    /**
     * Getter method for property <tt>query</tt>.
     * @return property value of query
     * @author hewei
     */
    public String getQuery() {
        return query;
    }

    /**
     * Setter method for property <tt>query</tt>.
     * @param query value to be assigned to property query
     * @author hewei
     */
    public void setQuery(String query) {
        this.query = query;
    }

    /**
     * Getter method for property <tt>params</tt>.
     * @return property value of params
     * @author hewei
     */
    public List<Object> getParams() {
        return params;
    }

    /**
     * Setter method for property <tt>params</tt>.
     * @param params value to be assigned to property params
     * @author hewei
     */
    public void setParams(List<Object> params) {
        this.params = params;
    }
}
