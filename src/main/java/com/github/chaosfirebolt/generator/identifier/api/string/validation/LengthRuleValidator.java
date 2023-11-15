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

package com.github.chaosfirebolt.generator.identifier.api.string.validation;

import com.github.chaosfirebolt.generator.identifier.api.string.rule.GeneratorRule;
import com.github.chaosfirebolt.generator.identifier.internal.util.CalculationUtility;
import org.apiguardian.api.API;

import java.util.function.Predicate;

/**
 * This validator considers rules valid, only if the sum of the lengths of identifier parts equals the total length of the identifier.
 * <br>
 * Created by ChaosFire on 12/5/2021
 */
@API(status = API.Status.STABLE, since = "2.0.0")
public class LengthRuleValidator extends BaseRuleValidator {

    private static final Predicate<GeneratorRule> CONDITION = rule -> {
        int sum = CalculationUtility.totalLength(rule.getParts());
        return rule.getLength() == sum;
    };
    private static final ErrorMessageCreator ERROR_MESSAGE_CREATOR = rule -> {
        int sum = CalculationUtility.totalLength(rule.getParts());
        return String.format("Required length of '%d' must be equal to sum of parts lengths, which is '%d'", rule.getLength(), sum);
    };

    /**
     * Constructs new LengthRuleValidator with preconfigured condition for rule validity and error message creator.
     */
    public LengthRuleValidator() {
        super(CONDITION, ERROR_MESSAGE_CREATOR);
    }
}
