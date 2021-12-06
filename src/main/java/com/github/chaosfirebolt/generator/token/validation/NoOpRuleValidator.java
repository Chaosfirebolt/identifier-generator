package com.github.chaosfirebolt.generator.token.validation;

import com.github.chaosfirebolt.generator.token.rule.GeneratorRule;

import java.util.function.Predicate;

/**
 * Validator, which considers every rule valid.
 * <br/>
 * Created by ChaosFire on 12/5/2021
 */
public class NoOpRuleValidator extends BaseRuleValidator {

    private static Predicate<GeneratorRule> ALWAYS_VALID;
    private static ErrorMessageCreator NO_MESSAGE;

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