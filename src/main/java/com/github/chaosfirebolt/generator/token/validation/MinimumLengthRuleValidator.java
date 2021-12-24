package com.github.chaosfirebolt.generator.token.validation;

import com.github.chaosfirebolt.generator.token.rule.GeneratorRule;
import com.github.chaosfirebolt.generator.token.util.CalculationUtility;

import java.util.function.Predicate;

/**
 * This validator considers rules valid, only if the sum of the minimum lengths of token parts equals the minimum length of the token.
 * <br/>
 * Created by ChaosFire on 12-Dec-21
 */
public class MinimumLengthRuleValidator extends BaseRuleValidator {

    private static final Predicate<GeneratorRule> CONDITION = rule -> {
        int sum = CalculationUtility.minimumLength(rule.getParts());
        return rule.getMinLength() == sum;
    };
    private static final ErrorMessageCreator ERROR_MESSAGE_CREATOR = rule -> {
        int sum = CalculationUtility.minimumLength(rule.getParts());
        return String.format("Required minimum length of '%d' must be equal to sum of parts minimum lengths, which is '%d'", rule.getMinLength(), sum);
    };

    /**
     * Constructs new MinimumLengthRuleValidator with preconfigured condition for rule validity and error message creator.
     */
    public MinimumLengthRuleValidator() {
        super(CONDITION, ERROR_MESSAGE_CREATOR);
    }
}