package com.github.chaosfirebolt.generator.token.validation;

import com.github.chaosfirebolt.generator.token.rule.GeneratorRule;

import java.util.function.Predicate;

/**
 * Created by ChaosFire on 12-Dec-21
 */
public class MinimumLengthEqualOrLessThanLength extends BaseRuleValidator {

    private static final Predicate<GeneratorRule> CONDITION = rule -> rule.getMinLength() <= rule.getLength();
    private static final ErrorMessageCreator ERROR_MESSAGE_CREATOR = rule ->
            String.format("Required minimum length of '%d' must be equal or less than total length, but was '%d'", rule.getMinLength(), rule.getLength());

    public MinimumLengthEqualOrLessThanLength() {
        super(CONDITION, ERROR_MESSAGE_CREATOR);
    }
}