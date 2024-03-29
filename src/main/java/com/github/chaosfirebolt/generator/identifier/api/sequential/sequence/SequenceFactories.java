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
import com.github.chaosfirebolt.generator.identifier.api.sequential.export.Import;
import org.apiguardian.api.API;

import java.util.Iterator;
import java.util.Objects;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.Predicate;
import java.util.stream.Stream;

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
    return new FiniteSequence<>(initialValue, fallbackCalculation, Objects.requireNonNull(hasNext, "Missing end condition"));
  }

  /**
   * Creates a finite sequence of elements from provided import and arguments. For the purpose of creating identifier generator, {@link #infinite} or {@link #resettable} sequences are preferred.
   *
   * @param oImport     the initial value of the sequence
   * @param calculation calculates the next element, using the previous one as input
   * @param hasNext     condition to determine when the sequence is unable to produce more elements
   * @param <O>         type of elements produced by this sequence
   * @return finite sequence of elements
   * @throws NullPointerException if any of the arguments is null
   */
  @API(status = API.Status.EXPERIMENTAL, since = "2.1.0")
  public static <O> Sequence<O> finite(Import<O> oImport, Calculation<O> calculation, Predicate<O> hasNext) {
    Objects.requireNonNull(oImport, "Null import passed");
    Objects.requireNonNull(calculation, "Null calculation passed");
    O initialValue = oImport.initialValue();
    Objects.requireNonNull(initialValue, "Null initial value passed");
    Calculation<O> fallbackCalculation = CalculationFactories.constantFallback(calculation, initialValue);
    return new FiniteSequence<>(initialValue, fallbackCalculation, oImport.latestValue(), Objects.requireNonNull(hasNext));
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
   * Creates an infinite sequence of elements from provided import and arguments.
   *
   * @param oImport     the initial value of the sequence
   * @param calculation calculates the next element, using the previous one as input
   * @param <O>         type of elements produced by this sequence
   * @return infinite sequence of elements
   * @throws NullPointerException if any of the arguments is null
   */
  @API(status = API.Status.EXPERIMENTAL, since = "2.1.0")
  public static <O> Sequence<O> infinite(Import<O> oImport, Calculation<O> calculation) {
    Objects.requireNonNull(oImport, "Null import passed");
    O initialValue = oImport.initialValue();
    Objects.requireNonNull(initialValue, "Null initial value passed");
    Objects.requireNonNull(calculation, "Null calculation passed");
    Calculation<O> fallbackCalculation = CalculationFactories.constantFallback(calculation, initialValue);
    return new InfiniteSequence<>(initialValue, fallbackCalculation, oImport.latestValue());
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

  /**
   * Creates a sequence from the supplied iterator. This sequence does not support reset and export operations.
   * The iterator must not return null elements.
   *
   * @param iterator iterator to base the sequence on
   * @param <O>      type of the elements produced by the sequence
   * @return iterator based sequence
   * @throws NullPointerException if supplied iterator is null
   */
  public static <O> Sequence<O> iterator(Iterator<O> iterator) {
    return new IteratorBasedSequence<>(Objects.requireNonNull(iterator, "Null iterator"));
  }

  /**
   * Creates a sequence from the supplied stream. This sequence does not support reset and export operations.
   * The stream must not return null elements.
   *
   * @param stream stream to base the sequence on
   * @param <O>    type of the elements produced by the sequence
   * @return sequence based on the streams' iterator
   * @throws NullPointerException if supplied stream is null
   */
  public static <O> Sequence<O> stream(Stream<O> stream) {
    Iterator<O> iterator = Objects.requireNonNull(stream, "Null stream").iterator();
    return iterator(iterator);
  }

  /**
   * Creates a sequence from the supplied spliterator. This sequence does not support reset and export operations.
   *
   * @param spliterator spliterator to base the sequence on
   * @param <O>         type of the elements produced by the sequence
   * @return sequence based on an iterator created from the spliterator
   * @throws NullPointerException if supplied spliterator is null
   */
  public static <O> Sequence<O> spliterator(Spliterator<O> spliterator) {
    Objects.requireNonNull(spliterator, "Null spliterator");
    return iterator(Spliterators.iterator(spliterator));
  }

  /**
   * Creates a sequence from the supplied iterable. This sequence does not support export operations.
   * The iterable must not contain null elements.
   *
   * @param iterable iterable to base the sequence on
   * @param <O>      type of the elements produced by the sequence
   * @return iterable based sequence
   * @throws NullPointerException if supplied iterator is null
   */
  public static <O> Sequence<O> iterable(Iterable<O> iterable) {
    return new IterableBasedSequence<>(Objects.requireNonNull(iterable, "Null iterable"));
  }
}
