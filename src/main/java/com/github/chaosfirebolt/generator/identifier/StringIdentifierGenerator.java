/*
 * Copyright 2021 Boyan Georgiev
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.chaosfirebolt.generator.identifier;

import com.github.chaosfirebolt.generator.identifier.part.Part;
import com.github.chaosfirebolt.generator.identifier.rule.GeneratorRule;
import com.github.chaosfirebolt.generator.identifier.util.ShuffleUtility;
import com.github.chaosfirebolt.generator.identifier.validation.MinimumLengthEqualOrLessThanLengthRuleValidator;
import com.github.chaosfirebolt.generator.identifier.validation.MinimumLengthRuleValidator;
import com.github.chaosfirebolt.generator.identifier.validation.RuleValidator;
import com.github.chaosfirebolt.generator.identifier.validation.LengthRuleValidator;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.ToIntFunction;

/**
 * Base class for generating string identifiers.
 * <br>
 * Created by ChaosFire on 12/5/2021
 */
public class StringIdentifierGenerator extends BaseIdentifierGenerator<String> {

    private static final List<RuleValidator> DEFAULT_VALIDATORS = Arrays.asList(new LengthRuleValidator(), new MinimumLengthRuleValidator(),
            new MinimumLengthEqualOrLessThanLengthRuleValidator());
    private static final ToIntFunction<Part> PART_LENGTH_FUNC = Part::getLength;
    private static final ToIntFunction<Part> PART_MIN_LENGTH_FUNC = Part::getMinLength;

    /**
     * The random generator.
     */
    protected final Random random;

    /**
     * Rule to be used for generation of string identifiers.
     */
    protected final GeneratorRule generatorRule;

    /**
     * Constructs instance of StringIdentifierGenerator, using provided {@link GeneratorRule}, a new instance of Secure Random and default list of rule validators.
     * @param generatorRule rule to be used for identifier generation
     * @throws com.github.chaosfirebolt.generator.identifier.exception.InvalidGeneratorRuleException if provided rule does not conform with default RuleValidators
     */
    public StringIdentifierGenerator(GeneratorRule generatorRule) {
        this(generatorRule, DEFAULT_VALIDATORS);
    }

    /**
     * Constructs instance of StringIdentifierGenerator, using provided {@link GeneratorRule}, provided list of {@link RuleValidator}, and a new instance of Secure Random.
     * @param generatorRule rule to be used for identifier generation
     * @param ruleValidators validators to be used for validation of provided generator rule
     * @throws com.github.chaosfirebolt.generator.identifier.exception.InvalidGeneratorRuleException if provided rule does not conform with provided RuleValidators
     */
    public StringIdentifierGenerator(GeneratorRule generatorRule, List<RuleValidator> ruleValidators) {
        this(new SecureRandom(), generatorRule, ruleValidators);
    }

    /**
     * Constructs instance of StringIdentifierGenerator, using provided {@link GeneratorRule}, provided list of {@link RuleValidator}, and provided {@link Random}.
     * @param random random number generator
     * @param generatorRule rule to be used for identifier generation
     * @param ruleValidators validators to be used for validation of provided generator rule
     * @throws com.github.chaosfirebolt.generator.identifier.exception.InvalidGeneratorRuleException if provided rule does not conform with provided RuleValidators
     */
    public StringIdentifierGenerator(Random random, GeneratorRule generatorRule, List<RuleValidator> ruleValidators) {
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
        char[] identifier = new char[this.generatorRule.getLength()];
        this.fillIdentifier(identifier, 0, PART_LENGTH_FUNC);
        this.shuffleArray(identifier);
        return new String(identifier);
    }

    private int fillIdentifier(char[] identifier, int identifierIndex, ToIntFunction<Part> partSizeFunction) {
        for (Part part : this.generatorRule.getParts()) {
            int partSize = partSizeFunction.applyAsInt(part);
            for (int i = 0; i < partSize; i++) {
                char nextChar = this.getRandomElement(part.getCharacters());
                identifier[identifierIndex++] = nextChar;
            }
        }
        return identifierIndex;
    }

    /**
     * Shuffles supplied array using implementation of Fisher-Yates algorithm.
     * <br>
     * Override this method to use different shuffling strategy.
     * @param array array to be shuffled
     */
    protected void shuffleArray(char[] array) {
        ShuffleUtility.shuffleFisherYates(this.random, array);
    }

    @Override
    public String generate(int identifierLength) {
        //in this implementation identifier length can not be less than minimum length from the rule
        if (identifierLength < this.generatorRule.getMinLength()) {
            identifierLength = this.generatorRule.getMinLength();
        }
        char[] identifier = new char[identifierLength];
        int identifierIndex = 0;
        identifierIndex = this.fillIdentifier(identifier, identifierIndex, PART_MIN_LENGTH_FUNC);
        int lastIndex = identifierLength - 1;
        while (identifierIndex < lastIndex) {
            Part randomPart = this.getRandomElement(this.generatorRule.getParts());
            char nextChar = this.getRandomElement(randomPart.getCharacters());
            identifier[identifierIndex++] = nextChar;
        }
        this.shuffleArray(identifier);
        return new String(identifier);
    }

    private <E> E getRandomElement(List<E> list) {
        int randomIndex = this.random.nextInt(list.size());
        return list.get(randomIndex);
    }
}