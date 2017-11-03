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

import com.itfsw.query.builder.MongodbQueryBuilderFactory;
import com.itfsw.query.builder.SqlQueryBuilderFactory;
import com.itfsw.query.builder.exception.FilterException;
import com.itfsw.query.builder.other.FileHelper;
import com.itfsw.query.builder.support.builder.MongodbBuilder;
import com.itfsw.query.builder.support.builder.SqlBuilder;
import com.itfsw.query.builder.support.model.result.MongodbQueryResult;
import com.itfsw.query.builder.support.model.result.SqlQueryResult;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * ---------------------------------------------------------------------------
 *
 * ---------------------------------------------------------------------------
 * @author: hewei
 * @time:2017/11/3 14:16
 * ---------------------------------------------------------------------------
 */
public class DatetimeConvertFilterTest {

    /**
     * 测试默认时间格式
     */
    @Test
    public void testDefaultDatetime() throws IOException {
        SqlQueryBuilderFactory factory = new SqlQueryBuilderFactory();
        SqlBuilder builder = factory.builder();
        String json = FileHelper.getStringFrom("tasks/type-datetime-default.json");
        SqlQueryResult result = builder.build(json);

        Assert.assertEquals("`datetime` = ? AND `date` = ? AND `time` = ?", result.getQuery());

        Assert.assertTrue(result.getParams().get(0) instanceof java.sql.Date);
        Assert.assertEquals("2017-11-03 14:25:12", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(result.getParams().get(0)));

        Assert.assertTrue(result.getParams().get(1) instanceof java.sql.Date);
        Assert.assertEquals("2017-11-15 00:00:00", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(result.getParams().get(1)));

        Assert.assertTrue(result.getParams().get(2) instanceof java.sql.Time);
        Assert.assertEquals("1970-01-01 14:30:00", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(result.getParams().get(2)));

        Assert.assertEquals("`datetime` = '2017-11-03 14:25:12' AND `date` = '2017-11-15 00:00:00' AND `time` = '14:30:00'", result.getQuery(true));
    }

    /**
     * 测试错误时间格式
     */
    @Test(expected = FilterException.class)
    public void testErrorDatetime() throws IOException {
        SqlQueryBuilderFactory factory = new SqlQueryBuilderFactory();
        SqlBuilder builder = factory.builder();
        String json = FileHelper.getStringFrom("tasks/type-datetime-error.json");
        builder.build(json);
    }

    /**
     * 测试参数有list的情况
     */
    @Test
    public void testTypeList() throws IOException {
        MongodbQueryBuilderFactory factory = new MongodbQueryBuilderFactory();
        MongodbBuilder builder = factory.builder();
        String json = FileHelper.getStringFrom("tasks/type-datetime-list.json");
        MongodbQueryResult result = builder.build(json);

        BasicDBObject item = (BasicDBObject) ((DBObject) (((BasicDBList) (result.getQuery().get("$and"))).get(1))).get("date");
        Assert.assertTrue(item.get("$gte") instanceof Date);
        Assert.assertTrue(item.get("$lte") instanceof Date);
        Assert.assertEquals("2017-11-01 00:00:00", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(item.get("$gte")));
        Assert.assertEquals("2017-11-23 00:00:00", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(item.get("$lte")));
    }

    /**
     * 测试自定义
     */
    @Test
    public void testCustom() throws IOException {
        SqlQueryBuilderFactory factory = new SqlQueryBuilderFactory();

        factory.addFilterAt(new DatetimeConvertFilter(
                new SimpleDateFormat("yyyy/MM/dd HH-mm-ss"),
                new SimpleDateFormat("yyyy/MM/dd"),
                new SimpleDateFormat("HH-mm-ss")
        ), DatetimeConvertFilter.class);

        SqlBuilder builder = factory.builder();
        String json = FileHelper.getStringFrom("tasks/type-datetime-custom.json");
        SqlQueryResult result = builder.build(json);

        Assert.assertEquals("`datetime` = ? AND `date` = ? AND `time` = ?", result.getQuery());

        Assert.assertTrue(result.getParams().get(0) instanceof java.sql.Date);
        Assert.assertEquals("2017-11-03 14:25:12", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(result.getParams().get(0)));

        Assert.assertTrue(result.getParams().get(1) instanceof java.sql.Date);
        Assert.assertEquals("2017-11-15 00:00:00", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(result.getParams().get(1)));

        Assert.assertTrue(result.getParams().get(2) instanceof java.sql.Time);
        Assert.assertEquals("1970-01-01 14:30:00", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(result.getParams().get(2)));

        Assert.assertEquals("`datetime` = '2017-11-03 14:25:12' AND `date` = '2017-11-15 00:00:00' AND `time` = '14:30:00'", result.getQuery(true));
    }
}