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

package com.github.chaosfirebolt.generator.identifier.string.builders;

import com.github.chaosfirebolt.generator.identifier.string.StringIdentifierGenerator;
import com.github.chaosfirebolt.generator.identifier.string.rule.GeneratorRule;
import com.github.chaosfirebolt.generator.identifier.string.validation.RuleValidator;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.random.RandomGenerator;

/**
 * Builder for {@link StringIdentifierGenerator}s.
 */
public class StringIdentifierGeneratorBuilder {

    private RandomGenerator randomGenerator;
    private GeneratorRule generatorRule;
    private List<RuleValidator> ruleValidators;

    StringIdentifierGeneratorBuilder() {
    }

    /**
     * @param randomGenerator the random generator to be used
     * @return this instance
     */
    public StringIdentifierGeneratorBuilder setRandomGenerator(RandomGenerator randomGenerator) {
        this.randomGenerator = randomGenerator;
        return this;
    }

    /**
     * @param generatorRule the generator rule to be used
     * @return this instance
     */
    public StringIdentifierGeneratorBuilder setGeneratorRule(GeneratorRule generatorRule) {
        this.generatorRule = generatorRule;
        return this;
    }

    /**
     * Replaces the current validators with the new ones.
     * @param ruleValidators validators replacing current ones
     * @return this instance
     */
    public StringIdentifierGeneratorBuilder setRuleValidators(List<RuleValidator> ruleValidators) {
        this.ruleValidators = ruleValidators;
        return this;
    }

    /**
     * Adds the validator to the list of validators.
     * @param ruleValidator validator to add
     * @return this instance
     */
    public StringIdentifierGeneratorBuilder addRuleValidator(RuleValidator ruleValidator) {
        this.ruleValidators.add(ruleValidator);
        return this;
    }

    /**
     * Creates new instance of {@link StringIdentifierGenerator}. Provides defaults of some of the parameters are missing, wherever possible.
     * @return a new instance
     * @throws NullPointerException if parameter is missing and can't supply default value for it
     */
    public StringIdentifierGenerator build() {
        RandomGenerator randomGenerator = this.randomGenerator == null ? new SecureRandom() : this.randomGenerator;
        List<RuleValidator> ruleValidators = this.ruleValidators == null ? StringIdentifierGenerator.DEFAULT_VALIDATORS : new ArrayList<>(this.ruleValidators);
        return new StringIdentifierGenerator(randomGenerator, Objects.requireNonNull(this.generatorRule, "Missing generator rule"), ruleValidators);
    }
}
