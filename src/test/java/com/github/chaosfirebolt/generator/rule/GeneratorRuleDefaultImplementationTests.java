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

import com.github.chaosfirebolt.generator.identifier.part.Part;
import com.github.chaosfirebolt.generator.identifier.part.UpperAlphabeticPart;
import com.github.chaosfirebolt.generator.identifier.rule.GeneratorRule;

import java.util.Collections;
import java.util.List;

/**
 * Created by ChaosFire on 24-Dec-21
 */
public class GeneratorRuleDefaultImplementationTests extends GeneratorRuleTests {

    public GeneratorRuleDefaultImplementationTests() {
        super(createRule());
    }

    private static GeneratorRule createRule() {
        return new UnvalidatedGeneratorRule(Collections.singletonList(new UpperAlphabeticPart(10)), 10);
    }

    @Override
    protected int expectedNumberOfParts() {
        return 1;
    }

    @Override
    protected int expectedLength() {
        return 10;
    }

    @Override
    protected int expectedMinLength() {
        return this.expectedLength();
    }

    private static final class UnvalidatedGeneratorRule implements GeneratorRule {

        private final List<Part> parts;
        private final int length;

        private UnvalidatedGeneratorRule(List<Part> parts, int length) {
            this.parts = parts;
            this.length = length;
        }

        @Override
        public List<Part> getParts() {
            return this.parts;
        }

        @Override
        public int getLength() {
            return this.length;
        }
    }
}