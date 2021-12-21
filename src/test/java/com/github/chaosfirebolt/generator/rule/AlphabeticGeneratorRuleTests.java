package com.github.chaosfirebolt.generator.rule;

import com.github.chaosfirebolt.generator.token.rule.AlphabeticGeneratorRule;

/**
 * Created by ChaosFire on 21-Dec-21
 */
public class AlphabeticGeneratorRuleTests extends GeneratorRuleTests {

    private static final int LOWER_CASE_LENGTH = 7;
    private static final int UPPER_CASE_LENGTH = 9;

    public AlphabeticGeneratorRuleTests() {
        super(new AlphabeticGeneratorRule(LOWER_CASE_LENGTH, UPPER_CASE_LENGTH));
    }

    @Override
    protected int expectedNumberOfParts() {
        return 2;
    }

    @Override
    protected int expectedLength() {
        return LOWER_CASE_LENGTH + UPPER_CASE_LENGTH;
    }

    @Override
    protected int expectedMinLength() {
        return LOWER_CASE_LENGTH + UPPER_CASE_LENGTH;
    }
}