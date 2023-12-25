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

package com.github.chaosfirebolt.generator.identifier.api.sequential.sequence;

import org.apiguardian.api.API;

import java.util.Optional;

/**
 * Decoration for an element of a sequence.
 *
 * @param <E> type of the input and result
 */
@API(status = API.Status.STABLE, since = "2.1.0")
public interface SequenceDecoration<E> {

  /**
   * Applies the decoration on provided input. Returning empty optional serves as a signal that more elements can't be generated.
   * After returning an empty optional once, all subsequent calls to this method <strong>must</strong> return an empty optional, unless the decoration is {@link #reset()}.
   *
   * @param input element to decorate
   * @return optional describing the result of the decoration
   */
  Optional<E> apply(E input);

  /**
   * Attempts to restore this decoration to a state, in which it can once more decorate more elements.
   * May or may not succeed.
   */
  void reset();

  /**
   * Decoration, which does not perform any operation on the input.
   *
   * @param <E> type of input and result
   * @return a no op decoration
   */
  static <E> SequenceDecoration<E> noOp() {
    return new NoOpDecoration<>();
  }
}
