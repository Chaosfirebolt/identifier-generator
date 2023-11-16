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

package com.github.chaosfirebolt.generator.identifier.api;

import com.github.chaosfirebolt.generator.identifier.api.exception.TooManyAttemptsException;

import java.util.function.Predicate;

/**
 * Extension of {@link IdentifierGenerator} allowing to generate identifiers with desired length.
 * @param <T> type of the generated identifier
 */
public interface TargetLengthIdentifierGenerator<T> extends IdentifierGenerator<T> {

  /**
   * Generates an identifier with the specified length
   *
   * @param identifierLength required length of the generated identifier
   * @return the generated identifier
   */
  T generate(int identifierLength);

  /**
   * Generates an identifier with the specified length, which is unique according to the provided condition
   *
   * @param identifierLength    required length of the generated identifier
   * @param uniquenessCondition condition for uniqueness of the generated identifier
   * @return the generated unique identifier
   * @throws TooManyAttemptsException if a maximum number of attempts to generate unique identifier has been set, and that number is reached
   */
  T generate(int identifierLength, Predicate<T> uniquenessCondition);
}
