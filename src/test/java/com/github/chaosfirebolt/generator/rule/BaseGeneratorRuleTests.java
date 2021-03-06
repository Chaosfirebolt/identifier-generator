/*
 * Copyright 2021-2022 Boyan Georgiev
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

import com.github.chaosfirebolt.generator.identifier.part.NumericPart;
import com.github.chaosfirebolt.generator.identifier.part.SpecialCharacterPart;
import com.github.chaosfirebolt.generator.identifier.rule.BaseGeneratorRule;

import java.util.Arrays;

/**
 * Created by ChaosFire on 21-Dec-21
 */
public class BaseGeneratorRuleTests extends GeneratorRuleTests {

    public BaseGeneratorRuleTests() {
        super(new BaseGeneratorRule(Arrays.asList(new SpecialCharacterPart(3, 1), new NumericPart(19, 17))));
    }

    @Override
    protected int expectedNumberOfParts() {
        return 2;
    }

    @Override
    protected int expectedLength() {
        return 22;
    }

    @Override
    protected int expectedMinLength() {
        return 18;
    }
}