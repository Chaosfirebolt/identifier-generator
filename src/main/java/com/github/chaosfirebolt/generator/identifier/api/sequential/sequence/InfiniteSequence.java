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

import java.util.Optional;

@API(status = API.Status.STABLE, since = "2.1.0")
class InfiniteSequence<E> implements Sequence<E> {

  private final Calculation<E> calculation;
  private E previousValue;

  InfiniteSequence(E initialValue, Calculation<E> calculation) {
    this.calculation = CalculationFactories.fallback(calculation, initialValue);
    this.previousValue = null;
  }

  @Override
  public Optional<E> next() {
    E next = this.calculation.calculate(this.previousValue);
    this.previousValue = next;
    return Optional.of(next);
  }

  @Override
  public void reset() {
    this.previousValue = null;
  }
}
