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

package com.github.chaosfirebolt.generator.identifier;

import com.github.chaosfirebolt.generator.identifier.api.BaseIdentifierGenerator;
import com.github.chaosfirebolt.generator.identifier.api.IdentifierGenerator;
import com.github.chaosfirebolt.generator.identifier.api.exception.TooManyAttemptsException;
import com.github.chaosfirebolt.generator.identifier.api.string.builders.StringGeneratorBuilders;
import com.github.chaosfirebolt.generator.identifier.api.string.builders.StringIdentifierGenerator;
import com.github.chaosfirebolt.generator.identifier.api.string.UuidStringIdentifierGenerator;
import com.github.chaosfirebolt.generator.identifier.api.string.part.NumericPart;
import com.github.chaosfirebolt.generator.identifier.api.string.part.SpecialCharacterPart;
import com.github.chaosfirebolt.generator.identifier.api.string.part.UpperAlphabeticPart;
import com.github.chaosfirebolt.generator.identifier.api.string.rule.BaseGeneratorRule;
import com.github.chaosfirebolt.generator.identifier.api.string.rule.GeneratorRule;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;

import java.lang.reflect.Field;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeFalse;
import static org.mockito.Mockito.doReturn;

/**
 * Created by ChaosFire on 22-Dec-21
 */
public class IdentifierGeneratorMethodTests {

    private static final String MOCK_ERROR_MESSAGE = "Mocking generator did not work";

    private Set<String> existingIdentifiers;
    private Predicate<String> uniqueCondition;

    @BeforeEach
    public void setUp() {
        this.existingIdentifiers = new HashSet<>();
        this.uniqueCondition = identifier -> this.existingIdentifiers.add(identifier);
    }

    @AfterEach
    public void tearDown() {
        this.existingIdentifiers.clear();
        this.existingIdentifiers = null;
    }

    @ParameterizedTest
    @MethodSource("generatorsWithExpectedLength")
    public void generateIdentifier_IdentifierShouldHaveCorrectLength(IdentifierGenerator<String> identifierGenerator, int expectedLength) {
        String identifier = identifierGenerator.generate();
        assertEquals(expectedLength, identifier.length());
    }

    private static List<Arguments> generatorsWithExpectedLength() {
        return allData().stream().map(args -> Arguments.of(args.generator(), args.expectedLength())).collect(Collectors.toList());
    }

    private static List<IdentifierArguments> allData() {
        GeneratorRule generatorRule = new BaseGeneratorRule(Arrays.asList(new SpecialCharacterPart(11), new UpperAlphabeticPart(111), new NumericPart(111)));
        return Arrays.asList(
                IdentifierArguments.of(StringGeneratorBuilders.alphabeticIdentifierGeneratorBuilder()
                        .setLowerCaseLength(15)
                        .setUpperCaseLength(15)
                        .build(), 10, 30, 30),
                IdentifierArguments.of(StringGeneratorBuilders.alphaNumericIdentifierGeneratorBuilder()
                        .setUpperCaseLength(15)
                        .setLowerCaseLength(15)
                        .setNumericLength(11)
                        .build(), 5, 41, 41),
                IdentifierArguments.of(StringGeneratorBuilders.anyCharacterIdentifierGeneratorBuilder()
                        .setLowerCaseLength(11)
                        .setUpperCaseLength(12)
                        .setNumericLength(7)
                        .setSpecialCharacterLength(3)
                        .build(), 9, 33, 33),
                IdentifierArguments.of(StringGeneratorBuilders.lowerAlphabeticIdentifierGeneratorBuilder()
                        .setLowerCaseLength(20)
                        .build(), 11, 20, 20),
                IdentifierArguments.of(StringGeneratorBuilders.numericIdentifierGeneratorBuilder()
                        .setNumericLength(29)
                        .build(), 100, 29, 29),
                IdentifierArguments.of(new StringIdentifierGenerator(generatorRule), 49, 233, 233),
                IdentifierArguments.of(StringGeneratorBuilders.stringIdentifierGeneratorBuilder().setGeneratorRule(generatorRule).build(), 49, 233, 233),
                IdentifierArguments.of(StringGeneratorBuilders
                        .upperAlphabeticIdentifierGeneratorBuilder()
                        .setUpperCaseLength(111)
                        .build(), 50, 111, 111),
                IdentifierArguments.of(new UuidStringIdentifierGenerator(), 20, 36, 36)
        );
    }

