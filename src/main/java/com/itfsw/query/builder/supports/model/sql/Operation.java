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

package com.itfsw.query.builder.supports.model.sql;

/**
 * ---------------------------------------------------------------------------
 * 操作
 * ---------------------------------------------------------------------------
 * @author: hewei
 * @time:2017/10/31 17:15
 * ---------------------------------------------------------------------------
 */
public class Operation {
    private String query;
    private Object value;

    /**
     * 构造函数
     * @param query
     * @param value
     */
    public Operation(String query, Object value) {
        this.query = query;
        this.value = value;
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
     * Getter method for property <tt>value</tt>.
     * @return property value of value
     * @author hewei
     */
    public Object getValue() {
        return value;
    }

    /**
     * Setter method for property <tt>value</tt>.
     * @param value value to be assigned to property value
     * @author hewei
     */
    public void setValue(Object value) {
        this.value = value;
    }
}
