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

package com.itfsw.query.builder.model;

import java.util.List;

/**
 * ---------------------------------------------------------------------------
 *
 * ---------------------------------------------------------------------------
 * @author: hewei
 * @time:2017/10/30 16:00
 * ---------------------------------------------------------------------------
 */
public class Group extends Rule {
    private String condition;
    private Boolean valid;
    private Boolean not;
    private List<Rule> rules;

    /**
     * Getter method for property <tt>condition</tt>.
     * @return property value of condition
     * @author hewei
     */
    public String getCondition() {
        return condition;
    }

    /**
     * Setter method for property <tt>condition</tt>.
     * @param condition value to be assigned to property condition
     * @author hewei
     */
    public void setCondition(String condition) {
        this.condition = condition;
    }

    /**
     * Getter method for property <tt>valid</tt>.
     * @return property value of valid
     * @author hewei
     */
    public Boolean getValid() {
        return valid;
    }

    /**
     * Setter method for property <tt>valid</tt>.
     * @param valid value to be assigned to property valid
     * @author hewei
     */
    public void setValid(Boolean valid) {
        this.valid = valid;
    }

    /**
     * Getter method for property <tt>not</tt>.
     * @return property value of not
     * @author hewei
     */
    public Boolean getNot() {
        return not;
    }

    /**
     * Setter method for property <tt>not</tt>.
     * @param not value to be assigned to property not
     * @author hewei
     */
    public void setNot(Boolean not) {
        this.not = not;
    }

    /**
     * Getter method for property <tt>rules</tt>.
     * @return property value of rules
     * @author hewei
     */
    public List<Rule> getRules() {
        return rules;
    }

    /**
     * Setter method for property <tt>rules</tt>.
     * @param rules value to be assigned to property rules
     * @author hewei
     */
    public void setRules(List<Rule> rules) {
        this.rules = rules;
    }
}
