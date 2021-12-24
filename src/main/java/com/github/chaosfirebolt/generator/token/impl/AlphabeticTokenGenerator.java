package com.github.chaosfirebolt.generator.token.impl;

import com.github.chaosfirebolt.generator.token.StringTokenGenerator;
import com.github.chaosfirebolt.generator.token.rule.AlphabeticGeneratorRule;
import com.github.chaosfirebolt.generator.token.validation.RuleValidator;

import java.util.List;
import java.util.Random;

/**
 * Implementation generating alphabetic tokens.
 * <br>
 * Created by ChaosFire on 18-Dec-21
 */
public class AlphabeticTokenGenerator extends StringTokenGenerator {

    /**
     * Constructs new instance of token generator, with desired lengths for lower and upper case characters.
     * @param lowerCaseLength length of lower case characters
     * @param upperCaseLength length of upper case characters
     * @throws IllegalArgumentException if any of the length parameters is negative or zero, the underlying TokenPart constructors will throw this exception
     */
    public AlphabeticTokenGenerator(int lowerCaseLength, int upperCaseLength) {
        super(new AlphabeticGeneratorRule(lowerCaseLength, upperCaseLength));
    }

    /**
     * Constructs new instance of token generator, with desired lengths for lower and upper case characters, and provided validators.
     * @param lowerCaseLength length of lower case characters
     * @param upperCaseLength length of upper case characters
     * @param ruleValidators validators for the rule
     * @throws IllegalArgumentException if any of the length parameters is negative or zero, the underlying TokenPart constructors will throw this exception
     */
    public AlphabeticTokenGenerator(int lowerCaseLength, int upperCaseLength, List<RuleValidator> ruleValidators) {
        super(new AlphabeticGeneratorRule(lowerCaseLength, upperCaseLength), ruleValidators);
    }

    /**
     * Constructs new instance of token generator, with desired lengths for lower and upper case characters, provided validators and random.
     * @param random random number generator
     * @param lowerCaseLength length of lower case characters
     * @param upperCaseLength length of upper case characters
     * @param ruleValidators validators for the rule
     * @throws IllegalArgumentException if any of the length parameters is negative or zero, the underlying TokenPart constructors will throw this exception
     */
    public AlphabeticTokenGenerator(Random random, int lowerCaseLength, int upperCaseLength, List<RuleValidator> ruleValidators) {
        super(random, new AlphabeticGeneratorRule(lowerCaseLength, upperCaseLength), ruleValidators);
    }
}