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

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itfsw.query.builder.exception.ParserNotFoundException;
import com.itfsw.query.builder.supports.model.GroupOperation;
import com.itfsw.query.builder.supports.model.JsonRule;
import com.itfsw.query.builder.supports.model.Operation;
import com.itfsw.query.builder.supports.parser.IGroupParser;
import com.itfsw.query.builder.supports.parser.IRuleParser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * ---------------------------------------------------------------------------
 * 构造类
 * ---------------------------------------------------------------------------
 * @author: hewei
 * @time:2017/10/30 15:44
 * ---------------------------------------------------------------------------
 */
public abstract class AbstractBuilder {
    private String query;   // 查询字符串
    private static ObjectMapper mapper; // object mapper
    private List<IRuleParser> ruleParsers;   // rule parser
    private IGroupParser groupParser;

    static {
        // object mapper
        mapper = new ObjectMapper();

        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    /**
     * 执行构建
     * @param query
     * @return
     */
    public boolean build(String query) throws IOException, ParserNotFoundException {
        JsonRule rule = mapper.readValue(query, JsonRule.class);
        Operation operation = parse(rule);
        return build(operation);
    }

    /**
     * 构建
     * @param operation
     * @return
     */
    protected abstract boolean build(Operation operation);

    /**
     * 解析操作
     * @param rule
     * @return
     * @throws ParserNotFoundException
     */
    private Operation parse(JsonRule rule) throws ParserNotFoundException {
        // 解析group
        if (rule.isGroup()) {
            if (groupParser.canParse(rule)) {
                GroupOperation operation = new GroupOperation();
                operation.setOperator(groupParser.operator(rule));

                // 递归解析
                List<Operation> list = new ArrayList<Operation>();
                for (JsonRule item : rule.getRules()) {
                    list.add(parse(item));
                }
                operation.setValue(list);

                return operation;
            } else {
                throw new ParserNotFoundException(
                        "Can't found a group parser for condition:" + rule.getCondition()
                                + (rule.getNot() != null ? " not:" + rule.getNot() : "") + "!"
                );
            }
        } else {
            // 解析rule
            for (IRuleParser parser : ruleParsers) {
                if (parser.canParse(rule)) {
                    return parser.parse(rule);
                }
            }
            // can not found rule parser
            throw new ParserNotFoundException("Can't found a rule parser!");
        }
    }
}
