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

package com.github.chaosfirebolt.generator.identifier.string.validation;

import com.github.chaosfirebolt.generator.identifier.string.rule.GeneratorRule;
import org.apiguardian.api.API;

import java.util.function.Predicate;

/**
 * This validator considers rules valid, only if the minimum length of the rule is less than or equals rule desired length.
 * <br>
 * Created by ChaosFire on 12-Dec-21
 */
@API(status = API.Status.STABLE)
public class MinimumLengthEqualOrLessThanLengthRuleValidator extends BaseRuleValidator {

    private static final Predicate<GeneratorRule> CONDITION = rule -> rule.getMinLength() <= rule.getLength();
    private static final ErrorMessageCreator ERROR_MESSAGE_CREATOR = rule ->
            String.format("Required minimum length of '%d' must be equal to or less than total length, which is '%d'", rule.getMinLength(), rule.getLength());

    /**
     * Constructs new MinimumLengthEqualOrLessThanLengthRuleValidator with preconfigured condition for rule validity and error message creator.
     */
    public MinimumLengthEqualOrLessThanLengthRuleValidator() {
        super(CONDITION, ERROR_MESSAGE_CREATOR);
    }
}
