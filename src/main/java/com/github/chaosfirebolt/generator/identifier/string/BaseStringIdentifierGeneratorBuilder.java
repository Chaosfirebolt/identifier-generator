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

package com.github.chaosfirebolt.generator.identifier.string;

import com.github.chaosfirebolt.generator.identifier.string.rule.GeneratorRule;
import com.github.chaosfirebolt.generator.identifier.string.validation.CompositeRuleValidator;
import com.github.chaosfirebolt.generator.identifier.string.validation.RuleValidator;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.random.RandomGenerator;

/**
 * The base builder for string based identifier generators.
 * @param <T> concrete type of the builder
 */
@SuppressWarnings("unchecked")
abstract class BaseStringIdentifierGeneratorBuilder<T extends BaseStringIdentifierGeneratorBuilder<T>> {

    private RandomGenerator randomGenerator;
    private GeneratorRule generatorRule;
    private List<RuleValidator> ruleValidators;

    BaseStringIdentifierGeneratorBuilder() {
    }

    /**
     * @param randomGenerator the random generator to be used
     * @return this instance
     */
    public T setRandomGenerator(RandomGenerator randomGenerator) {
        this.randomGenerator = randomGenerator;
        return (T) this;
    }

    /**
     * @param generatorRule the generator rule to be used
     * @return this instance
     */
    T setGeneratorRule(GeneratorRule generatorRule) {
        this.generatorRule = generatorRule;
        return (T) this;
    }

    /**
     * Replaces the current validators with the new ones.
     * @param ruleValidators validators replacing current ones
     * @return this instance
     */
    public T setRuleValidators(List<RuleValidator> ruleValidators) {
        this.ruleValidators = ruleValidators;
        return (T) this;
    }

    /**
     * Adds the validator to the list of validators.
     * @param ruleValidator validator to add
     * @return this instance
     */
    public T addRuleValidator(RuleValidator ruleValidator) {
        if (this.ruleValidators == null) {
            this.ruleValidators = new ArrayList<>();
        }
        this.ruleValidators.add(ruleValidator);
        return (T) this;
    }

    /**
     * Creates new instance of {@link StringIdentifierGenerator}. Provides defaults of some of the parameters are missing, wherever possible.
     * @return a new instance
     * @throws NullPointerException if parameter is missing and can't supply default value for it
     */
    public StringIdentifierGenerator build() {
        RandomGenerator randomGenerator = this.randomGenerator == null ? new SecureRandom() : this.randomGenerator;
        RuleValidator ruleValidator;
        if (this.ruleValidators == null || this.ruleValidators.isEmpty()) {
            ruleValidator = StringIdentifierGenerator.DEFAULT_VALIDATOR;
        } else if (this.ruleValidators.size() == 1) {
            ruleValidator = this.ruleValidators.get(0);
        } else {
            ruleValidator = new CompositeRuleValidator(this.ruleValidators);
        }
        GeneratorRule generatorRule = Objects.requireNonNull(getGeneratorRule(), "Missing generator rule");
        return new StringIdentifierGenerator(randomGenerator, generatorRule, ruleValidator);
    }

    /**
     * Factory method, returning the rule to use.
     * <br>
     * Override wherever applicable.
     * @return generator rule to use
     * @throws IllegalArgumentException in case of invalid rule setup
     */
    GeneratorRule getGeneratorRule() {
        return this.generatorRule;
    }
}
