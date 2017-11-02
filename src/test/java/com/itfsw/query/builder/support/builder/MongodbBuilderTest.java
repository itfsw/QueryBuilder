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

import com.itfsw.query.builder.MongodbQueryBuilderFactory;
import com.itfsw.query.builder.other.FileHelper;
import com.itfsw.query.builder.support.model.result.MongodbQueryResult;
import com.itfsw.query.builder.support.utils.spring.StringUtils;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;

/**
 * ---------------------------------------------------------------------------
 *
 * ---------------------------------------------------------------------------
 * @author: hewei
 * @time:2017/11/2 17:57
 * ---------------------------------------------------------------------------
 */
public class MongodbBuilderTest {
    private static MongodbBuilder builder;

    @BeforeClass
    public static void init() {
        MongodbQueryBuilderFactory factory = new MongodbQueryBuilderFactory();
        builder = factory.builder();
    }

    /**
     * equal 操作
     */
    @Test
    public void testOperatorBetween() throws IOException {
        String json = FileHelper.getStringFrom("tasks/operator-between.json");
        MongodbQueryResult result = builder.build(json);

        Assert.assertEquals(
                StringUtils.trimAllWhitespace(result.getQuery().toString()),
                "{\"age\":{\"$gte\":5,\"$lte\":10}}"
        );
    }

    /**
     * equal 操作
     */
    @Test
    public void testOperatorEqual() throws IOException {
        String json = FileHelper.getStringFrom("tasks/operator-equal.json");
        MongodbQueryResult result = builder.build(json);

        Assert.assertEquals(
                StringUtils.trimAllWhitespace(result.getQuery().toString()),
                "{\"username\":\"xiaoxiao\"}"
        );
    }
}