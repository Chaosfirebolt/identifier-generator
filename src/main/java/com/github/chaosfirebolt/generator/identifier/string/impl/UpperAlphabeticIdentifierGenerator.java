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

package com.github.chaosfirebolt.generator.identifier.string.impl;

import com.github.chaosfirebolt.generator.identifier.string.StringIdentifierGenerator;
import com.github.chaosfirebolt.generator.identifier.string.rule.UpperAlphabeticGeneratorRule;
import com.github.chaosfirebolt.generator.identifier.string.validation.RuleValidator;

import java.util.Random;

/**
 * Implementation generating upper case identifiers.
 * <br>
 * Created by ChaosFire on 18-Dec-21
 */
@Deprecated(forRemoval = true)
public class UpperAlphabeticIdentifierGenerator extends StringIdentifierGenerator {

    /**
     * Constructs new instance of identifier generator, with desired length for upper case characters.
     * @param length length of upper case characters
     * @throws IllegalArgumentException if length is negative or zero, the underlying Part constructor will throw this exception
     */
    public UpperAlphabeticIdentifierGenerator(int length) {
        super(new UpperAlphabeticGeneratorRule(length));
    }

    /**
     * Constructs new instance of identifier generator, with desired length for upper case characters and provided validators.
     *
     * @param length        length of upper case characters
     * @param ruleValidator validators for the rule
     * @throws IllegalArgumentException if length is negative or zero, the underlying Part constructor will throw this exception
     */
    public UpperAlphabeticIdentifierGenerator(int length, RuleValidator ruleValidator) {
        super(new UpperAlphabeticGeneratorRule(length), ruleValidator);
    }

    /**
     * Constructs new instance of identifier generator, with desired length for upper case characters, provided validators and random.
     *
     * @param random        random number generator
     * @param length        length of upper case characters
     * @param ruleValidator validators for the rule
     * @throws IllegalArgumentException if length is negative or zero, the underlying Part constructor will throw this exception
     */
    public UpperAlphabeticIdentifierGenerator(Random random, int length, RuleValidator ruleValidator) {
        super(random, new UpperAlphabeticGeneratorRule(length), ruleValidator);
    }
}
