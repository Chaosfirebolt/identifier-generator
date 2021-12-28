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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Predicate;

/**
 * Provides basic and somewhat naive functionality for generating unique tokens.
 * <br>
 * New tokens will be generated until the condition proves one to be unique.
 * <br>
 * Created by ChaosFire on 12/5/2021
 */
public abstract class BaseIdentifierGenerator<T> implements IdentifierGenerator<T> {

    /**
     * Default value for maximum number of attempts.
     */
    private static final int DEFAULT_MAX_ATTEMPTS = -1;

    /**
     * The logger.
     */
    protected final Logger logger;

    /**
     * Maximum number of attempts to generate unique token, before throwing exception.
     * <br>
     * Negative value means attempting forever.
     */
    private int maximumAttempts;

    protected BaseIdentifierGenerator() {
        this.logger = LoggerFactory.getLogger(this.getClass());
        this.maximumAttempts = DEFAULT_MAX_ATTEMPTS;
    }

    @Override
    public T generate(Predicate<T> uniquenessCondition) {
        T token = this.generate();
        int count = 1;
        while (!uniquenessCondition.test(token)) {
            this.throwIfMaxAttemptsExceeded(count);
            token = this.regenerateToken(token);
            count++;
        }
        this.logger.trace("Unique token generated after {} attempts", count);
        return token;
    }

    private void throwIfMaxAttemptsExceeded(int currentCount) {
        if (this.maximumAttempts > 0 && currentCount >= this.maximumAttempts) {
            String message = "Maximum number of attempts to generate unique token reached - " + currentCount;
            this.logger.trace(message);
            throw new TooManyAttemptsException(message);
        }
    }

    /**
     * Generates a new token, if the previously generated one is not unique.
     * <br>
     * This implementation will just create a new one from scratch, without taking into account the previous token.
     * <br>
     * Override this method for better/faster regeneration.
     * @param previousToken previously generated and not unique token
     * @return newly generated token
     */
    @SuppressWarnings("unused")
    protected T regenerateToken(T previousToken) {
        return this.generate();
    }

    @Override
    public T generate(int tokenLength, Predicate<T> uniquenessCondition) {
        T token = this.generate(tokenLength);
        int count = 1;
        while (!uniquenessCondition.test(token)) {
            this.throwIfMaxAttemptsExceeded(count);
            token = this.regenerateToken(token, tokenLength);
            count++;
        }
        this.logger.trace("Unique token generated after {} attempts", count);
        return token;
    }

    /**
     * Generates a new token, if the previously generated one is not unique.
     * <br>
     * This implementation will just create a new one from scratch, without taking into account the previous token.
     * <br>
     * Override this method for better/faster regeneration.
     * @param previousToken previously generated and not unique token
     * @param tokenLength required length of the token
     * @return newly generated token
     */
    @SuppressWarnings("unused")
    protected T regenerateToken(T previousToken, int tokenLength) {
        return this.generate(tokenLength);
    }

    /**
     * Sets the value of maximum number of attempts. Attempting to set negative value or zero will have no effect.
     * <br>
     * Positive value can be assigned once. Subsequent invocations will have no effect.
     * @param maximumAttempts maximum number of attempts to generate unique token before throwing exception.
     */
    public void setMaximumAttempts(int maximumAttempts) {
        if (maximumAttempts <= 0 || this.maximumAttempts > 0) {
            return;
        }
        this.maximumAttempts = maximumAttempts;
    }
}