    @ParameterizedTest
    @MethodSource("generators")
    public void generateIdentifier_GenerateMany_AllShouldBeDifferent(IdentifierGenerator<String> identifierGenerator) {
        for (int i = 0; i < 100; i++) {
            String identifier = identifierGenerator.generate();
            boolean exists = !this.existingIdentifiers.add(identifier);
            assertFalse(exists, () -> String.format("Identifier '%s' already generated", identifier));
        }
    }

    @ParameterizedTest
    @MethodSource("generatorsWithExpectedLength")
    public void generateUniqueIdentifier_IdentifierShouldHaveCorrectLength(IdentifierGenerator<String> identifierGenerator, int expectedLength) {
        String identifier = identifierGenerator.generate(this.uniqueCondition);
        assertEquals(expectedLength, identifier.length());
    }

    @ParameterizedTest
    @MethodSource("generators")
    public void generateUniqueIdentifier_ExpectedNumberOfUniqueIdentifiersShouldBeGenerated(IdentifierGenerator<String> identifierGenerator) {
        int targetCount = 1_000;
        for (int i = 0; i < targetCount; i++) {
            identifierGenerator.generate(this.uniqueCondition);
        }
        assertEquals(targetCount, this.existingIdentifiers.size());
    }

    private static List<Arguments> generators() {
        return allData().stream().map(IdentifierArguments::generator).map(Arguments::of).collect(Collectors.toList());
    }

    @ParameterizedTest
    @MethodSource("generatorsWithMaxAttempts")
    public void generateUniqueIdentifier_MaximumAttemptsReached_ShouldThrowTooManyAttemptsExceptionWithCorrectMessage(IdentifierGenerator<String> identifierGenerator, int maxAttempts) {
        String toBeReturned = identifierGenerator.generate();
        IdentifierGenerator<String> generator = Mockito.spy(identifierGenerator);
        doReturn(toBeReturned).when(generator).generate();

        String firstGenerated = generator.generate(this.uniqueCondition);
        assertEquals(toBeReturned, firstGenerated, MOCK_ERROR_MESSAGE);

        TooManyAttemptsException exc = assertThrows(TooManyAttemptsException.class, () -> generator.generate(this.uniqueCondition));
        String expectedMessage = "Maximum number of attempts to generate unique identifier reached - " + maxAttempts;
        assertEquals(expectedMessage, exc.getMessage());
    }

    private static List<Arguments> generatorsWithMaxAttempts() {
        return allData().stream()
                .peek(args -> args.generator().setMaximumAttempts(args.maxAttempts()))
                .map(args -> Arguments.of(args.generator(), args.maxAttempts()))
                .collect(Collectors.toList());
    }

    @ParameterizedTest
    @MethodSource("generatorsWithExpectedMinLength")
    public void generateIdentifierWithLength_LengthIsLessThanMinimum_IdentifierShouldHaveCorrectLength(IdentifierGenerator<String> identifierGenerator, int expectedMinLength) {
        int length = expectedMinLength / 2;
        String identifier = identifierGenerator.generate(length);
        assertEquals(expectedMinLength, identifier.length());
    }

    private static List<Arguments> generatorsWithExpectedMinLength() {
        return allData().stream().map(args -> Arguments.of(args.generator(), args.expectedMinLength())).collect(Collectors.toList());
    }

    @ParameterizedTest
    @MethodSource("generatorsWithExpectedMinLength")
    public void generateIdentifierWithLength_LengthIsMoreThanMinimum_IdentifierShouldHaveCorrectLength(IdentifierGenerator<String> identifierGenerator, int expectedMinLength) {
        assumeFalse(identifierGenerator.getClass().equals(UuidStringIdentifierGenerator.class), "Test not applicable for uuid identifier generator");
        int length = expectedMinLength * 2;
        String identifier = identifierGenerator.generate(length);
        assertEquals(length, identifier.length());
    }

    @ParameterizedTest
    @MethodSource("generators")
    public void generateIdentifierWithLength_GenerateMany_AllShouldBeDifferent(IdentifierGenerator<String> identifierGenerator) {
        for (int i = 0; i < 100; i++) {
            String identifier = identifierGenerator.generate(100);
            boolean exists = !this.existingIdentifiers.add(identifier);
            assertFalse(exists, () -> String.format("identifier '%s' already generated", identifier));
        }
    }

    @ParameterizedTest
    @MethodSource("generatorsWithExpectedMinLength")
    public void generateUniqueIdentifierWithLength_LengthIsLessThanMinimum_IdentifierShouldHaveCorrectLength(IdentifierGenerator<String> identifierGenerator, int expectedMinLength) {
        int length = expectedMinLength / 2;
        String identifier = identifierGenerator.generate(length, this.uniqueCondition);
        assertEquals(expectedMinLength, identifier.length());
    }

