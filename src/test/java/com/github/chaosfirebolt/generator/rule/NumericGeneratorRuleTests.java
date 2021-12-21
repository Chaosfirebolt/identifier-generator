package com.github.chaosfirebolt.generator.rule;

import com.github.chaosfirebolt.generator.token.rule.NumericGeneratorRule;

/**
 * Created by ChaosFire on 21-Dec-21
 */
public class NumericGeneratorRuleTests extends GeneratorRuleTests {

    private static final int NUMERIC_LENGTH = 5;

    public NumericGeneratorRuleTests() {
        super(new NumericGeneratorRule(NUMERIC_LENGTH));
    }

    @Override
    protected int expectedNumberOfParts() {
        return 1;
    }

    @Override
    protected int expectedLength() {
        return NUMERIC_LENGTH;
    }

    @Override
    protected int expectedMinLength() {
        return NUMERIC_LENGTH;
    }
}