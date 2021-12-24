package com.github.chaosfirebolt.generator.token.validation;

import com.github.chaosfirebolt.generator.token.rule.GeneratorRule;

import java.util.function.Predicate;

/**
 * This validator considers rules valid, only if the minimum length of the rule is less than or equals rule desired length.
 * <br>
 * Created by ChaosFire on 12-Dec-21
 */
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