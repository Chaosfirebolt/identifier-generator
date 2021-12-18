package com.github.chaosfirebolt.generator.token.impl;

import com.github.chaosfirebolt.generator.token.StringTokenGenerator;
import com.github.chaosfirebolt.generator.token.rule.AlphaNumericGeneratorRule;
import com.github.chaosfirebolt.generator.token.validation.RuleValidator;

import java.util.List;
import java.util.Random;

/**
 * Implementation generating alphanumeric tokens.
 * <br/>
 * Created by ChaosFire on 18-Dec-21
 */
public class AlphaNumericTokenGenerator extends StringTokenGenerator {

    /**
     * Constructs new instance of token generator, with desired lengths for lower case, upper case and numeric characters.
     * @param lowerCaseLength length of lower case characters
     * @param upperCaseLength length of upper case characters
     * @param numericLength length of numeric characters
     */
    public AlphaNumericTokenGenerator(int lowerCaseLength, int upperCaseLength, int numericLength) {
        super(new AlphaNumericGeneratorRule(lowerCaseLength, upperCaseLength, numericLength));
    }

    /**
     * Constructs new instance of token generator, with desired lengths for lower case, upper case, numeric characters and provided validators.
     * @param lowerCaseLength length of lower case characters
     * @param upperCaseLength length of upper case characters
     * @param numericLength length of numeric characters
     * @param ruleValidators validators for the rule
     */
    public AlphaNumericTokenGenerator(int lowerCaseLength, int upperCaseLength, int numericLength, List<RuleValidator> ruleValidators) {
        super(new AlphaNumericGeneratorRule(lowerCaseLength, upperCaseLength, numericLength), ruleValidators);
    }

    /**
     * Constructs new instance of token generator, with desired lengths for lower case, upper case, numeric characters provided validators and random.
     * @param random random number generator
     * @param lowerCaseLength length of lower case characters
     * @param upperCaseLength length of upper case characters
     * @param numericLength length of numeric characters
     * @param ruleValidators validators for the rule
     */
    public AlphaNumericTokenGenerator(Random random, int lowerCaseLength, int upperCaseLength, int numericLength, List<RuleValidator> ruleValidators) {
        super(random, new AlphaNumericGeneratorRule(lowerCaseLength, upperCaseLength, numericLength), ruleValidators);
    }
}