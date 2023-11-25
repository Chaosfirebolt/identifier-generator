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

package com.github.chaosfirebolt.generator.identifier.api.sequential.builders;

import com.github.chaosfirebolt.generator.identifier.internal.builders.SequentialIdentifierGeneratorFactory;
import org.apiguardian.api.API;

import java.util.function.Function;

@API(status = API.Status.INTERNAL, since = "2.1.0")
final class ConstantTypeSequentialBuilder<ID> extends SequentialIdentifierGeneratorBuilder<ID, ID> {

  ConstantTypeSequentialBuilder(SequentialIdentifierGeneratorFactory<ID, ID> generatorFactory) {
    super(generatorFactory);
  }

  @Override
  Function<ID, ID> getMapper() {
    if (this.mapper == null) {
      return Function.identity();
    }
    return this.mapper;
  }
}
