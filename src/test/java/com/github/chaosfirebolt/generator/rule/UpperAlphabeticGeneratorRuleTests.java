package com.github.chaosfirebolt.generator.rule;

import com.github.chaosfirebolt.generator.token.rule.UpperAlphabeticGeneratorRule;

/**
 * Created by ChaosFire on 21-Dec-21
 */
public class UpperAlphabeticGeneratorRuleTests extends GeneratorRuleTests {

    private static final int UPPER_CASE_LENGTH = 9;

    public UpperAlphabeticGeneratorRuleTests() {
        super(new UpperAlphabeticGeneratorRule(UPPER_CASE_LENGTH));
    }

    @Override
    protected int expectedNumberOfParts() {
        return 1;
    }

    @Override
    protected int expectedLength() {
        return UPPER_CASE_LENGTH;
    }

    @Override
    protected int expectedMinLength() {
        return UPPER_CASE_LENGTH;
    }
}