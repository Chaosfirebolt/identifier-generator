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

import java.util.function.Predicate;

/**
 * The base for implementations of {@link TargetLengthIdentifierGenerator}.
 * @param <T> type of the generated identifier
 */
public abstract class BaseTargetLengthIdentifierGenerator<T> extends BaseIdentifierGenerator<T> implements TargetLengthIdentifierGenerator<T> {

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
   *
   * @param previousIdentifier previously generated and not unique identifier
   * @param identifierLength   required length of the identifier
   * @return newly generated identifier
   */
  @SuppressWarnings("unused")
  protected T regenerateIdentifier(T previousIdentifier, int identifierLength) {
    return this.generate(identifierLength);
  }
}
