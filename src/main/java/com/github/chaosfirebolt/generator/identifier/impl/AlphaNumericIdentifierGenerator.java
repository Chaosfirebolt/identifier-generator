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

package com.github.chaosfirebolt.generator.identifier.impl;

import com.github.chaosfirebolt.generator.identifier.StringIdentifierGenerator;
import com.github.chaosfirebolt.generator.identifier.rule.AlphaNumericGeneratorRule;
import com.github.chaosfirebolt.generator.identifier.validation.RuleValidator;

import java.util.List;
import java.util.Random;

/**
 * Implementation generating alphanumeric identifiers.
 * <br>
 * Created by ChaosFire on 18-Dec-21
 */
public class AlphaNumericIdentifierGenerator extends StringIdentifierGenerator {

    /**
     * Constructs new instance of identifier generator, with desired lengths for lower case, upper case and numeric characters.
     * @param lowerCaseLength length of lower case characters
     * @param upperCaseLength length of upper case characters
     * @param numericLength length of numeric characters
     * @throws IllegalArgumentException if any of the length parameters is negative or zero, the underlying Part constructors will throw this exception
     */
    public AlphaNumericIdentifierGenerator(int lowerCaseLength, int upperCaseLength, int numericLength) {
        super(new AlphaNumericGeneratorRule(lowerCaseLength, upperCaseLength, numericLength));
    }

    /**
     * Constructs new instance of identifier generator, with desired lengths for lower case, upper case, numeric characters and provided validators.
     * @param lowerCaseLength length of lower case characters
     * @param upperCaseLength length of upper case characters
     * @param numericLength length of numeric characters
     * @param ruleValidators validators for the rule
     * @throws IllegalArgumentException if any of the length parameters is negative or zero, the underlying Part constructors will throw this exception
     */
    public AlphaNumericIdentifierGenerator(int lowerCaseLength, int upperCaseLength, int numericLength, List<RuleValidator> ruleValidators) {
        super(new AlphaNumericGeneratorRule(lowerCaseLength, upperCaseLength, numericLength), ruleValidators);
    }

    /**
     * Constructs new instance of identifier generator, with desired lengths for lower case, upper case, numeric characters provided validators and random.
     * @param random random number generator
     * @param lowerCaseLength length of lower case characters
     * @param upperCaseLength length of upper case characters
     * @param numericLength length of numeric characters
     * @param ruleValidators validators for the rule
     * @throws IllegalArgumentException if any of the length parameters is negative or zero, the underlying Part constructors will throw this exception
     */
    public AlphaNumericIdentifierGenerator(Random random, int lowerCaseLength, int upperCaseLength, int numericLength, List<RuleValidator> ruleValidators) {
        super(random, new AlphaNumericGeneratorRule(lowerCaseLength, upperCaseLength, numericLength), ruleValidators);
    }
}