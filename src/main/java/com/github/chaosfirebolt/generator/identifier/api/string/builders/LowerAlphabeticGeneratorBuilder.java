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
 * Internal!
 *
 * @param <T> concrete type of the builder
 */
@API(status = API.Status.STABLE, since = "2.0.0")
public interface LowerAlphabeticGeneratorBuilder<T extends TypeSpecificStringIdentifierBuilder<T>> {

  /**
   * @param lowerCaseLength desired length of lower case alphabetic characters
   * @return this builder
   */
  T setLowerCaseLength(int lowerCaseLength);

  /**
   * @param minLowerCaseLength desired minimum length of lower case alphabetic characters
   * @return this builder
   */
  T setMinLowerCaseLength(int minLowerCaseLength);
}
