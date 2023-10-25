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

package com.github.chaosfirebolt.generator.identifier.validation;

import com.github.chaosfirebolt.generator.identifier.rule.GeneratorRule;

import java.util.function.Predicate;

/**
 * Validator, which considers every rule valid.
 * <br>
 * Created by ChaosFire on 12/5/2021
 */
public class NoOpRuleValidator extends BaseRuleValidator {

    private static Predicate<GeneratorRule> ALWAYS_VALID;
    private static ErrorMessageCreator NO_MESSAGE;

    /**
     * Constructs new instance of NoOpRuleValidator
     */
    public NoOpRuleValidator() {
        super(getCondition(), getMessageCreator());
    }

    private static Predicate<GeneratorRule> getCondition() {
        if (ALWAYS_VALID == null) {
            ALWAYS_VALID = rule -> true;
        }
        return ALWAYS_VALID;
    }

    private static ErrorMessageCreator getMessageCreator() {
        if (NO_MESSAGE == null) {
            NO_MESSAGE = rule -> null;
        }
        return NO_MESSAGE;
    }
}