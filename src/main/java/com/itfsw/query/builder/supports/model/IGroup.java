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

package com.itfsw.query.builder.supports.model;

import java.util.List;

/**
 * ---------------------------------------------------------------------------
 *
 * ---------------------------------------------------------------------------
 * @author: hewei
 * @time:2017/10/31 13:10
 * ---------------------------------------------------------------------------
 */
public interface IGroup {
    /**
     * Getter method for property <tt>condition</tt>.
     * @return property value of condition
     */
    String getCondition();

    /**
     * Getter method for property <tt>not</tt>.
     * @return property value of not
     */
    Boolean getNot();

    /**
     * Getter method for property <tt>rules</tt>.
     * @return property value of rules
     */
    List<JsonRule> getRules();
}
