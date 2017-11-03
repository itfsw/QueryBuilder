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
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;

/**
 * ---------------------------------------------------------------------------
 *
 * ---------------------------------------------------------------------------
 * @author: hewei
 * @time:2017/11/3 15:44
 * ---------------------------------------------------------------------------
 */
public class ValidateFilterTest {
    private static SqlBuilder builder;

    @BeforeClass
    public static void init() {
        SqlQueryBuilderFactory factory = new SqlQueryBuilderFactory();
        builder = factory.builder();
    }

    /**
     * 测试空 rule
     */
    @Test(expected = FilterException.class)
    public void testEmptyRules() throws IOException {
        String json = FileHelper.getStringFrom("tasks/error-empty-rules.json");
        builder.build(json);
    }

    /**
     * 测试空 field
     */
    @Test(expected = FilterException.class)
    public void testEmptyField() throws IOException {
        String json = FileHelper.getStringFrom("tasks/error-empty-field.json");
        builder.build(json);
    }

    /**
     * 测试空 type
     */
    @Test(expected = FilterException.class)
    public void testEmptyType() throws IOException {
        String json = FileHelper.getStringFrom("tasks/error-empty-type.json");
        builder.build(json);
    }

    /**
     * 测试空 operator
     */
    @Test(expected = FilterException.class)
    public void testEmptyOperator() throws IOException {
        String json = FileHelper.getStringFrom("tasks/error-empty-operator.json");
        builder.build(json);
    }

    /**
     * 测试in value 不是list
     */
    @Test(expected = FilterException.class)
    public void testInValue() throws IOException {
        String json = FileHelper.getStringFrom("tasks/error-in-value.json");
        builder.build(json);
    }

    /**
     * 测试between value 大小不是2个
     */
    @Test(expected = FilterException.class)
    public void testBetweenValue() throws IOException {
        String json = FileHelper.getStringFrom("tasks/error-between-value.json");
        builder.build(json);
    }

    /**
     * 测试equal value 大小超过一个
     */
    @Test(expected = FilterException.class)
    public void testEqualValue() throws IOException {
        String json = FileHelper.getStringFrom("tasks/error-equal-value.json");
        builder.build(json);
    }
}