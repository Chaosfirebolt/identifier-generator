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

package com.github.chaosfirebolt.generator.identifier.validation;

import com.github.chaosfirebolt.generator.identifier.exception.InvalidGeneratorRuleException;
import com.github.chaosfirebolt.generator.identifier.rule.GeneratorRule;

import java.util.function.Predicate;

/**
 * Created by ChaosFire on 12/5/2021
 */
public class BaseRuleValidator implements RuleValidator {

    /**
     * Condition testing if the rule is valid
     */
    private final Predicate<GeneratorRule> ruleValidityCondition;
    /**
     * Creates error message in case of invalid rule
     */
    private final ErrorMessageCreator errorMessageCreator;

    /**
     * Constructs rule validator with the supplied validity condition.
     * @param ruleValidityCondition condition for rule validity
     * @param errorMessageCreator creator for error messages
     */
    public BaseRuleValidator(Predicate<GeneratorRule> ruleValidityCondition, ErrorMessageCreator errorMessageCreator) {
        this.ruleValidityCondition = ruleValidityCondition;
        this.errorMessageCreator = errorMessageCreator;
    }

    @Override
    public void validate(GeneratorRule rule) {
        boolean isValid = this.ruleValidityCondition.test(rule);
        if (!isValid) {
            String errorMessage = this.errorMessageCreator.create(rule);
            throw new InvalidGeneratorRuleException(errorMessage);
        }
    }
}