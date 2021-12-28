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

package com.github.chaosfirebolt.generator.token;

import com.github.chaosfirebolt.generator.token.exception.TooManyAttemptsException;
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

    private Set<String> existingTokens;
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
        this.existingTokens = new HashSet<>();
        this.uniqueCondition = token -> this.existingTokens.add(token);
        BaseIdentifierGenerator<String> tokenGenerator = this.getGenerator();
        tokenGenerator.setMaximumAttempts(this.maxAttempts);
        this.identifierGenerator = tokenGenerator;
    }

    protected abstract BaseIdentifierGenerator<String> getGenerator();

    @AfterEach
    void tearDown() {
        this.existingTokens.clear();
        this.existingTokens = null;
        this.identifierGenerator = null;
    }

    @Test
    public void generateToken_TokenShouldHaveCorrectLength() {
        String token = this.identifierGenerator.generate();
        this.logToken(token);
        assertEquals(this.expectedLength, token.length());
    }

    private void logToken(String token) {
        this.logger.info("{} generated token - {}", this.identifierGenerator.getClass().getSimpleName(), token);
    }

    @Test
    public void generateToken_GenerateMany_AllShouldBeDifferent() {
        for (int i = 0; i < 100; i++) {
            String token = this.identifierGenerator.generate();
            this.logToken(token);
            boolean exists = !this.existingTokens.add(token);
            assertFalse(exists, () -> String.format("Token '%s' already generated", token));
        }
    }

    @Test
    public void generateUniqueToken_TokenShouldHaveCorrectLength() {
        String token = this.identifierGenerator.generate(this.uniqueCondition);
        this.logToken(token);
        assertEquals(this.expectedLength, token.length());
    }

    @Test
    @Timeout(1)
    public void generateUniqueToken_ExpectedNumberOfUniqueTokensShouldBeGenerated() {
        int targetCount = 10_000;
        for (int i = 0; i < targetCount; i++) {
            this.identifierGenerator.generate(this.uniqueCondition);
        }
        assertEquals(targetCount, this.existingTokens.size());
    }

    @Test
    public void generateUniqueToken_MaximumAttemptsReached_ShouldThrowTooManyAttemptsExceptionWithCorrectMessage() {
        String toBeReturned = this.identifierGenerator.generate();
        IdentifierGenerator<String> generator = Mockito.spy(this.identifierGenerator);
        doReturn(toBeReturned).when(generator).generate();

        String firstGenerated = generator.generate(this.uniqueCondition);
        assertEquals(toBeReturned, firstGenerated, MOCK_ERROR_MESSAGE);

        TooManyAttemptsException exc = assertThrows(TooManyAttemptsException.class, () -> generator.generate(this.uniqueCondition));
        String expectedMessage = "Maximum number of attempts to generate unique token reached - " + this.maxAttempts;
        assertEquals(expectedMessage, exc.getMessage());
    }

    @Test
    public void generateTokenWithLength_LengthIsLessThanMinimum_TokenShouldHaveCorrectLength() {
        int length = this.expectedMinLength / 2;
        String token = this.identifierGenerator.generate(length);
        this.logToken(token);
        assertEquals(this.expectedMinLength, token.length());
    }

    @Test
    public void generateTokenWithLength_LengthIsMoreThanMinimum_TokenShouldHaveCorrectLength() {
        int length = this.expectedMinLength * 2;
        String token = this.identifierGenerator.generate(length);
        this.logToken(token);
        assertEquals(length, token.length());
    }

    @Test
    public void generateTokenWithLength_GenerateMany_AllShouldBeDifferent() {
        for (int i = 0; i < 100; i++) {
            String token = this.identifierGenerator.generate(100);
            this.logToken(token);
            boolean exists = !this.existingTokens.add(token);
            assertFalse(exists, () -> String.format("Token '%s' already generated", token));
        }
    }

    @Test
    public void generateUniqueTokenWithLength_LengthIsLessThanMinimum_TokenShouldHaveCorrectLength() {
        int length = this.expectedMinLength / 2;
        String token = this.identifierGenerator.generate(length, this.uniqueCondition);
        this.logToken(token);
        assertEquals(this.expectedMinLength, token.length());
    }

    @Test
    public void generateUniqueTokenWithLength_LengthIsMoreThanMinimum_TokenShouldHaveCorrectLength() {
        int length = this.expectedMinLength * 2;
        String token = this.identifierGenerator.generate(length, this.uniqueCondition);
        this.logToken(token);
        assertEquals(length, token.length());
    }

    @Test
    @Timeout(1)
    public void generateUniqueTokenWithLength_ExpectedNumberOfUniqueTokensShouldBeGenerated() {
        int targetCount = 10_000;
        for (int i = 0; i < targetCount; i++) {
            this.identifierGenerator.generate(100, this.uniqueCondition);
        }
        assertEquals(targetCount, this.existingTokens.size());
    }

    @Test
    public void generateUniqueTokenWithLength_MaximumAttemptsReached_ShouldThrowTooManyAttemptsExceptionWithCorrectMessage() {
        int length = 100;
        String toBeReturned = this.identifierGenerator.generate(length);
        IdentifierGenerator<String> generator = Mockito.spy(this.identifierGenerator);
        doReturn(toBeReturned).when(generator).generate(length);

        String firstGenerated = generator.generate(length, this.uniqueCondition);
        assertEquals(toBeReturned, firstGenerated, MOCK_ERROR_MESSAGE);

        TooManyAttemptsException exc = assertThrows(TooManyAttemptsException.class, () -> generator.generate(length, this.uniqueCondition));
        String expectedMessage = "Maximum number of attempts to generate unique token reached - " + this.maxAttempts;
        assertEquals(expectedMessage, exc.getMessage());
    }

    @Test
    public void setMaximumAttempts_SetNegative_ValueShouldRemainUnchanged() {
        BaseIdentifierGenerator<String> tokenGenerator = this.getGenerator();
        tokenGenerator.setMaximumAttempts(-2);
        int actual = getMaxAttempts(tokenGenerator);
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
        BaseIdentifierGenerator<String> tokenGenerator = this.getGenerator();
        tokenGenerator.setMaximumAttempts(0);
        int actual = getMaxAttempts(tokenGenerator);
        assertEquals(-1, actual);
    }

    @Test
    public void setMaximumAttempts_SetPositive_ValueShouldChange() {
        int expected = 11;
        BaseIdentifierGenerator<String> tokenGenerator = this.getGenerator();
        tokenGenerator.setMaximumAttempts(expected);
        int actual = getMaxAttempts(tokenGenerator);
        assertEquals(expected, actual);
    }

    @Test
    public void setMaximumAttempts_SetPositiveTwice_ValueShouldBeFirstSet() {
        int expected = 20;
        BaseIdentifierGenerator<String> tokenGenerator = this.getGenerator();
        tokenGenerator.setMaximumAttempts(expected);
        tokenGenerator.setMaximumAttempts(10);
        int actual = getMaxAttempts(tokenGenerator);
        assertEquals(expected, actual);
    }
}