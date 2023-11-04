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
import com.github.chaosfirebolt.generator.identifier.string.rule.AlphabeticGeneratorRule;
import com.github.chaosfirebolt.generator.identifier.string.validation.RuleValidator;

import java.util.random.RandomGenerator;

/**
 * Implementation generating alphabetic identifiers.
 * <br>
 * Created by ChaosFire on 18-Dec-21
 */
@Deprecated(forRemoval = true)
public class AlphabeticIdentifierGenerator extends StringIdentifierGenerator {

    /**
     * Constructs new instance of identifier generator, with desired lengths for lower and upper case characters.
     * @param lowerCaseLength length of lower case characters
     * @param upperCaseLength length of upper case characters
     * @throws IllegalArgumentException if any of the length parameters is negative or zero, the underlying Part constructors will throw this exception
     */
    public AlphabeticIdentifierGenerator(int lowerCaseLength, int upperCaseLength) {
        super(new AlphabeticGeneratorRule(lowerCaseLength, upperCaseLength));
    }

    /**
     * Constructs new instance of identifier generator, with desired lengths for lower and upper case characters, and provided validators.
     *
     * @param lowerCaseLength length of lower case characters
     * @param upperCaseLength length of upper case characters
     * @param ruleValidator   validators for the rule
     * @throws IllegalArgumentException if any of the length parameters is negative or zero, the underlying Part constructors will throw this exception
     */
    public AlphabeticIdentifierGenerator(int lowerCaseLength, int upperCaseLength, RuleValidator ruleValidator) {
        super(new AlphabeticGeneratorRule(lowerCaseLength, upperCaseLength), ruleValidator);
    }

    /**
     * Constructs new instance of identifier generator, with desired lengths for lower and upper case characters, provided validators and random.
     *
     * @param randomGenerator random number generator
     * @param lowerCaseLength length of lower case characters
     * @param upperCaseLength length of upper case characters
     * @param ruleValidator   validators for the rule
     * @throws IllegalArgumentException if any of the length parameters is negative or zero, the underlying Part constructors will throw this exception
     */
    public AlphabeticIdentifierGenerator(RandomGenerator randomGenerator, int lowerCaseLength, int upperCaseLength, RuleValidator ruleValidator) {
        super(randomGenerator, new AlphabeticGeneratorRule(lowerCaseLength, upperCaseLength), ruleValidator);
    }
}
