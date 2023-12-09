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
import java.util.function.Function;

@API(status = API.Status.INTERNAL, since = "2.1.0")
class DecoratedSequence<E, R> implements Sequence<R> {

  private final Sequence<E> actualSequence;
  private final Function<E, R> mapper;
  private final SequenceDecoration<R> decoration;

  DecoratedSequence(Sequence<E> actualSequence, Function<E, R> mapper, SequenceDecoration<R> decoration) {
    this.actualSequence = actualSequence;
    this.mapper = mapper;
    this.decoration = decoration;
  }

  @Override
  public Optional<R> next() {
    return this.actualSequence.next()
            .map(this.mapper)
            .flatMap(this.decoration::apply);
  }

  @Override
  public void reset() {
    this.actualSequence.reset();
    this.decoration.reset();
  }
}
