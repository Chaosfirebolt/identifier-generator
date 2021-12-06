package com.github.chaosfirebolt.generator.token;

import com.github.chaosfirebolt.generator.token.part.TokenPart;
import com.github.chaosfirebolt.generator.token.rule.GeneratorRule;
import com.github.chaosfirebolt.generator.token.validation.RuleValidator;
import com.github.chaosfirebolt.generator.token.validation.StrictLengthRuleValidator;

import java.security.SecureRandom;
import java.util.Random;

/**
 * Base class for generating string tokens.
 * <br/>
 * Created by ChaosFire on 12/5/2021
 */
public class StringTokenGenerator extends BaseTokenGenerator<String> {

    protected final Random random;
    protected final GeneratorRule generatorRule;
    private final RuleValidator ruleValidator;

    public StringTokenGenerator(GeneratorRule generatorRule) {
        this(generatorRule, new StrictLengthRuleValidator());
    }

    public StringTokenGenerator(GeneratorRule generatorRule, RuleValidator ruleValidator) {
        this(new SecureRandom(), generatorRule, ruleValidator);
    }

    public StringTokenGenerator(Random random, GeneratorRule generatorRule, RuleValidator ruleValidator) {
        this.random = random;
        this.generatorRule = generatorRule;
        this.ruleValidator = ruleValidator;
        this.validateRule();
    }

    private void validateRule() {
        this.ruleValidator.validate(this.generatorRule);
    }

    @Override
    public String generate() {
        //TODO extract generation in a method
        char[] token = new char[this.generatorRule.getLength()];
        int tokenIndex = 0;
        for (TokenPart part : this.generatorRule.getParts()) {
            for (int i = 0; i < part.getLength(); i++) {
                int randomIndex = this.random.nextInt(part.getLength());
                char nextChar = part.getCharacters().get(randomIndex);
                token[tokenIndex++] = nextChar;
            }
        }
        //TODO shuffle char array
        return new String(token);
    }

    @Override
    public String generate(int tokenLength) {
        //TODO generate using supplied length
        //TODO if length ==, just like generate, if length < get 1 char from each part, then the rest at random, if length > just like generate, then the rest at random
        //TODO shuffle char array
        return null;
    }
}