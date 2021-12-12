package com.github.chaosfirebolt.generator.token;

import com.github.chaosfirebolt.generator.token.part.TokenPart;
import com.github.chaosfirebolt.generator.token.rule.GeneratorRule;
import com.github.chaosfirebolt.generator.token.util.ShuffleUtility;
import com.github.chaosfirebolt.generator.token.validation.MinimumLengthEqualOrLessThanLength;
import com.github.chaosfirebolt.generator.token.validation.MinimumLengthRuleValidator;
import com.github.chaosfirebolt.generator.token.validation.RuleValidator;
import com.github.chaosfirebolt.generator.token.validation.LengthRuleValidator;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.ToIntFunction;

/**
 * Base class for generating string tokens.
 * <br/>
 * Created by ChaosFire on 12/5/2021
 */
public class StringTokenGenerator extends BaseTokenGenerator<String> {

    private static final List<RuleValidator> DEFAULT_VALIDATORS = Arrays.asList(new LengthRuleValidator(), new MinimumLengthRuleValidator(), new MinimumLengthEqualOrLessThanLength());
    private static final ToIntFunction<TokenPart> PART_LENGTH_FUNC = TokenPart::getLength;
    private static final ToIntFunction<TokenPart> PART_MIN_LENGTH_FUNC = TokenPart::getMinLength;

    /**
     * The random generator.
     */
    protected final Random random;

    /**
     * Rule to be used for generation of string tokens.
     */
    protected final GeneratorRule generatorRule;

    /**
     * Constructs instance of StringTokenGenerator, using provided {@link GeneratorRule}, a new instance of Secure Random and default list of rule validators.
     * @param generatorRule rule to be used for token generation
     * @throws com.github.chaosfirebolt.generator.token.exception.InvalidGeneratorRuleException if provided rule does not conform with default RuleValidators
     */
    public StringTokenGenerator(GeneratorRule generatorRule) {
        this(generatorRule, DEFAULT_VALIDATORS);
    }

    /**
     * Constructs instance of StringTokenGenerator, using provided {@link GeneratorRule}, provided list of {@link RuleValidator}, and a new instance of Secure Random.
     * @param generatorRule rule to be used for token generation
     * @param ruleValidators validators to be used for validation of provided generator rule
     * @throws com.github.chaosfirebolt.generator.token.exception.InvalidGeneratorRuleException if provided rule does not conform with provided RuleValidators
     */
    public StringTokenGenerator(GeneratorRule generatorRule, List<RuleValidator> ruleValidators) {
        this(new SecureRandom(), generatorRule, ruleValidators);
    }

    /**
     * Constructs instance of StringTokenGenerator, using provided {@link GeneratorRule}, provided list of {@link RuleValidator}, and provided {@link Random}.
     * @param random random number generator
     * @param generatorRule rule to be used for token generation
     * @param ruleValidators validators to be used for validation of provided generator rule
     */
    public StringTokenGenerator(Random random, GeneratorRule generatorRule, List<RuleValidator> ruleValidators) {
        this.random = random;
        this.generatorRule = generatorRule;
        validateRule(ruleValidators, generatorRule);
    }

    private static void validateRule(List<RuleValidator> ruleValidators, GeneratorRule generatorRule) {
        for (RuleValidator ruleValidator : ruleValidators) {
            ruleValidator.validate(generatorRule);
        }
    }

    @Override
    public String generate() {
        char[] token = new char[this.generatorRule.getLength()];
        this.fillToken(token, 0, PART_LENGTH_FUNC);
        this.shuffleArray(token);
        return new String(token);
    }

    private int fillToken(char[] token, int tokenIndex, ToIntFunction<TokenPart> partSizeFunction) {
        for (TokenPart part : this.generatorRule.getParts()) {
            int partSize = partSizeFunction.applyAsInt(part);
            for (int i = 0; i < partSize; i++) {
                char nextChar = this.getRandomElement(part.getCharacters());
                token[tokenIndex++] = nextChar;
            }
        }
        return tokenIndex;
    }

    /**
     * Shuffles supplied array using implementation of Fisher-Yates algorithm.
     * <br/>
     * Override this method to use different shuffling strategy.
     * @param array array to be shuffled
     */
    protected void shuffleArray(char[] array) {
        ShuffleUtility.shuffleFisherYates(this.random, array);
    }

    @Override
    public String generate(int tokenLength) {
        //in this implementation token length can not be less than minimum length from the rule
        if (tokenLength < this.generatorRule.getMinLength()) {
            tokenLength = this.generatorRule.getMinLength();
        }
        char[] token = new char[tokenLength];
        int tokenIndex = 0;
        tokenIndex = this.fillToken(token, tokenIndex, PART_MIN_LENGTH_FUNC);
        int lastIndex = tokenLength - 1;
        while (tokenIndex < lastIndex) {
            TokenPart randomPart = this.getRandomElement(this.generatorRule.getParts());
            char nextChar = this.getRandomElement(randomPart.getCharacters());
            token[tokenIndex++] = nextChar;
        }
        this.shuffleArray(token);
        return new String(token);
    }

    private <E> E getRandomElement(List<E> list) {
        int randomIndex = this.random.nextInt(list.size());
        return list.get(randomIndex);
    }
}