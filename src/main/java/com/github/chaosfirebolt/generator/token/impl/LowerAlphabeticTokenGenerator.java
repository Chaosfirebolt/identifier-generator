package com.github.chaosfirebolt.generator.token.impl;

import com.github.chaosfirebolt.generator.token.StringTokenGenerator;
import com.github.chaosfirebolt.generator.token.rule.LowerAlphabeticGeneratorRule;
import com.github.chaosfirebolt.generator.token.validation.RuleValidator;

import java.util.List;
import java.util.Random;

/**
 * Implementation generating lower case tokens.
 * <br>
 * Created by ChaosFire on 18-Dec-21
 */
public class LowerAlphabeticTokenGenerator extends StringTokenGenerator {

    /**
     * Constructs new instance of token generator, with desired length for lower case characters.
     * @param length length of lower case characters
     * @throws IllegalArgumentException if length is negative or zero, the underlying TokenPart constructor will throw this exception
     */
    public LowerAlphabeticTokenGenerator(int length) {
        super(new LowerAlphabeticGeneratorRule(length));
    }

    /**
     * Constructs new instance of token generator, with desired length for lower case characters and provided validators.
     * @param length length of lower case characters
     * @param ruleValidators validators for the rule
     * @throws IllegalArgumentException if length is negative or zero, the underlying TokenPart constructor will throw this exception
     */
    public LowerAlphabeticTokenGenerator(int length, List<RuleValidator> ruleValidators) {
        super(new LowerAlphabeticGeneratorRule(length), ruleValidators);
    }

    /**
     * Constructs new instance of token generator, with desired length for lower case characters provided validators and random.
     * @param random random number generator
     * @param length length of lower case characters
     * @param ruleValidators validators for the rule
     * @throws IllegalArgumentException if length is negative or zero, the underlying TokenPart constructor will throw this exception
     */
    public LowerAlphabeticTokenGenerator(Random random, int length, List<RuleValidator> ruleValidators) {
        super(random, new LowerAlphabeticGeneratorRule(length), ruleValidators);
    }
}