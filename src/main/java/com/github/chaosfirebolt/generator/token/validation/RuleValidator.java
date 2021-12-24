package com.github.chaosfirebolt.generator.token.validation;

import com.github.chaosfirebolt.generator.token.rule.GeneratorRule;

/**
 * Represent validator for a {@link GeneratorRule}
 * <br>
 * Created by ChaosFire on 12/5/2021
 */
public interface RuleValidator {

    /**
     * Validates supplied rule.
     * @param rule rule to be validated
     * @throws com.github.chaosfirebolt.generator.token.exception.InvalidGeneratorRuleException if the validator considers tested rule invalid
     */
    void validate(GeneratorRule rule);
}