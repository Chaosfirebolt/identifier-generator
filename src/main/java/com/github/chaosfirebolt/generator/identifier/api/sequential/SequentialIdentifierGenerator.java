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

package com.github.chaosfirebolt.generator.identifier.api.sequential;

import com.github.chaosfirebolt.generator.identifier.api.BaseIdentifierGenerator;
import com.github.chaosfirebolt.generator.identifier.api.sequential.builders.SequentialIdentifierGeneratorBuilder;
import com.github.chaosfirebolt.generator.identifier.api.sequential.sequence.Sequence;
import com.github.chaosfirebolt.generator.identifier.api.sequential.sequence.SequenceDecoration;
import org.apiguardian.api.API;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Identifier generator, which produces identifiers from a sequence.
 * @param <E> type of the element produced by the sequence
 * @param <ID> type of the identifier produced by generator
 */
@API(status = API.Status.STABLE, since = "2.1.0")
public class SequentialIdentifierGenerator<E, ID> extends BaseIdentifierGenerator<ID> {

  private final Sequence<E> sequence;
  private final Function<E, ID> mapper;
  private final SequenceDecoration<ID> decoration;
  private final Supplier<? extends RuntimeException> exceptionFactory;

  private SequentialIdentifierGenerator(Sequence<E> sequence, Function<E, ID> mapper, SequenceDecoration<ID> decoration, Supplier<? extends RuntimeException> exceptionFactory) {
    this.sequence = sequence;
    this.mapper = mapper;
    this.decoration = decoration;
    this.exceptionFactory = exceptionFactory;
  }

  /**
   * Builder for sequential generator, producing identifiers with different type than the underlying sequence.
   * @return new builder
   * @param <E> type of the element produced by the sequence
   * @param <ID> type of the identifier produced by generator
   */
  public static <E, ID> SequentialIdentifierGeneratorBuilder<E, ID> fluidTypeBuilder() {
    return SequentialIdentifierGeneratorBuilder.fluidTypeBuilder(SequentialIdentifierGenerator::new);
  }

  /**
   * Builder for sequential generator, producing identifiers with same type as the underlying sequence.
   * @return new builder
   * @param <ID> type of the element produced by the sequence and the identifiers produced the generator
   */
  public static <ID> SequentialIdentifierGeneratorBuilder<ID, ID> constantTypeBuilder() {
    return SequentialIdentifierGeneratorBuilder.constantTypeBuilder(SequentialIdentifierGenerator::new);
  }

  @Override
  public ID generate() {
    Optional<ID> decorated = this.nextDecoratedId();
    if (decorated.isEmpty()) {
      this.decoration.reset();
      this.sequence.reset();
      decorated = this.nextDecoratedId();
    }
    return decorated.orElseThrow(this.exceptionFactory);
  }

  private Optional<ID> nextDecoratedId() {
    ID nextId = this.sequence.next()
            .map(this.mapper)
            .orElseThrow(this.exceptionFactory);
    return this.decoration.apply(nextId);
  }
}
