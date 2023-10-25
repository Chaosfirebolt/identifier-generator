/*
 * Copyright 2021-2023 Boyan Georgiev
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

package com.github.chaosfirebolt.generator.rule;

import com.github.chaosfirebolt.generator.identifier.rule.AlphaNumericGeneratorRule;

/**
 * Created by ChaosFire on 21-Dec-21
 */
public class AlphaNumericGeneratorRuleTests extends GeneratorRuleTests {

    private static final int LOWER_CASE_LENGTH = 7;
    private static final int UPPER_CASE_LENGTH = 9;
    private static final int NUMERIC_LENGTH = 5;

    public AlphaNumericGeneratorRuleTests() {
        super(new AlphaNumericGeneratorRule(LOWER_CASE_LENGTH, UPPER_CASE_LENGTH, NUMERIC_LENGTH));
    }

    @Override
    protected int expectedNumberOfParts() {
        return 3;
    }

    @Override
    protected int expectedLength() {
        return LOWER_CASE_LENGTH + UPPER_CASE_LENGTH + NUMERIC_LENGTH;
    }

    @Override
    protected int expectedMinLength() {
        return LOWER_CASE_LENGTH + UPPER_CASE_LENGTH + NUMERIC_LENGTH;
    }
}