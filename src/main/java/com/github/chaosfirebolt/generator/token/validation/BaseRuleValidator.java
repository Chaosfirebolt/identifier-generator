package com.github.chaosfirebolt.generator.token.validation;

import com.github.chaosfirebolt.generator.token.exception.InvalidGeneratorRuleException;
import com.github.chaosfirebolt.generator.token.rule.GeneratorRule;

import java.util.function.Predicate;

/**
 * Created by ChaosFire on 12/5/2021
 */
public class BaseRuleValidator implements RuleValidator {

    /**
     * Condition testing if the rule is valid
     */
    private final Predicate<GeneratorRule> ruleValidityCondition;
    /**
     * Creates error message in case of invalid rule
     */
    private final ErrorMessageCreator errorMessageCreator;

    /**
     * Constructs rule validator with the supplied validity condition.
     * @param ruleValidityCondition condition for rule validity
     * @param errorMessageCreator creator for error messages
     */
    public BaseRuleValidator(Predicate<GeneratorRule> ruleValidityCondition, ErrorMessageCreator errorMessageCreator) {
        this.ruleValidityCondition = ruleValidityCondition;
        this.errorMessageCreator = errorMessageCreator;
    }

    @Override
    public void validate(GeneratorRule rule) {
        boolean isValid = this.ruleValidityCondition.test(rule);
        if (!isValid) {
            String errorMessage = this.errorMessageCreator.create(rule);
            throw new InvalidGeneratorRuleException(errorMessage);
        }
    }
}