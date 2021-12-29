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

import com.github.chaosfirebolt.generator.identifier.rule.GeneratorRule;
import com.github.chaosfirebolt.generator.identifier.util.CalculationUtility;

import java.util.function.Predicate;

/**
 * This validator considers rules valid, only if the sum of the minimum lengths of token parts equals the minimum length of the token.
 * <br>
 * Created by ChaosFire on 12-Dec-21
 */
public class MinimumLengthRuleValidator extends BaseRuleValidator {

    private static final Predicate<GeneratorRule> CONDITION = rule -> {
        int sum = CalculationUtility.minimumLength(rule.getParts());
        return rule.getMinLength() == sum;
    };
    private static final ErrorMessageCreator ERROR_MESSAGE_CREATOR = rule -> {
        int sum = CalculationUtility.minimumLength(rule.getParts());
        return String.format("Required minimum length of '%d' must be equal to sum of parts minimum lengths, which is '%d'", rule.getMinLength(), sum);
    };

    /**
     * Constructs new MinimumLengthRuleValidator with preconfigured condition for rule validity and error message creator.
     */
    public MinimumLengthRuleValidator() {
        super(CONDITION, ERROR_MESSAGE_CREATOR);
    }
}