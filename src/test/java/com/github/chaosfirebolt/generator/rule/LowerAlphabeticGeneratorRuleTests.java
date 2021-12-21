package com.github.chaosfirebolt.generator.rule;

import com.github.chaosfirebolt.generator.token.rule.LowerAlphabeticGeneratorRule;

/**
 * Created by ChaosFire on 21-Dec-21
 */
public class LowerAlphabeticGeneratorRuleTests extends GeneratorRuleTests {

    private static final int LOWER_CASE_LENGTH = 99;

    public LowerAlphabeticGeneratorRuleTests() {
        super(new LowerAlphabeticGeneratorRule(LOWER_CASE_LENGTH));
    }

    @Override
    protected int expectedNumberOfParts() {
        return 1;
    }

    @Override
    protected int expectedLength() {
        return LOWER_CASE_LENGTH;
    }

    @Override
    protected int expectedMinLength() {
        return LOWER_CASE_LENGTH;
    }
}