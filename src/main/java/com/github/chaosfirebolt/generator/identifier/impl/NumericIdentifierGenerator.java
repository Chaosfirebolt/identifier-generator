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

package com.github.chaosfirebolt.generator.identifier.impl;

import com.github.chaosfirebolt.generator.identifier.StringIdentifierGenerator;
import com.github.chaosfirebolt.generator.identifier.rule.NumericGeneratorRule;
import com.github.chaosfirebolt.generator.identifier.validation.RuleValidator;

import java.util.List;
import java.util.Random;

/**
 * Implementation generating numeric identifiers.
 * <br>
 * Created by ChaosFire on 18-Dec-21
 */
public class NumericIdentifierGenerator extends StringIdentifierGenerator {

    /**
     * Constructs new instance of identifier generator, with desired length for numeric characters.
     * @param length length of numeric characters
     * @throws IllegalArgumentException if length is negative or zero, the underlying Part constructor will throw this exception
     */
    public NumericIdentifierGenerator(int length) {
        super(new NumericGeneratorRule(length));
    }

    /**
     * Constructs new instance of identifier generator, with desired length for numeric characters and provided validators.
     * @param length length of numeric characters
     * @param ruleValidators validators for the rule
     * @throws IllegalArgumentException if length is negative or zero, the underlying Part constructor will throw this exception
     */
    public NumericIdentifierGenerator(int length, List<RuleValidator> ruleValidators) {
        super(new NumericGeneratorRule(length), ruleValidators);
    }

    /**
     * Constructs new instance of identifier generator, with desired length for numeric characters, provided validators and random.
     * @param random random number generator
     * @param length length of numeric characters
     * @param ruleValidators validators for the rule
     * @throws IllegalArgumentException if length is negative or zero, the underlying Part constructor will throw this exception
     */
    public NumericIdentifierGenerator(Random random, int length, List<RuleValidator> ruleValidators) {
        super(random, new NumericGeneratorRule(length), ruleValidators);
    }
}
