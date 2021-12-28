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

package com.github.chaosfirebolt.generator.token.constructor;

import com.github.chaosfirebolt.generator.token.StringTokenGenerator;
import com.github.chaosfirebolt.generator.token.exception.InvalidGeneratorRuleException;
import com.github.chaosfirebolt.generator.token.part.TokenPart;
import com.github.chaosfirebolt.generator.token.rule.GeneratorRule;
import com.github.chaosfirebolt.generator.token.util.CharacterUtility;
import com.github.chaosfirebolt.generator.token.validation.MinimumLengthEqualOrLessThanLengthRuleValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Created by ChaosFire on 24-Dec-21
 */
public class StringTokenGeneratorValidationTests {

    private static final List<Character> CHARACTERS = CharacterUtility.characterListFromIntRange(97, 123);

    private static void assertException(Executable executable, String expectedMessage) {
        InvalidGeneratorRuleException exception = assertThrows(InvalidGeneratorRuleException.class, executable);
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void minimumLengthHigherThanLength_ShouldThrowInvalidGeneratorRuleException() {
        TokenPart part = new TestTokenPart(15, 10, CHARACTERS);
        GeneratorRule rule = new TestGeneratorRule(part, 15, 16);
        String expectedErrorMessage = "Required minimum length of '16' must be equal to or less than total length, which is '15'";
        assertException(() -> new StringTokenGenerator(rule, Collections.singletonList(new MinimumLengthEqualOrLessThanLengthRuleValidator())), expectedErrorMessage);
    }

    @Test
    public void ruleMinimumLengthLessThanPartsMinLength_ShouldThrowInvalidGeneratorRuleException() {
        TokenPart part = new TestTokenPart(15, 10, CHARACTERS);
        GeneratorRule rule = new TestGeneratorRule(part, 15, 9);
        String expectedErrorMessage = "Required minimum length of '9' must be equal to sum of parts minimum lengths, which is '10'";
        assertException(() -> new StringTokenGenerator(rule), expectedErrorMessage);
    }

    @Test
    public void ruleMinimumLengthMoreThanPartsMinLength_ShouldThrowInvalidGeneratorRuleException() {
        TokenPart part = new TestTokenPart(15, 10, CHARACTERS);
        GeneratorRule rule = new TestGeneratorRule(part, 15, 11);
        String expectedErrorMessage = "Required minimum length of '11' must be equal to sum of parts minimum lengths, which is '10'";
        assertException(() -> new StringTokenGenerator(rule), expectedErrorMessage);
    }

    @Test
    public void ruleLengthLessThanPartsLength_ShouldThrowInvalidGeneratorRuleException() {
        TokenPart part = new TestTokenPart(15, 10, CHARACTERS);
        GeneratorRule rule = new TestGeneratorRule(part, 14, 10);
        String expectedErrorMessage = "Required length of '14' must be equal to sum of parts lengths, which is '15'";
        assertException(() -> new StringTokenGenerator(rule), expectedErrorMessage);
    }

    @Test
    public void ruleLengthMoreThanPartsLength_ShouldThrowInvalidGeneratorRuleException() {
        TokenPart part = new TestTokenPart(15, 10, CHARACTERS);
        GeneratorRule rule = new TestGeneratorRule(part, 16, 10);
        String expectedErrorMessage = "Required length of '16' must be equal to sum of parts lengths, which is '15'";
        assertException(() -> new StringTokenGenerator(rule), expectedErrorMessage);
    }

    private static final class TestTokenPart implements TokenPart {

        private final int length;
        private final int minLength;
        private final List<Character> characters;

        private TestTokenPart(int length, int minLength, List<Character> characters) {
            this.length = length;
            this.minLength = minLength;
            this.characters = characters;
        }

        @Override
        public int getLength() {
            return this.length;
        }

        @Override
        public int getMinLength() {
            return this.minLength;
        }

        @Override
        public List<Character> getCharacters() {
            return this.characters;
        }
    }

    private static final class TestGeneratorRule implements GeneratorRule {

        private final List<TokenPart> parts;
        private final int length;
        private final int minLength;

        private TestGeneratorRule(TokenPart part, int length, int minLength) {
            this.parts = Collections.singletonList(part);
            this.length = length;
            this.minLength = minLength;
        }

        @Override
        public List<TokenPart> getParts() {
            return this.parts;
        }

        @Override
        public int getLength() {
            return this.length;
        }

        @Override
        public int getMinLength() {
            return this.minLength;
        }
    }
}