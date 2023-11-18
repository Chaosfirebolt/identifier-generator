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
import java.util.function.Predicate;

@API(status = API.Status.INTERNAL, since = "2.1.0")
class FiniteSequence<E> implements Sequence<E> {

  private final Calculation<E> calculation;
  private final Predicate<E> hasNext;
  private E previousValue;
  private E nextValue;

  FiniteSequence(E initialValue, Calculation<E> calculation, Predicate<E> hasNext) {
    this.calculation = Calculation.nullSafe(calculation, initialValue);
    this.hasNext = hasNext;
    this.previousValue = null;
    this.nextValue = null;
  }

  @Override
  public E next() {
    if (!hasNext()) {
      throw new NoSuchElementException("Sequence can't produce more elements");
    }
    E next = this.nextValue != null ? this.nextValue : this.calculation.calculate(this.previousValue);
    this.previousValue = next;
    this.nextValue = null;
    return next;
  }

  @Override
  public boolean hasNext() {
    if (this.nextValue == null) {
      this.nextValue = this.calculation.calculate(this.previousValue);
    }
    return this.hasNext.test(this.nextValue);
  }

  @Override
  public void reset() {
    this.previousValue = null;
  }
}
