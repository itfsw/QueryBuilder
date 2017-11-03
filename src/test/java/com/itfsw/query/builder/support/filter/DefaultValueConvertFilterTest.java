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

package com.itfsw.query.builder.support.filter;

import com.itfsw.query.builder.SqlQueryBuilderFactory;
import com.itfsw.query.builder.exception.FilterException;
import com.itfsw.query.builder.other.FileHelper;
import com.itfsw.query.builder.support.builder.SqlBuilder;
import com.itfsw.query.builder.support.model.result.SqlQueryResult;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;

/**
 * ---------------------------------------------------------------------------
 *
 * ---------------------------------------------------------------------------
 * @author: hewei
 * @time:2017/11/3 16:04
 * ---------------------------------------------------------------------------
 */
public class DefaultValueConvertFilterTest {
    private static SqlBuilder builder;

    @BeforeClass
    public static void init() {
        SqlQueryBuilderFactory factory = new SqlQueryBuilderFactory();
        builder = factory.builder();
    }

    /**
     * 测试value转String
     */
    @Test
    public void testValue2String() throws IOException {
        String json = FileHelper.getStringFrom("tasks/value-2-string.json");
        SqlQueryResult result = builder.build(json);

        Assert.assertEquals("price = ? AND age IN(?, ?, ?)", result.getQuery());
        Assert.assertEquals("10.04", result.getParams().get(0));
        Assert.assertEquals("10", result.getParams().get(1));
        Assert.assertEquals("50", result.getParams().get(2));
        Assert.assertEquals("60", result.getParams().get(3));
        Assert.assertEquals("price = '10.04' AND age IN('10', '50', '60')", result.getQuery(true));
    }

    /**
     * 测试value转Double
     */
    @Test
    public void testValue2Double() throws IOException {
        String json = FileHelper.getStringFrom("tasks/value-2-double.json");
        SqlQueryResult result = builder.build(json);

        Assert.assertEquals("price = ? AND age IN(?, ?, ?)", result.getQuery());
        Assert.assertEquals(10.04d, result.getParams().get(0));
        Assert.assertEquals(10d, result.getParams().get(1));
        Assert.assertEquals(50d, result.getParams().get(2));
        Assert.assertEquals(60d, result.getParams().get(3));
        Assert.assertEquals("price = 10.04 AND age IN(10.0, 50.0, 60.0)", result.getQuery(true));
    }

    /**
     * 测试value转Integer
     */
    @Test
    public void testValue2Integer() throws IOException {
        String json = FileHelper.getStringFrom("tasks/value-2-integer.json");
        SqlQueryResult result = builder.build(json);

        Assert.assertEquals("price = ? AND age IN(?, ?, ?)", result.getQuery());
        Assert.assertEquals(10, result.getParams().get(0));
        Assert.assertEquals(10, result.getParams().get(1));
        Assert.assertEquals(50, result.getParams().get(2));
        Assert.assertEquals(60, result.getParams().get(3));
        Assert.assertEquals("price = 10 AND age IN(10, 50, 60)", result.getQuery(true));
    }

    /**
     * 测试转换异常
     */
    @Test(expected = FilterException.class)
    public void testConvertError() throws IOException {
        String json = FileHelper.getStringFrom("tasks/error-value-2-integer.json");
        builder.build(json);
    }
}