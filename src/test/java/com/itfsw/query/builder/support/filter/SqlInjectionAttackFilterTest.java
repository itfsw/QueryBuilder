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
 * @time:2017/11/3 21:41
 * ---------------------------------------------------------------------------
 */
public class SqlInjectionAttackFilterTest {
    private static SqlBuilder builder;

    @BeforeClass
    public static void init() {
        SqlQueryBuilderFactory factory = new SqlQueryBuilderFactory();
        builder = factory.builder();
    }

    /**
     * field too long
     */
    @Test(expected = FilterException.class)
    public void testErrorSqlTooLong() throws IOException {
        String json = FileHelper.getStringFrom("tasks/error-sql-too-long.json");
        builder.build(json);
    }

    /**
     * field Special word
     */
    @Test(expected = FilterException.class)
    public void testErrorSqlSpecialWord() throws IOException {
        String json = FileHelper.getStringFrom("tasks/error-sql-special-word.json");
        builder.build(json);
    }

    /**
     * key word
     */
    @Test
    public void testErrorSqlKeyword() throws IOException {
        String json = FileHelper.getStringFrom("tasks/error-sql-key-word.json");
        SqlQueryResult result = builder.build(json);

        Assert.assertEquals("`select` = ?", result.getQuery());
    }
}