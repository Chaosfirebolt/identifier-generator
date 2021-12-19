package com.github.chaosfirebolt.generator.token.impl;

import com.github.chaosfirebolt.generator.token.StringTokenGenerator;
import com.github.chaosfirebolt.generator.token.rule.NumericGeneratorRule;
import com.github.chaosfirebolt.generator.token.validation.RuleValidator;

import java.util.List;
import java.util.Random;

/**
 * Implementation generating numeric tokens.
 * <br/>
 * Created by ChaosFire on 18-Dec-21
 */
public class NumericTokenGenerator extends StringTokenGenerator {

    /**
     * Constructs new instance of token generator, with desired length for numeric characters.
     * @param length length of numeric characters
     */
    public NumericTokenGenerator(int length) {
        super(new NumericGeneratorRule(length));
    }

    /**
     * Constructs new instance of token generator, with desired length for numeric characters and provided validators.
     * @param length length of numeric characters
     * @param ruleValidators validators for the rule
     */
    public NumericTokenGenerator(int length, List<RuleValidator> ruleValidators) {
        super(new NumericGeneratorRule(length), ruleValidators);
    }

    /**
     * Constructs new instance of token generator, with desired length for numeric characters, provided validators and random.
     * @param random random number generator
     * @param length length of numeric characters
     * @param ruleValidators validators for the rule
     */
    public NumericTokenGenerator(Random random, int length, List<RuleValidator> ruleValidators) {
        super(random, new NumericGeneratorRule(length), ruleValidators);
    }
}