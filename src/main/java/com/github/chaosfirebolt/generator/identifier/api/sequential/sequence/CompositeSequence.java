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

import java.util.List;
import java.util.Optional;
import java.util.function.BinaryOperator;

@API(status = API.Status.INTERNAL, since = "2.1.0")
class CompositeSequence<E> implements Sequence<E> {

  private final List<Sequence<E>> delegates;
  private final BinaryOperator<E> accumulator;

  CompositeSequence(List<Sequence<E>> delegates, BinaryOperator<E> accumulator) {
    this.delegates = delegates;
    this.accumulator = accumulator;
  }

  @Override
  public Optional<E> next() {
    E result = null;
    boolean foundAny = false;
    for (Sequence<E> delegate : this.delegates) {
      Optional<E> current = delegate.next();
      if (current.isEmpty()) {
        return Optional.empty();
      }
      if (!foundAny) {
        foundAny = true;
        result = current.get();
      } else {
        result = this.accumulator.apply(result, current.get());
      }
    }
    return Optional.ofNullable(result);
  }

  @Override
  public void reset() {
    this.delegates.forEach(Sequence::reset);
  }
}
