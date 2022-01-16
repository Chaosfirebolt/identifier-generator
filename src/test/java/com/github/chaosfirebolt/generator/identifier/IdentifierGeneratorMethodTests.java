/*
 * Copyright 2021-2022 Boyan Georgiev
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

import com.github.chaosfirebolt.generator.identifier.exception.TooManyAttemptsException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.mockito.Mockito;
import org.slf4j.Logger;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;

/**
 * Created by ChaosFire on 22-Dec-21
 */
public abstract class IdentifierGeneratorMethodTests {

    private static final String MOCK_ERROR_MESSAGE = "Mocking generator did not work";

    private Set<String> existingIdentifiers;
    private IdentifierGenerator<String> identifierGenerator;
    private Predicate<String> uniqueCondition;
    private final Logger logger;
    private final int maxAttempts;
    private final int expectedLength;
    private final int expectedMinLength;

    protected IdentifierGeneratorMethodTests(Logger logger, int maxAttempts, int expectedLength, int expectedMinLength) {
        this.logger = logger;
        this.maxAttempts = maxAttempts;
        this.expectedLength = expectedLength;
        this.expectedMinLength = expectedMinLength;
    }

    @BeforeEach
    void setUp() {
        this.existingIdentifiers = new HashSet<>();
        this.uniqueCondition = identifier -> this.existingIdentifiers.add(identifier);
        BaseIdentifierGenerator<String> identifierGenerator = this.getGenerator();
        identifierGenerator.setMaximumAttempts(this.maxAttempts);
        this.identifierGenerator = identifierGenerator;
    }

    protected abstract BaseIdentifierGenerator<String> getGenerator();

    @AfterEach
    void tearDown() {
        this.existingIdentifiers.clear();
        this.existingIdentifiers = null;
        this.identifierGenerator = null;
    }

    @Test
    public void generateIdentifier_IdentifierShouldHaveCorrectLength() {
        String identifier = this.identifierGenerator.generate();
        this.logIdentifier(identifier);
        assertEquals(this.expectedLength, identifier.length());
    }

    private void logIdentifier(String identifier) {
        this.logger.info("{} generated identifier - {}", this.identifierGenerator.getClass().getSimpleName(), identifier);
    }

    @Test
    public void generateIdentifier_GenerateMany_AllShouldBeDifferent() {
        for (int i = 0; i < 100; i++) {
            String identifier = this.identifierGenerator.generate();
            this.logIdentifier(identifier);
            boolean exists = !this.existingIdentifiers.add(identifier);
            assertFalse(exists, () -> String.format("Identifier '%s' already generated", identifier));
        }
    }

    @Test
    public void generateUniqueIdentifier_IdentifierShouldHaveCorrectLength() {
        String identifier = this.identifierGenerator.generate(this.uniqueCondition);
        this.logIdentifier(identifier);
        assertEquals(this.expectedLength, identifier.length());
    }

    @Test
    @Timeout(1)
    public void generateUniqueIdentifier_ExpectedNumberOfUniqueIdentifiersShouldBeGenerated() {
        int targetCount = 10_000;
        for (int i = 0; i < targetCount; i++) {
            this.identifierGenerator.generate(this.uniqueCondition);
        }
        assertEquals(targetCount, this.existingIdentifiers.size());
    }

    @Test
    public void generateUniqueIdentifier_MaximumAttemptsReached_ShouldThrowTooManyAttemptsExceptionWithCorrectMessage() {
        String toBeReturned = this.identifierGenerator.generate();
        IdentifierGenerator<String> generator = Mockito.spy(this.identifierGenerator);
        doReturn(toBeReturned).when(generator).generate();

        String firstGenerated = generator.generate(this.uniqueCondition);
        assertEquals(toBeReturned, firstGenerated, MOCK_ERROR_MESSAGE);

        TooManyAttemptsException exc = assertThrows(TooManyAttemptsException.class, () -> generator.generate(this.uniqueCondition));
        String expectedMessage = "Maximum number of attempts to generate unique identifier reached - " + this.maxAttempts;
        assertEquals(expectedMessage, exc.getMessage());
    }

