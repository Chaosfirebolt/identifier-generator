package com.github.chaosfirebolt.generator.token.rule;

import com.github.chaosfirebolt.generator.token.part.NumericTokenPart;

import java.util.Collections;

/**
 * {@link GeneratorRule} rule for numeric tokens.
 * <br>
 * Created by ChaosFire on 12/6/2021
 */
public class NumericGeneratorRule extends BaseGeneratorRule {

    /**
     * Constructs new NumericGeneratorRule with specified length for numeric part.
     * @param length desired length of numeric part
     */
    public NumericGeneratorRule(int length) {
        super(Collections.singletonList(new NumericTokenPart(length)));
    }
}