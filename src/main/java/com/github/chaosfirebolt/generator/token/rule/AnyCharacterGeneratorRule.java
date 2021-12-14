package com.github.chaosfirebolt.generator.token.rule;

import com.github.chaosfirebolt.generator.token.part.*;

import java.util.Arrays;

/**
 * {@link GeneratorRule} rule for tokens consisting of alphanumeric and special characters.
 * <br/>
 * Created by ChaosFire on 12/6/2021
 */
public class AnyCharacterGeneratorRule extends BaseGeneratorRule {
    /**
     * Constructs new AnyCharacterGeneratorRule with specified lengths for alphanumeric and special character parts.
     * @param lowerCaseLength desired length of lower case part
     * @param upperCaseLength desired length of upper case part
     * @param numericLength desired length of numeric part
     * @param specialCharLength desired length of special characters part
     */
    public AnyCharacterGeneratorRule(int lowerCaseLength, int upperCaseLength, int numericLength, int specialCharLength) {
        super(Arrays.asList(new LowerAlphabeticTokenPart(lowerCaseLength), new UpperAlphabeticTokenPart(upperCaseLength),
                new NumericTokenPart(numericLength), new SpecialCharacterTokenPart(specialCharLength)));
    }
}