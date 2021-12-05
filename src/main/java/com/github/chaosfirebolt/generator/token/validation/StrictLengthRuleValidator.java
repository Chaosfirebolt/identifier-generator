package com.github.chaosfirebolt.generator.token.validation;

/**
 * This validator considers rules valid, only if the sum of the lengths of token parts equals the total length of the token.
 * <br/>
 * Created by ChaosFire on 12/5/2021
 */
public class StrictLengthRuleValidator extends BaseRuleValidator {

    //TODO provide implementations for super constructor parameters
    public StrictLengthRuleValidator() {
        super(null, null);
    }
}