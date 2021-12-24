package com.github.chaosfirebolt.generator.token.rule;

import com.github.chaosfirebolt.generator.token.part.UpperAlphabeticTokenPart;

import java.util.Collections;

/**
 * {@link GeneratorRule} rule for upper case alphabetic tokens.
 * <br>
 * Created by ChaosFire on 12/6/2021
 */
public class UpperAlphabeticGeneratorRule extends BaseGeneratorRule {

    /**
     * Constructs new UpperAlphabeticGeneratorRule with specified length for upper case alphabetic part.
     * @param length desired length of upper case alphabetic part
     */
    public UpperAlphabeticGeneratorRule(int length) {
        super(Collections.singletonList(new UpperAlphabeticTokenPart(length)));
    }
}