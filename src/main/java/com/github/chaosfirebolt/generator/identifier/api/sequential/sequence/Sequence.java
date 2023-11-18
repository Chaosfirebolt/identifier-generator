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

import com.github.chaosfirebolt.generator.identifier.api.sequential.calculation.Calculation;
import org.apiguardian.api.API;

import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.function.Predicate;

/**
 * Represents a sequence of elements.
 * @param <E> type of the elements in the sequence
 */
@API(status = API.Status.STABLE, since = "2.1.0")
public interface Sequence<E> {

  /**
   * Calculates and returns the next element of the sequence.
   * If the sequence is unable to produce more elements throws {@link NoSuchElementException}.
   * @return the next element in this sequence
   * @throws NoSuchElementException if the sequence is unable to retrieve the next element
   */
  E next();

  /**
   * Checks whether this sequence can draw more elements.
   * @return true, if more elements can be drawn, false otherwise
   */
  boolean hasNext();

  /**
   * Resets the sequence. If the sequence was unable to produce more elements, e.g. {@link #next()} would throw {@link NoSuchElementException},
   * after invoking this method the sequence must be able to produce elements again.
   */
  void reset();

  /**
   * Creates a finite sequence of elements from provided arguments.
   * @param initialValue the initial value of the sequence
   * @param calculation calculates the next element, using the previous one as input
   * @param hasNext condition to determine when the sequence is unable to produce more elements
   * @return finite sequence of elements
   * @param <O> type of elements produced by this sequence
   * @throws NullPointerException if any of the arguments is null
   */
  static <O> Sequence<O> finite(O initialValue, Calculation<O> calculation, Predicate<O> hasNext) {
    return new FiniteSequence<>(Objects.requireNonNull(initialValue, "Null initial value passed"), Objects.requireNonNull(calculation, "Null calculation passed"), Objects.requireNonNull(hasNext));
  }

  /**
   * Creates an infinite sequence of elements from provided arguments
   * @param initialValue the initial value of the sequence
   * @param calculation calculates the next element, using the previous one as input
   * @return infinite sequence of elements
   * @param <O> type of elements produced by this sequence
   * @throws NullPointerException if any of the arguments is null
   */
  static <O> Sequence<O> infinite(O initialValue, Calculation<O> calculation) {
    return new InfiniteSequence<>(Objects.requireNonNull(initialValue, "Null initial value passed"), Objects.requireNonNull(calculation, "Null calculation passed"));
  }
}
