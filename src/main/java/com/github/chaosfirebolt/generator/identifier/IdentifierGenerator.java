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

import java.util.function.Predicate;

/**
 * This interface defines the basic functionality for generating identifiers.
 * <br>
 * Created by ChaosFire on 12/5/2021
 */
public interface IdentifierGenerator<T> {

    /**
     * Generates an identifier
     * @return the generated identifier
     */
    T generate();

    /**
     * Generates an identifier which is unique according to the provided condition
     * @param uniquenessCondition condition for uniqueness of the generated identifier
     * @return the generated unique identifier
     * @throws com.github.chaosfirebolt.generator.identifier.exception.TooManyAttemptsException if a maximum number of attempts to generate unique identifier has been set, and that number is reached
     */
    T generate(Predicate<T> uniquenessCondition);

    /**
     * Generates an identifier with the specified length
     * @param identifierLength required length of the generated identifier
     * @return the generated identifier
     */
    T generate(int identifierLength);

    /**
     * Generates an identifier with the specified length, which is unique according to the provided condition
     * @param identifierLength required length of the generated identifier
     * @param uniquenessCondition condition for uniqueness of the generated identifier
     * @return the generated unique identifier
     * @throws com.github.chaosfirebolt.generator.identifier.exception.TooManyAttemptsException if a maximum number of attempts to generate unique identifier has been set, and that number is reached
     */
    T generate(int identifierLength, Predicate<T> uniquenessCondition);
}