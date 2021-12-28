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

import java.util.function.Predicate;

/**
 * This interface defines the basic functionality for generating tokens.
 * <br>
 * Created by ChaosFire on 12/5/2021
 */
public interface TokenGenerator<T> {

    /**
     * Generates a token
     * @return the generated token
     */
    T generate();

    /**
     * Generates a token which is unique according to the provided condition
     * @param uniquenessCondition condition for uniqueness of the generated token
     * @return the generated unique token
     * @throws com.github.chaosfirebolt.generator.token.exception.TooManyAttemptsException if a maximum number of attempts to generate unique token has been set, and that number is reached
     */
    T generate(Predicate<T> uniquenessCondition);

    /**
     * Generates a token with the specified length
     * @param tokenLength required length of the generated token
     * @return the generated token
     */
    T generate(int tokenLength);

    /**
     * Generates a token with the specified length, which is unique according to the provided condition
     * @param tokenLength required length of the generated token
     * @param uniquenessCondition condition for uniqueness of the generated token
     * @return the generated unique token
     * @throws com.github.chaosfirebolt.generator.token.exception.TooManyAttemptsException if a maximum number of attempts to generate unique token has been set, and that number is reached
     */
    T generate(int tokenLength, Predicate<T> uniquenessCondition);
}