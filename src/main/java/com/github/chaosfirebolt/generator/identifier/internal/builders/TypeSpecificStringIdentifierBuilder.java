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

import com.github.chaosfirebolt.generator.identifier.api.string.StringIdentifierGenerator;
import com.github.chaosfirebolt.generator.identifier.api.string.part.Part;
import com.github.chaosfirebolt.generator.identifier.api.string.rule.BaseGeneratorRule;
import com.github.chaosfirebolt.generator.identifier.api.string.rule.GeneratorRule;
import org.apiguardian.api.API;

import java.util.List;

/**
 * Builder for subclasses of {@link StringIdentifierGenerator}.
 *
 * @param <T> concrete type of the builder
 */
@API(status = API.Status.INTERNAL, since = "2.0.0")
public abstract class TypeSpecificStringIdentifierBuilder<T extends TypeSpecificStringIdentifierBuilder<T>> extends BaseStringIdentifierGeneratorBuilder<T> {

  protected TypeSpecificStringIdentifierBuilder() {
  }

  @Override
  protected final T setGeneratorRule(GeneratorRule generatorRule) {
    //no funny stuff with reflection
    throw new UnsupportedOperationException(getClass().getSimpleName() + " does not support directly setting generator rule");
  }

  @Override
  protected final GeneratorRule getGeneratorRule() {
    return new BaseGeneratorRule(getParts());
  }

  /**
   * Get the parts the generator rule should be composed of.
   *
   * @return the parts
   */
  protected abstract List<Part> getParts();
}
