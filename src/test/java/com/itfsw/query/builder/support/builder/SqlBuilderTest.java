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

package com.itfsw.query.builder.support.builder;

import com.itfsw.query.builder.SqlQueryBuilderFactory;
import com.itfsw.query.builder.other.FileHelper;
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
 * @time:2017/11/2 17:56
 * ---------------------------------------------------------------------------
 */
public class SqlBuilderTest {
    private static SqlBuilder builder;

    @BeforeClass
    public static void init() {
        SqlQueryBuilderFactory factory = new SqlQueryBuilderFactory();
        builder = factory.builder();
    }

    /**
     * equal 操作
     */
    @Test
    public void testOperatorEqual() throws IOException {
        String json = FileHelper.getStringFrom("tasks/operator-equal.json");
        SqlQueryResult result = builder.build(json);

        Assert.assertEquals("username = ?", result.getQuery());
        Assert.assertEquals("Mistic", result.getParams().get(0));
        Assert.assertEquals("username = 'Mistic'", result.getQuery(true));
    }

    /**
     * not equal 操作
     */
    @Test
    public void testOperatorNotEqual() throws IOException {
        String json = FileHelper.getStringFrom("tasks/operator-not-equal.json");
        SqlQueryResult result = builder.build(json);

        Assert.assertEquals("username != ?", result.getQuery());
        Assert.assertEquals("Mistic", result.getParams().get(0));
        Assert.assertEquals("username != 'Mistic'", result.getQuery(true));
    }

    /**
     * in 操作
     */
    @Test
    public void testOperatorIn() throws IOException {
        String json = FileHelper.getStringFrom("tasks/operator-in.json");
        SqlQueryResult result = builder.build(json);

        Assert.assertEquals("age IN(?, ?, ?)", result.getQuery());
        Assert.assertEquals(1, result.getParams().get(0));
        Assert.assertEquals(5, result.getParams().get(1));
        Assert.assertEquals(10, result.getParams().get(2));
        Assert.assertEquals("age IN(1, 5, 10)", result.getQuery(true));
    }

    /**
     * not in 操作
     */
    @Test
    public void testOperatorNotIn() throws IOException {
        String json = FileHelper.getStringFrom("tasks/operator-not-in.json");
        SqlQueryResult result = builder.build(json);

        Assert.assertEquals("age NOT IN(?, ?, ?)", result.getQuery());
        Assert.assertEquals(1, result.getParams().get(0));
        Assert.assertEquals(5, result.getParams().get(1));
        Assert.assertEquals(10, result.getParams().get(2));
        Assert.assertEquals("age NOT IN(1, 5, 10)", result.getQuery(true));
    }

    /**
     * less 操作
     */
    @Test
    public void testOperatorLess() throws IOException {
        String json = FileHelper.getStringFrom("tasks/operator-less.json");
        SqlQueryResult result = builder.build(json);

        Assert.assertEquals("age < ?", result.getQuery());
        Assert.assertEquals(50, result.getParams().get(0));
        Assert.assertEquals("age < 50", result.getQuery(true));
    }

    /**
     * less or equal 操作
     */
    @Test
    public void testOperatorLessOrEqual() throws IOException {
        String json = FileHelper.getStringFrom("tasks/operator-less-or-equal.json");
        SqlQueryResult result = builder.build(json);

        Assert.assertEquals("age <= ?", result.getQuery());
        Assert.assertEquals(50, result.getParams().get(0));
        Assert.assertEquals("age <= 50", result.getQuery(true));
    }

    /**
     * greater 操作
     */
    @Test
    public void testOperatorGreater() throws IOException {
        String json = FileHelper.getStringFrom("tasks/operator-greater.json");
        SqlQueryResult result = builder.build(json);

        Assert.assertEquals("age > ?", result.getQuery());
        Assert.assertEquals(50, result.getParams().get(0));
        Assert.assertEquals("age > 50", result.getQuery(true));
    }

    /**
     * greater or equal 操作
     */
    @Test
    public void testOperatorGreaterOrEqual() throws IOException {
        String json = FileHelper.getStringFrom("tasks/operator-greater-or-equal.json");
        SqlQueryResult result = builder.build(json);

        Assert.assertEquals("age >= ?", result.getQuery());
        Assert.assertEquals(50, result.getParams().get(0));
        Assert.assertEquals("age >= 50", result.getQuery(true));
    }

    /**
     * equal 操作
     */
    @Test
    public void testOperatorBetween() throws IOException {
        String json = FileHelper.getStringFrom("tasks/operator-between.json");
        SqlQueryResult result = builder.build(json);

        Assert.assertEquals("age BETWEEN ? AND ?", result.getQuery());
        Assert.assertEquals(5, result.getParams().get(0));
        Assert.assertEquals(10, result.getParams().get(1));
        Assert.assertEquals("age BETWEEN 5 AND 10", result.getQuery(true));
    }

    /**
     * not between 操作
     */
    @Test
    public void testOperatorNotBetween() throws IOException {
        String json = FileHelper.getStringFrom("tasks/operator-not-between.json");
        SqlQueryResult result = builder.build(json);

        Assert.assertEquals("age NOT BETWEEN ? AND ?", result.getQuery());
        Assert.assertEquals(5, result.getParams().get(0));
        Assert.assertEquals(10, result.getParams().get(1));
        Assert.assertEquals("age NOT BETWEEN 5 AND 10", result.getQuery(true));
    }

    /**
     * begins with 操作
     */
    @Test
    public void testOperatorBeginsWith() throws IOException {
        String json = FileHelper.getStringFrom("tasks/operator-begins-with.json");
        SqlQueryResult result = builder.build(json);

        Assert.assertEquals("username LIKE(?)", result.getQuery());
        Assert.assertEquals("Mistic%", result.getParams().get(0));
        Assert.assertEquals("username LIKE('Mistic%')", result.getQuery(true));
    }
}