package com.github.chaosfirebolt.generator.token.rule;

import com.github.chaosfirebolt.generator.token.part.*;

import java.util.Arrays;

/**
 * {@link GeneratorRule} rule for tokens consisting of alphanumeric and special characters.
 * <br/>
 * Created by ChaosFire on 12/6/2021
 */
public class AnyCharacterRuleGenerator extends BaseGeneratorRule {

    public AnyCharacterRuleGenerator(int lowerCaseLength, int upperCaseLength, int numericLength, int specialCharLength) {
        super(Arrays.asList(new LowerAlphabeticTokenPart(lowerCaseLength), new UpperAlphabeticTokenPart(upperCaseLength),
                new NumericTokenPart(numericLength), new SpecialCharacterTokenPart(specialCharLength)));
    }
}