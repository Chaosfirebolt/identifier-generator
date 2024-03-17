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

import com.github.chaosfirebolt.generator.identifier.api.sequential.export.Exportable;
import org.apiguardian.api.API;

import java.util.Optional;

/**
 * Represents a sequence of elements.
 *
 * @param <E> type of the elements in the sequence
 */
@API(status = API.Status.STABLE, since = "2.1.0")
public interface Sequence<E> extends Exportable<E> {

  /**
   * Calculates and returns the next element of the sequence.
   * If the sequence is unable to produce more elements, returns an empty optional.
   *
   * @return an optional describing the next element in this sequence
   */
  Optional<E> next();

  /**
   * Resets the sequence. If the sequence was unable to produce more elements, e.g. {@link #next()} would return an empty {@link Optional},
   * after invoking this method the sequence must be able to produce elements again.
   *
   * @throws UnsupportedOperationException if the sequence does not support a reset
   */
  void reset();
}
