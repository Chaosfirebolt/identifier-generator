package com.github.chaosfirebolt.generator.token.rule;

import com.github.chaosfirebolt.generator.token.part.LowerAlphabeticTokenPart;
import com.github.chaosfirebolt.generator.token.part.NumericTokenPart;
import com.github.chaosfirebolt.generator.token.part.UpperAlphabeticTokenPart;

import java.util.Arrays;

/**
 * {@link GeneratorRule} rule for alphanumeric tokens.
 * <br/>
 * Created by ChaosFire on 12/6/2021
 */
public class AlphaNumericGeneratorRule extends BaseGeneratorRule {

    public AlphaNumericGeneratorRule(int lowerCaseLength, int upperCaseLength, int numericLength) {
        super(Arrays.asList(new LowerAlphabeticTokenPart(lowerCaseLength), new UpperAlphabeticTokenPart(upperCaseLength), new NumericTokenPart(numericLength)));
    }
}