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

package com.github.chaosfirebolt.generator.identifier.validation;

import com.github.chaosfirebolt.generator.identifier.rule.GeneratorRule;

/**
 * Represent validator for a {@link GeneratorRule}
 * <br>
 * Created by ChaosFire on 12/5/2021
 */
public interface RuleValidator {

    /**
     * Validates supplied rule.
     * @param rule rule to be validated
     * @throws com.github.chaosfirebolt.generator.identifier.exception.InvalidGeneratorRuleException if the validator considers tested rule invalid
     */
    void validate(GeneratorRule rule);
}