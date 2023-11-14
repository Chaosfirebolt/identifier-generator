/*
 * Copyright 2021-2023 Boyan Georgiev
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

package com.github.chaosfirebolt.generator.identifier.api.string;

import com.github.chaosfirebolt.generator.identifier.api.BaseIdentifierGenerator;
import com.github.chaosfirebolt.generator.identifier.api.exception.InvalidGeneratorRuleException;
import com.github.chaosfirebolt.generator.identifier.api.string.validation.*;
import com.github.chaosfirebolt.generator.identifier.api.string.part.Part;
import com.github.chaosfirebolt.generator.identifier.api.string.rule.GeneratorRule;
import com.github.chaosfirebolt.generator.identifier.internal.util.ShuffleUtility;
import org.apiguardian.api.API;

import java.security.SecureRandom;
import java.util.List;
import java.util.function.ToIntFunction;
import java.util.random.RandomGenerator;

/**
 * Base class for generating string identifiers.
 * <br>
 * Created by ChaosFire on 12/5/2021
 */
@API(status = API.Status.STABLE)
public class StringIdentifierGenerator extends BaseIdentifierGenerator<String> {

    /**
     * The default rule validators.
     */
    public static final RuleValidator DEFAULT_VALIDATOR = new CompositeRuleValidator(new LengthRuleValidator(), new MinimumLengthRuleValidator(), new MinimumLengthEqualOrLessThanLengthRuleValidator());

    /**
     * The random generator.
     */
    protected final RandomGenerator random;

    /**
     * Rule to be used for generation of string identifiers.
     */
    protected final GeneratorRule generatorRule;

    /**
     * Constructs instance of StringIdentifierGenerator, using provided {@link GeneratorRule}, a new instance of {@link SecureRandom} and default list of rule validators.
     * @param generatorRule rule to be used for identifier generation
     * @throws InvalidGeneratorRuleException if provided rule does not conform with default RuleValidators
     */
    public StringIdentifierGenerator(GeneratorRule generatorRule) {
        this(generatorRule, DEFAULT_VALIDATOR);
    }

    /**
     * Constructs instance of StringIdentifierGenerator, using provided {@link GeneratorRule}, provided list of {@link RuleValidator}, and a new instance of {@link  SecureRandom}.
     *
     * @param generatorRule rule to be used for identifier generation
     * @param ruleValidator validator to be used for validation of provided generator rule
     * @throws InvalidGeneratorRuleException if provided rule does not conform with provided RuleValidators
     */
    public StringIdentifierGenerator(GeneratorRule generatorRule, RuleValidator ruleValidator) {
        this(new SecureRandom(), generatorRule, ruleValidator);
    }

    /**
     * Constructs instance of StringIdentifierGenerator, using provided {@link GeneratorRule}, provided list of {@link RuleValidator}, and provided {@link RandomGenerator}.
     * @param random random number generator
     * @param generatorRule rule to be used for identifier generation
     * @param ruleValidator validator to be used for validation of provided generator rule
     * @throws InvalidGeneratorRuleException if provided rule does not conform with provided RuleValidators
     */
    public StringIdentifierGenerator(RandomGenerator random, GeneratorRule generatorRule, RuleValidator ruleValidator) {
        ruleValidator.validate(generatorRule);
        this.random = random;
        this.generatorRule = generatorRule;
    }

    @Override
    public String generate() {
        char[] identifier = new char[this.generatorRule.getLength()];
        this.fillIdentifier(identifier, 0, Part::getLength);
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
        identifierLength = Math.max(identifierLength, this.generatorRule.getMinLength());
        char[] identifier = new char[identifierLength];
        int identifierIndex = 0;
        identifierIndex = this.fillIdentifier(identifier, identifierIndex, Part::minLength);
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
