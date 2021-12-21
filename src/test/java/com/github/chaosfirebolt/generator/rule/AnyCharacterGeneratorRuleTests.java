package com.github.chaosfirebolt.generator.rule;

import com.github.chaosfirebolt.generator.token.rule.AnyCharacterGeneratorRule;

/**
 * Created by ChaosFire on 21-Dec-21
 */
public class AnyCharacterGeneratorRuleTests extends GeneratorRuleTests {

    private static final int LOWER_CASE_LENGTH = 7;
    private static final int UPPER_CASE_LENGTH = 9;
    private static final int NUMERIC_LENGTH = 5;
    private static final int SPECIAL_LENGTH = 3;

    public AnyCharacterGeneratorRuleTests() {
        super(new AnyCharacterGeneratorRule(LOWER_CASE_LENGTH, UPPER_CASE_LENGTH, NUMERIC_LENGTH, SPECIAL_LENGTH));
    }

    @Override
    protected int expectedNumberOfParts() {
        return 4;
    }

    @Override
    protected int expectedLength() {
        return LOWER_CASE_LENGTH + UPPER_CASE_LENGTH + NUMERIC_LENGTH + SPECIAL_LENGTH;
    }

    @Override
    protected int expectedMinLength() {
        return LOWER_CASE_LENGTH + UPPER_CASE_LENGTH + NUMERIC_LENGTH + SPECIAL_LENGTH;
    }
}