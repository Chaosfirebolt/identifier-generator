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
import org.apiguardian.api.API;

import java.util.List;

/**
 * Validator composed of other validators.
 */
@API(status = API.Status.STABLE, since = "2.0.0")
public class CompositeRuleValidator implements RuleValidator {

    private final List<RuleValidator> delegates;

    /**
     * @param delegates array of the actual validators
     * @throws IllegalArgumentException if {@code delegates} is null or empty
     */
    public CompositeRuleValidator(RuleValidator... delegates) {
        this(delegates == null ? List.of() : List.of(delegates));
    }

    /**
     * @param delegates list of the actual validators
     * @throws IllegalArgumentException if {@code delegates} is null or empty
     */
    public CompositeRuleValidator(List<RuleValidator> delegates) {
        this.delegates = validateDelegates(delegates);
    }

    private static List<RuleValidator> validateDelegates(List<RuleValidator> validators) {
        if (validators == null || validators.isEmpty()) {
            throw new IllegalArgumentException("Missing rule validators");
        }
        return validators;
    }

    @Override
    public void validate(GeneratorRule rule) {
        this.delegates.forEach(delegate -> delegate.validate(rule));
    }
}
