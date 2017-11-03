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

package com.itfsw.query.builder.exception;

import com.itfsw.query.builder.SqlQueryBuilderFactory;
import com.itfsw.query.builder.other.CustomParser;
import com.itfsw.query.builder.other.FileHelper;
import com.itfsw.query.builder.support.builder.SqlBuilder;
import com.itfsw.query.builder.support.model.result.SqlQueryResult;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

/**
 * ---------------------------------------------------------------------------
 *
 * ---------------------------------------------------------------------------
 * @author: hewei
 * @time:2017/11/3 13:54
 * ---------------------------------------------------------------------------
 */
public class ParserNotFoundExceptionTest {
    private SqlBuilder builder;

    @Before
    public void init() {
        SqlQueryBuilderFactory factory = new SqlQueryBuilderFactory();
        builder = factory.builder();
    }

    /**
     * 测试parser 找不到
     */
    @Test(expected = ParserNotFoundException.class)
    public void testParserNotFound() throws IOException {
        String json = FileHelper.getStringFrom("tasks/custom-operator.json");
        builder.build(json);
    }

    /**
     * 测试parser 找不到(添加自定义)
     */
    @Test
    public void testParserNotFoundAndAdd() throws IOException {
        SqlQueryBuilderFactory factory = new SqlQueryBuilderFactory();
        factory.addParser(new CustomParser());
        SqlBuilder builder = factory.builder();

        String json = FileHelper.getStringFrom("tasks/custom-operator.json");
        SqlQueryResult result = builder.build(json);
        Assert.assertEquals("username <> ?", result.getQuery());
        Assert.assertEquals("Mistic", result.getParams().get(0));
        Assert.assertEquals("username <> 'Mistic'", result.getQuery(true));
    }
}