    @ParameterizedTest
    @MethodSource("generatorsWithExpectedMinLength")
    public void generateUniqueIdentifierWithLength_LengthIsMoreThanMinimum_IdentifierShouldHaveCorrectLength(IdentifierGenerator<String> identifierGenerator, int expectedMinLength) {
        assumeFalse(identifierGenerator.getClass().equals(UuidStringIdentifierGenerator.class), "Test not applicable for uuid identifier generator");
        int length = expectedMinLength * 2;
        String identifier = identifierGenerator.generate(length, this.uniqueCondition);
        assertEquals(length, identifier.length());
    }

    @ParameterizedTest
    @MethodSource("generators")
    public void generateUniqueIdentifierWithLength_ExpectedNumberOfUniqueIdentifiersShouldBeGenerated(IdentifierGenerator<String> identifierGenerator) {
        int targetCount = 1_000;
        for (int i = 0; i < targetCount; i++) {
            identifierGenerator.generate(100, this.uniqueCondition);
        }
        assertEquals(targetCount, this.existingIdentifiers.size());
    }

    @ParameterizedTest
    @MethodSource("generatorsWithMaxAttempts")
    public void generateUniqueIdentifierWithLength_MaximumAttemptsReached_ShouldThrowTooManyAttemptsExceptionWithCorrectMessage(IdentifierGenerator<String> identifierGenerator, int maxAttempts) {
        int length = 100;
        String toBeReturned = identifierGenerator.generate(length);
        IdentifierGenerator<String> generator = Mockito.spy(identifierGenerator);
        doReturn(toBeReturned).when(generator).generate(length);

        String firstGenerated = generator.generate(length, this.uniqueCondition);
        assertEquals(toBeReturned, firstGenerated, MOCK_ERROR_MESSAGE);

        TooManyAttemptsException exc = assertThrows(TooManyAttemptsException.class, () -> generator.generate(length, this.uniqueCondition));
        String expectedMessage = "Maximum number of attempts to generate unique identifier reached - " + maxAttempts;
        assertEquals(expectedMessage, exc.getMessage());
    }

    @ParameterizedTest
    @MethodSource("generators")
    public void setMaximumAttempts_SetNegative_ValueShouldRemainUnchanged(BaseIdentifierGenerator<String> identifierGenerator) {
        identifierGenerator.setMaximumAttempts(-2);
        int actual = getMaxAttempts(identifierGenerator);
        assertEquals(-1, actual);
    }

    private static int getMaxAttempts(BaseIdentifierGenerator<String> generator) {
        try {
            Field field = getField(generator.getClass());
            field.setAccessible(true);
            Object value = field.get(generator);
            field.setAccessible(false);
            return (int) value;
        } catch (ReflectiveOperationException exc) {
            throw new RuntimeException(exc);
        }
    }

    private static Field getField(Class<?> clazz) {
        Field field = null;
        while (field == null) {
            try {
                field = clazz.getDeclaredField("maximumAttempts");
            } catch (NoSuchFieldException exc) {
                clazz = Objects.requireNonNull(clazz.getSuperclass(), "Can not get superclass of " + clazz);
            }
        }
        return field;
    }

    @ParameterizedTest
    @MethodSource("generators")
    public void setMaximumAttempts_SetZero_ValueShouldRemainUnchanged(BaseIdentifierGenerator<String> identifierGenerator) {
        identifierGenerator.setMaximumAttempts(0);
        int actual = getMaxAttempts(identifierGenerator);
        assertEquals(-1, actual);
    }

    @ParameterizedTest
    @MethodSource("generators")
    public void setMaximumAttempts_SetPositive_ValueShouldChange(BaseIdentifierGenerator<String> identifierGenerator) {
        int expected = 11;
        identifierGenerator.setMaximumAttempts(expected);
        int actual = getMaxAttempts(identifierGenerator);
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @MethodSource("generators")
    public void setMaximumAttempts_SetPositiveTwice_ValueShouldBeFirstSet(BaseIdentifierGenerator<String> identifierGenerator) {
        int expected = 20;
        identifierGenerator.setMaximumAttempts(expected);
        identifierGenerator.setMaximumAttempts(10);
        int actual = getMaxAttempts(identifierGenerator);
        assertEquals(expected, actual);
    }
}