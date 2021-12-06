package com.github.chaosfirebolt.generator.token.rule;

import com.github.chaosfirebolt.generator.token.part.LowerAlphabeticTokenPart;

import java.util.Collections;

/**
 * {@link GeneratorRule} rule for lower case alphabetic tokens.
 * <br/>
 * Created by ChaosFire on 12/6/2021
 */
public class LowerAlphabeticGeneratorRule extends BaseGeneratorRule {

    public LowerAlphabeticGeneratorRule(int length) {
        super(Collections.singletonList(new LowerAlphabeticTokenPart(length)), length);
    }
}