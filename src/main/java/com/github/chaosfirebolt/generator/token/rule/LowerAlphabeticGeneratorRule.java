package com.github.chaosfirebolt.generator.token.rule;

import com.github.chaosfirebolt.generator.token.part.LowerAlphabeticTokenPart;

import java.util.Collections;

/**
 * {@link GeneratorRule} rule for lower case alphabetic tokens.
 * <br>
 * Created by ChaosFire on 12/6/2021
 */
public class LowerAlphabeticGeneratorRule extends BaseGeneratorRule {

    /**
     * Constructs new LowerAlphabeticGeneratorRule with specified length for lower case alphabetic part.
     * @param length desired length of lower case alphabetic part
     */
    public LowerAlphabeticGeneratorRule(int length) {
        super(Collections.singletonList(new LowerAlphabeticTokenPart(length)));
    }
}