/*
 * Copyright 2021 Boyan Georgiev
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

import com.github.chaosfirebolt.generator.identifier.rule.GeneratorRule;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by ChaosFire on 21-Dec-21
 */
public abstract class GeneratorRuleTests {

    private final GeneratorRule generatorRule;

    protected GeneratorRuleTests(GeneratorRule generatorRule) {
        this.generatorRule = generatorRule;
    }

    @Test
    public void getParts_ShouldReturnCorrectNumberOfParts() {
        int size = this.generatorRule.getParts().size();
        assertEquals(this.expectedNumberOfParts(), size);
    }

    protected abstract int expectedNumberOfParts();

    @Test
    public void getLength_ShouldReturnCorrectLength() {
        int length = this.generatorRule.getLength();
        assertEquals(this.expectedLength(), length);
    }

    protected abstract int expectedLength();

    @Test
    public void getMinLength_ShouldReturnCorrectMinLength() {
        int minLength = this.generatorRule.getMinLength();
        assertEquals(this.expectedMinLength(), minLength);
    }

    protected abstract int expectedMinLength();
}