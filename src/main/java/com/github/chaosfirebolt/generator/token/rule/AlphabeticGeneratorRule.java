package com.github.chaosfirebolt.generator.token.rule;

import com.github.chaosfirebolt.generator.token.part.LowerAlphabeticTokenPart;
import com.github.chaosfirebolt.generator.token.part.UpperAlphabeticTokenPart;

import java.util.Arrays;

/**
 * {@link GeneratorRule} rule for upper and lower case alphabetic tokens.
 * <br/>
 * Created by ChaosFire on 12/6/2021
 */
public class AlphabeticGeneratorRule extends BaseGeneratorRule {

    /**
     * Constructs new AlphabeticGeneratorRule with specified lengths for lower case and upper case parts
     * @param lowerCaseLength desired length of lower case part
     * @param upperCaseLength desired length of upper case part
     */
    public AlphabeticGeneratorRule(int lowerCaseLength, int upperCaseLength) {
        super(Arrays.asList(new LowerAlphabeticTokenPart(lowerCaseLength), new UpperAlphabeticTokenPart(upperCaseLength)));
    }
}