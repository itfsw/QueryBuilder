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

package com.itfsw.query.builder.supports;

import com.itfsw.query.builder.SqlQueryBuilderFactory;
import com.itfsw.query.builder.supports.builder.SqlBuilder;
import org.junit.Test;

import java.io.IOException;

/**
 * ---------------------------------------------------------------------------
 *
 * ---------------------------------------------------------------------------
 * @author: hewei
 * @time:2017/10/30 17:22
 * ---------------------------------------------------------------------------
 */
public class SqlBuilderTest {

    @Test
    public void test() throws IOException {
        SqlQueryBuilderFactory factory = new SqlQueryBuilderFactory();
        SqlBuilder builder = factory.builder("{\"condition\":\"OR\",\"rules\":[{\"id\":\"name\",\"field\":\"username\",\"type\":\"string\",\"input\":\"text\",\"operator\":\"not_begins_with\",\"value\":\"Mistic\"},{\"id\":\"price\",\"field\":\"price\",\"type\":\"double\",\"input\":\"number\",\"operator\":\"between\",\"value\":[\"100\",\"200\"],\"data\":{\"class\":\"com.example.PriceTag\"}},{\"condition\":\"AND\",\"rules\":[{\"id\":\"category\",\"field\":\"category\",\"type\":\"integer\",\"input\":\"checkbox\",\"operator\":\"in\",\"value\":[\"1\",\"2\"]},{\"id\":\"rate\",\"field\":\"rate\",\"type\":\"integer\",\"input\":\"number\",\"operator\":\"less_or_equal\",\"value\":\"2\"}],\"not\":true}],\"not\":false,\"valid\":true}");
    }
}
