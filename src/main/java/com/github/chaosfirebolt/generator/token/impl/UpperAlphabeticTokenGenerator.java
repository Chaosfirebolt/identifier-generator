package com.github.chaosfirebolt.generator.token.impl;

import com.github.chaosfirebolt.generator.token.StringTokenGenerator;
import com.github.chaosfirebolt.generator.token.rule.UpperAlphabeticGeneratorRule;
import com.github.chaosfirebolt.generator.token.validation.RuleValidator;

import java.util.List;
import java.util.Random;

/**
 * Implementation generating upper case tokens.
 * <br>
 * Created by ChaosFire on 18-Dec-21
 */
public class UpperAlphabeticTokenGenerator extends StringTokenGenerator {

    /**
     * Constructs new instance of token generator, with desired length for upper case characters.
     * @param length length of upper case characters
     * @throws IllegalArgumentException if length is negative or zero, the underlying TokenPart constructor will throw this exception
     */
    public UpperAlphabeticTokenGenerator(int length) {
        super(new UpperAlphabeticGeneratorRule(length));
    }

    /**
     * Constructs new instance of token generator, with desired length for upper case characters and provided validators.
     * @param length length of upper case characters
     * @param ruleValidators validators for the rule
     * @throws IllegalArgumentException if length is negative or zero, the underlying TokenPart constructor will throw this exception
     */
    public UpperAlphabeticTokenGenerator(int length, List<RuleValidator> ruleValidators) {
        super(new UpperAlphabeticGeneratorRule(length), ruleValidators);
    }

    /**
     * Constructs new instance of token generator, with desired length for upper case characters, provided validators and random.
     * @param random random number generator
     * @param length length of upper case characters
     * @param ruleValidators validators for the rule
     * @throws IllegalArgumentException if length is negative or zero, the underlying TokenPart constructor will throw this exception
     */
    public UpperAlphabeticTokenGenerator(Random random, int length, List<RuleValidator> ruleValidators) {
        super(random, new UpperAlphabeticGeneratorRule(length), ruleValidators);
    }
}