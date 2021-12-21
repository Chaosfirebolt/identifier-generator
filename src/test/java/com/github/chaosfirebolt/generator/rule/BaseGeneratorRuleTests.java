package com.github.chaosfirebolt.generator.rule;

import com.github.chaosfirebolt.generator.token.part.NumericTokenPart;
import com.github.chaosfirebolt.generator.token.part.SpecialCharacterTokenPart;
import com.github.chaosfirebolt.generator.token.rule.BaseGeneratorRule;

import java.util.Arrays;

/**
 * Created by ChaosFire on 21-Dec-21
 */
public class BaseGeneratorRuleTests extends GeneratorRuleTests {

    public BaseGeneratorRuleTests() {
        super(new BaseGeneratorRule(Arrays.asList(new SpecialCharacterTokenPart(3, 1), new NumericTokenPart(19, 17))));
    }

    @Override
    protected int expectedNumberOfParts() {
        return 2;
    }

    @Override
    protected int expectedLength() {
        return 22;
    }

    @Override
    protected int expectedMinLength() {
        return 18;
    }
}