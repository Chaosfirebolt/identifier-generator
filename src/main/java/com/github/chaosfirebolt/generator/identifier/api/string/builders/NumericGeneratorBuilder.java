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

package com.github.chaosfirebolt.generator.identifier.api.string.builders;

import com.github.chaosfirebolt.generator.identifier.internal.builders.TypeSpecificStringIdentifierBuilder;
import org.apiguardian.api.API;

/**
 * Defines functionality to configure generator with numeric symbols.
 *
 * @param <T> concrete type of the builder
 */
@API(status = API.Status.STABLE, since = "2.0.0")
public interface NumericGeneratorBuilder<T extends TypeSpecificStringIdentifierBuilder<T>> {

  /**
   * @param numericLength desired length of numeric characters
   * @return this builder
   */
  T setNumericLength(int numericLength);

  /**
   * @param minNumericLength desired minimum length of numeric characters
   * @return this builder
   */
  T setMinNumericLength(int minNumericLength);
}