    @Test
    public void generateIdentifierWithLength_LengthIsLessThanMinimum_IdentifierShouldHaveCorrectLength() {
        int length = this.expectedMinLength / 2;
        String identifier = this.identifierGenerator.generate(length);
        this.logIdentifier(identifier);
        assertEquals(this.expectedMinLength, identifier.length());
    }

    @Test
    public void generateIdentifierWithLength_LengthIsMoreThanMinimum_IdentifierShouldHaveCorrectLength() {
        int length = this.expectedMinLength * 2;
        String identifier = this.identifierGenerator.generate(length);
        this.logIdentifier(identifier);
        assertEquals(length, identifier.length());
    }

    @Test
    public void generateIdentifierWithLength_GenerateMany_AllShouldBeDifferent() {
        for (int i = 0; i < 100; i++) {
            String identifier = this.identifierGenerator.generate(100);
            this.logIdentifier(identifier);
            boolean exists = !this.existingIdentifiers.add(identifier);
            assertFalse(exists, () -> String.format("identifier '%s' already generated", identifier));
        }
    }

    @Test
    public void generateUniqueIdentifierWithLength_LengthIsLessThanMinimum_IdentifierShouldHaveCorrectLength() {
        int length = this.expectedMinLength / 2;
        String identifier = this.identifierGenerator.generate(length, this.uniqueCondition);
        this.logIdentifier(identifier);
        assertEquals(this.expectedMinLength, identifier.length());
    }

    @Test
    public void generateUniqueIdentifierWithLength_LengthIsMoreThanMinimum_IdentifierShouldHaveCorrectLength() {
        int length = this.expectedMinLength * 2;
        String identifier = this.identifierGenerator.generate(length, this.uniqueCondition);
        this.logIdentifier(identifier);
        assertEquals(length, identifier.length());
    }

    @Test
    @Timeout(1)
    public void generateUniqueIdentifierWithLength_ExpectedNumberOfUniqueIdentifiersShouldBeGenerated() {
        int targetCount = 10_000;
        for (int i = 0; i < targetCount; i++) {
            this.identifierGenerator.generate(100, this.uniqueCondition);
        }
        assertEquals(targetCount, this.existingIdentifiers.size());
    }

    @Test
    public void generateUniqueIdentifierWithLength_MaximumAttemptsReached_ShouldThrowTooManyAttemptsExceptionWithCorrectMessage() {
        int length = 100;
        String toBeReturned = this.identifierGenerator.generate(length);
        IdentifierGenerator<String> generator = Mockito.spy(this.identifierGenerator);
        doReturn(toBeReturned).when(generator).generate(length);

        String firstGenerated = generator.generate(length, this.uniqueCondition);
        assertEquals(toBeReturned, firstGenerated, MOCK_ERROR_MESSAGE);

        TooManyAttemptsException exc = assertThrows(TooManyAttemptsException.class, () -> generator.generate(length, this.uniqueCondition));
        String expectedMessage = "Maximum number of attempts to generate unique identifier reached - " + this.maxAttempts;
        assertEquals(expectedMessage, exc.getMessage());
    }

    @Test
    public void setMaximumAttempts_SetNegative_ValueShouldRemainUnchanged() {
        BaseIdentifierGenerator<String> identifierGenerator = this.getGenerator();
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

    @Test
    public void setMaximumAttempts_SetZero_ValueShouldRemainUnchanged() {
        BaseIdentifierGenerator<String> identifierGenerator = this.getGenerator();
        identifierGenerator.setMaximumAttempts(0);
        int actual = getMaxAttempts(identifierGenerator);
        assertEquals(-1, actual);
    }

    @Test
    public void setMaximumAttempts_SetPositive_ValueShouldChange() {
        int expected = 11;
        BaseIdentifierGenerator<String> identifierGenerator = this.getGenerator();
        identifierGenerator.setMaximumAttempts(expected);
        int actual = getMaxAttempts(identifierGenerator);
        assertEquals(expected, actual);
    }

    @Test
    public void setMaximumAttempts_SetPositiveTwice_ValueShouldBeFirstSet() {
        int expected = 20;
        BaseIdentifierGenerator<String> identifierGenerator = this.getGenerator();
        identifierGenerator.setMaximumAttempts(expected);
        identifierGenerator.setMaximumAttempts(10);
        int actual = getMaxAttempts(identifierGenerator);
        assertEquals(expected, actual);
    }
}