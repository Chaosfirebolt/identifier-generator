package com.github.chaosfirebolt.generator.token.impl;

import com.github.chaosfirebolt.generator.token.StringTokenGenerator;
import com.github.chaosfirebolt.generator.token.rule.AnyCharacterGeneratorRule;
import com.github.chaosfirebolt.generator.token.validation.RuleValidator;

import java.util.List;
import java.util.Random;

/**
 * Implementation generating tokens consisting of alphanumeric and special characters.
 * <br>
 * Created by ChaosFire on 18-Dec-21
 */
public class AnyCharacterTokenGenerator extends StringTokenGenerator {

    /**
     * Constructs new instance of token generator, with desired lengths for lower case, upper case, special and numeric characters.
     * @param lowerCaseLength length of lower case characters
     * @param upperCaseLength length of upper case characters
     * @param numericLength length of numeric characters
     * @param specialCharLength length of special characters
     * @throws IllegalArgumentException if any of the length parameters is negative or zero, the underlying TokenPart constructors will throw this exception
     */
    public AnyCharacterTokenGenerator(int lowerCaseLength, int upperCaseLength, int numericLength, int specialCharLength) {
        super(new AnyCharacterGeneratorRule(lowerCaseLength, upperCaseLength, numericLength, specialCharLength));
    }

    /**
     * Constructs new instance of token generator, with desired lengths for lower case, upper case, special and numeric characters, and provided validators.
     * @param lowerCaseLength length of lower case characters
     * @param upperCaseLength length of upper case characters
     * @param numericLength length of numeric characters
     * @param specialCharLength length of special characters
     * @param ruleValidators validators for the rule
     * @throws IllegalArgumentException if any of the length parameters is negative or zero, the underlying TokenPart constructors will throw this exception
     */
    public AnyCharacterTokenGenerator(int lowerCaseLength, int upperCaseLength, int numericLength, int specialCharLength, List<RuleValidator> ruleValidators) {
        super(new AnyCharacterGeneratorRule(lowerCaseLength, upperCaseLength, numericLength, specialCharLength), ruleValidators);
    }

    /**
     * Constructs new instance of token generator, with desired lengths for lower case, upper case, special and numeric characters, provided validators and random.
     * @param random random number generator
     * @param lowerCaseLength length of lower case characters
     * @param upperCaseLength length of upper case characters
     * @param numericLength length of numeric characters
     * @param specialCharLength length of special characters
     * @param ruleValidators validators for the rule
     * @throws IllegalArgumentException if any of the length parameters is negative or zero, the underlying TokenPart constructors will throw this exception
     */
    public AnyCharacterTokenGenerator(Random random, int lowerCaseLength, int upperCaseLength, int numericLength, int specialCharLength, List<RuleValidator> ruleValidators) {
        super(random, new AnyCharacterGeneratorRule(lowerCaseLength, upperCaseLength, numericLength, specialCharLength), ruleValidators);
    }
}