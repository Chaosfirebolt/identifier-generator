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
import com.github.chaosfirebolt.generator.identifier.api.sequential.calculation.CalculationFactories;
import org.apiguardian.api.API;

import java.util.Objects;
import java.util.function.Predicate;

/**
 * Factory for creating different {@link Sequence}s.
 */
@API(status = API.Status.STABLE, since = "2.1.0")
public class SequenceFactories {

  private SequenceFactories() {
    throw new RuntimeException("No instances allowed");
  }

  /**
   * Creates a finite sequence of elements from provided arguments. For the purpose of creating identifier generator, {@link #infinite} or {@link #resettable} sequences are preferred.
   *
   * @param initialValue the initial value of the sequence
   * @param calculation  calculates the next element, using the previous one as input
   * @param hasNext      condition to determine when the sequence is unable to produce more elements
   * @param <O>          type of elements produced by this sequence
   * @return finite sequence of elements
   * @throws NullPointerException if any of the arguments is null
   */
  public static <O> Sequence<O> finite(O initialValue, Calculation<O> calculation, Predicate<O> hasNext) {
    Objects.requireNonNull(calculation, "Null calculation passed");
    Objects.requireNonNull(initialValue, "Null initial value passed");
    Calculation<O> fallbackCalculation = CalculationFactories.constantFallback(calculation, initialValue);
    return new FiniteSequence<>(initialValue, fallbackCalculation, Objects.requireNonNull(hasNext));
  }

  /**
   * Creates an infinite sequence of elements from provided arguments.
   *
   * @param initialValue the initial value of the sequence
   * @param calculation  calculates the next element, using the previous one as input
   * @param <O>          type of elements produced by this sequence
   * @return infinite sequence of elements
   * @throws NullPointerException if any of the arguments is null
   */
  public static <O> Sequence<O> infinite(O initialValue, Calculation<O> calculation) {
    Objects.requireNonNull(initialValue, "Null initial value passed");
    Objects.requireNonNull(calculation, "Null calculation passed");
    Calculation<O> fallbackCalculation = CalculationFactories.constantFallback(calculation, initialValue);
    return new InfiniteSequence<>(initialValue, fallbackCalculation);
  }

  /**
   * Creates a self resetting sequence from the supplied one.
   *
   * @param sequence sequence to wrap as a resettable one
   * @param <O>      type of elements produced by this sequence
   * @return a resettable sequence
   * @throws NullPointerException if supplied sequence is null
   */
  public static <O> Sequence<O> resettable(Sequence<O> sequence) {
    return new ResettableSequence<>(Objects.requireNonNull(sequence, "Null sequence"));
  }
}
