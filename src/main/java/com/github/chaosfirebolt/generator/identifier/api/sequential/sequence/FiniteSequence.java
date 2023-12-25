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

import java.util.Optional;
import java.util.function.Predicate;

@API(status = API.Status.INTERNAL, since = "2.1.0")
final class FiniteSequence<E> extends BaseSequence<E> {

  private final Predicate<E> hasNext;
  private E nextValue;

  FiniteSequence(E initialValue, Calculation<E> calculation, Predicate<E> hasNext) {
    super(initialValue, calculation);
    this.hasNext = hasNext;
    this.nextValue = null;
  }

  FiniteSequence(E initialValue, Calculation<E> calculation, E previousValue, Predicate<E> hasNext) {
    super(initialValue, calculation, previousValue);
    this.hasNext = hasNext;
    this.nextValue = null;
  }

  @Override
  public Optional<E> next() {
    if (!hasNext()) {
      return Optional.empty();
    }
    E next = this.nextValue != null ? this.nextValue : this.calculation.calculate(this.previousValue);
    this.previousValue = next;
    this.nextValue = null;
    return Optional.of(next);
  }

  private boolean hasNext() {
    if (this.nextValue == null) {
      this.nextValue = this.calculation.calculate(this.previousValue);
    }
    return this.hasNext.test(this.nextValue);
  }

  @Override
  public void reset() {
    this.previousValue = null;
    this.nextValue = null;
  }
}
