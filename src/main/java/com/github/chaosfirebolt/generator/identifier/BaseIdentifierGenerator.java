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

import com.github.chaosfirebolt.generator.identifier.exception.TooManyAttemptsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Predicate;

/**
 * Provides basic and somewhat naive functionality for generating unique identifiers.
 * <br>
 * New identifiers will be generated until the condition proves one to be unique.
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
     * Maximum number of attempts to generate unique identifier, before throwing exception.
     * <br>
     * Negative value means attempting forever.
     */
    private int maximumAttempts;

    /**
     * Utility constructor providing default values.
     */
    protected BaseIdentifierGenerator() {
        this.logger = LoggerFactory.getLogger(this.getClass());
        this.maximumAttempts = DEFAULT_MAX_ATTEMPTS;
    }

    @Override
    public T generate(Predicate<T> uniquenessCondition) {
        T identifier = this.generate();
        int count = 1;
        this.logger.debug("Starting unique identifier generation");
        while (!uniquenessCondition.test(identifier)) {
            this.throwIfMaxAttemptsExceeded(count);
            identifier = this.regenerateIdentifier(identifier);
            count++;
        }
        this.logger.debug("Unique identifier generated after {} attempts", count);
        return identifier;
    }

    private void throwIfMaxAttemptsExceeded(int currentCount) {
        if (this.maximumAttempts > 0 && currentCount >= this.maximumAttempts) {
            String message = "Maximum number of attempts to generate unique identifier reached - " + currentCount;
            this.logger.debug(message);
            throw new TooManyAttemptsException(message);
        }
    }

    /**
     * Generates a new identifier, if the previously generated one is not unique.
     * <br>
     * This implementation will just create a new one from scratch, without taking into account the previous identifier.
     * <br>
     * Override this method for better/faster regeneration.
     * @param previousIdentifier previously generated and not unique identifier
     * @return newly generated identifier
     */
    @SuppressWarnings("unused")
    protected T regenerateIdentifier(T previousIdentifier) {
        return this.generate();
    }

    @Override
    public T generate(int identifierLength, Predicate<T> uniquenessCondition) {
        T identifier = this.generate(identifierLength);
        int count = 1;
        this.logger.debug("Starting unique identifier generation");
        while (!uniquenessCondition.test(identifier)) {
            this.throwIfMaxAttemptsExceeded(count);
            identifier = this.regenerateIdentifier(identifier, identifierLength);
            count++;
        }
        this.logger.debug("Unique identifier generated after {} attempts", count);
        return identifier;
    }

    /**
     * Generates a new identifier, if the previously generated one is not unique.
     * <br>
     * This implementation will just create a new one from scratch, without taking into account the previous identifier.
     * <br>
     * Override this method for better/faster regeneration.
     * @param previousIdentifier previously generated and not unique identifier
     * @param identifierLength required length of the identifier
     * @return newly generated identifier
     */
    @SuppressWarnings("unused")
    protected T regenerateIdentifier(T previousIdentifier, int identifierLength) {
        return this.generate(identifierLength);
    }

    /**
     * Sets the value of maximum number of attempts. Attempting to set negative value or zero will have no effect.
     * <br>
     * Positive value can be assigned once. Subsequent invocations will have no effect.
     * @param maximumAttempts maximum number of attempts to generate unique identifier before throwing exception.
     */
    public void setMaximumAttempts(int maximumAttempts) {
        if (maximumAttempts <= 0 || this.maximumAttempts > 0) {
            return;
        }
        this.maximumAttempts = maximumAttempts;
    }
}
