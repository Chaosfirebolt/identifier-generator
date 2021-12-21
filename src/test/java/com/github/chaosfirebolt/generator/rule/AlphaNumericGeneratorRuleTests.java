package com.github.chaosfirebolt.generator.rule;

import com.github.chaosfirebolt.generator.token.rule.AlphaNumericGeneratorRule;

/**
 * Created by ChaosFire on 21-Dec-21
 */
public class AlphaNumericGeneratorRuleTests extends GeneratorRuleTests {

    private static final int LOWER_CASE_LENGTH = 7;
    private static final int UPPER_CASE_LENGTH = 9;
    private static final int NUMERIC_LENGTH = 5;

    public AlphaNumericGeneratorRuleTests() {
        super(new AlphaNumericGeneratorRule(LOWER_CASE_LENGTH, UPPER_CASE_LENGTH, NUMERIC_LENGTH));
    }

    @Override
    protected int expectedNumberOfParts() {
        return 3;
    }

    @Override
    protected int expectedLength() {
        return LOWER_CASE_LENGTH + UPPER_CASE_LENGTH + NUMERIC_LENGTH;
    }

    @Override
    protected int expectedMinLength() {
        return LOWER_CASE_LENGTH + UPPER_CASE_LENGTH + NUMERIC_LENGTH;
    }
}