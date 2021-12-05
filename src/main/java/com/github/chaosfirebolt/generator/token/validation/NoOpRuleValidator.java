package com.github.chaosfirebolt.generator.token.validation;

import com.github.chaosfirebolt.generator.token.rule.GeneratorRule;

import java.util.function.Predicate;

/**
 * Validator, which considers every rule valid.
 * <br/>
 * Created by ChaosFire on 12/5/2021
 */
public class NoOpRuleValidator extends BaseRuleValidator {

    private static final Predicate<GeneratorRule> ALWAYS_VALID = rule -> true;
    private static final ErrorMessageCreator NO_MESSAGE = rule -> null;

    public NoOpRuleValidator() {
        super(ALWAYS_VALID, NO_MESSAGE);
    }
}