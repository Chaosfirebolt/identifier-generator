package com.github.chaosfirebolt.generator.token.validation;

import com.github.chaosfirebolt.generator.token.rule.GeneratorRule;
import com.github.chaosfirebolt.generator.token.util.CalculationUtility;

import java.util.function.Predicate;

/**
 * This validator considers rules valid, only if the sum of the lengths of token parts equals the total length of the token.
 * <br/>
 * Created by ChaosFire on 12/5/2021
 */
public class StrictLengthRuleValidator extends BaseRuleValidator {

    private static final Predicate<GeneratorRule> CONDITION = rule -> {
        int sum = CalculationUtility.totalLength(rule.getParts());
        return rule.getLength() == sum;
    };
    private static final ErrorMessageCreator ERROR_MESSAGE_CREATOR = rule -> {
        int sum = CalculationUtility.totalLength(rule.getParts());
        return String.format("Required length of '%d' must be equal to sum of parts lengths, but was '%d'", rule.getLength(), sum);
    };

    public StrictLengthRuleValidator() {
        super(CONDITION, ERROR_MESSAGE_CREATOR);
    }
}