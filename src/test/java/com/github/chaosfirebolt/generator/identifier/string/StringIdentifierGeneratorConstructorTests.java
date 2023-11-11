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

package com.github.chaosfirebolt.generator.identifier.string;

import com.github.chaosfirebolt.generator.identifier.exception.InvalidGeneratorRuleException;
import com.github.chaosfirebolt.generator.identifier.string.part.NumericPart;
import com.github.chaosfirebolt.generator.identifier.string.part.Part;
import com.github.chaosfirebolt.generator.identifier.string.rule.BaseGeneratorRule;
import com.github.chaosfirebolt.generator.identifier.string.rule.GeneratorRule;
import com.github.chaosfirebolt.generator.identifier.string.util.CharacterUtility;
import com.github.chaosfirebolt.generator.identifier.string.validation.BaseRuleValidator;
import com.github.chaosfirebolt.generator.identifier.string.validation.NoOpRuleValidator;
import com.github.chaosfirebolt.generator.identifier.string.validation.RuleValidator;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by ChaosFire on 23-Dec-21
 */
public class StringIdentifierGeneratorConstructorTests {

    private static final String ERROR_MESSAGE = "identifier minimum length can not be less than 50";
    private static final String ERROR_EXCEPTION_THROWN = "Creating new instance should not have thrown exception";
    private static final RuleValidator VALIDATOR = new BaseRuleValidator(rule -> rule.getMinLength() >= 50, rule -> ERROR_MESSAGE);

    @Test
    public void ruleValidatorsConstructor_RuleDoesNotConform_ShouldThrowInvalidGeneratorRuleException() {
        Part part = new NumericPart(60, 10);
        GeneratorRule rule = new BaseGeneratorRule(Collections.singletonList(part));
        InvalidGeneratorRuleException exception = assertThrows(InvalidGeneratorRuleException.class, () -> new StringIdentifierGenerator(rule, VALIDATOR));
        assertEquals(ERROR_MESSAGE, exception.getMessage());
    }

    @Test
    public void ruleValidatorsConstructor_RuleDoesConform_Test1_ShouldNotThrow() {
        Part part = new NumericPart(60, 50);
        GeneratorRule rule = new BaseGeneratorRule(Collections.singletonList(part));
        assertDoesNotThrow(() -> new StringIdentifierGenerator(rule, VALIDATOR), ERROR_EXCEPTION_THROWN);
    }

    @Test
    public void ruleValidatorsConstructor_RuleDoesConform_Test2_ShouldNotThrow() {
        Part part = new NumericPart(60, 55);
        GeneratorRule rule = new BaseGeneratorRule(Collections.singletonList(part));
        assertDoesNotThrow(() -> new StringIdentifierGenerator(rule, VALIDATOR), ERROR_EXCEPTION_THROWN);
    }

    @Test
    public void ruleValidatorsConstructor_NoOpValidator_ShouldNotThrow() {
        List<Part> parts = Collections.singletonList(new UnvalidatedPart(0, CharacterUtility.characterListFromIntRange(48, 58)));
        GeneratorRule rule = new BaseGeneratorRule(parts);
        assertDoesNotThrow(() -> new StringIdentifierGenerator(rule, new NoOpRuleValidator()), ERROR_EXCEPTION_THROWN);
    }

    @Test
    public void randomRuleValidatorsConstructor_RuleDoesNotConform_ShouldThrowInvalidGeneratorRuleException() {
        Part part = new NumericPart(60, 10);
        GeneratorRule rule = new BaseGeneratorRule(Collections.singletonList(part));
        InvalidGeneratorRuleException exception = assertThrows(InvalidGeneratorRuleException.class, () -> new StringIdentifierGenerator(new Random(), rule, VALIDATOR));
        assertEquals(ERROR_MESSAGE, exception.getMessage());
    }

    @Test
    public void randomRuleValidatorsConstructor_RuleDoesConform_Test1_ShouldNotThrow() {
        Part part = new NumericPart(60, 50);
        GeneratorRule rule = new BaseGeneratorRule(Collections.singletonList(part));
        assertDoesNotThrow(() -> new StringIdentifierGenerator(new Random(), rule, VALIDATOR), ERROR_EXCEPTION_THROWN);
    }

    @Test
    public void randomRuleValidatorsConstructor_RuleDoesConform_Test2_ShouldNotThrow() {
        Part part = new NumericPart(60, 55);
        GeneratorRule rule = new BaseGeneratorRule(Collections.singletonList(part));
        assertDoesNotThrow(() -> new StringIdentifierGenerator(new Random(), rule, VALIDATOR), ERROR_EXCEPTION_THROWN);
    }

    private record UnvalidatedPart(int length, List<Character> characters) implements Part {

        @Override
        public int getLength() {
            return length();
        }

        @Override
        public List<Character> getCharacters() {
            return characters();
        }
    }
}