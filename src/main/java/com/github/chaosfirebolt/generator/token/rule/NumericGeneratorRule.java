package com.github.chaosfirebolt.generator.token.rule;

import com.github.chaosfirebolt.generator.token.part.NumericTokenPart;

import java.util.Collections;

/**
 * {@link GeneratorRule} rule for numeric tokens.
 * <br/>
 * Created by ChaosFire on 12/6/2021
 */
public class NumericGeneratorRule extends BaseGeneratorRule {

    public NumericGeneratorRule(int length) {
        super(Collections.singletonList(new NumericTokenPart(length)), length);
    }
}