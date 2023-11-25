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

package com.github.chaosfirebolt.generator.identifier.internal.builders;

import com.github.chaosfirebolt.generator.identifier.api.sequential.SequentialIdentifierGenerator;
import com.github.chaosfirebolt.generator.identifier.api.sequential.sequence.Sequence;
import org.apiguardian.api.API;

import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Internal, do not use!
 * @param <E>
 * @param <ID>
 */
@API(status = API.Status.INTERNAL, since = "2.1.0")
public interface SequentialIdentifierGeneratorFactory<E, ID> {

  /**
   * Internal, do not use!
   * Creates new instance of {@link SequentialIdentifierGenerator} from provided arguments.
   */
  SequentialIdentifierGenerator<E, ID> create(Sequence<E> sequence, Function<E, ID> mapper, Supplier<? extends RuntimeException> exceptionFactory);
}
