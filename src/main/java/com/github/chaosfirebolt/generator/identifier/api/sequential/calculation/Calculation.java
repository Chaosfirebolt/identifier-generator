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

package com.github.chaosfirebolt.generator.identifier.api.sequential.calculation;

import org.apiguardian.api.API;

/**
 * Operation producing the same result as the input.
 * @param <T> type of the input and result
 */
@API(status = API.Status.STABLE, since = "2.1.0")
@FunctionalInterface
public interface Calculation<T> {

  /**
   * Applies this operation to the input
   * @param input the input
   * @return result after this operation is applied on the input
   */
  T calculate(T input);

  /**
   * Wraps a null safe calculation around the supplied one, which returns the fallback value in case of {@code null} input,
   * otherwise invokes the actual calculation.
   * @param calculation the actual calculation
   * @param fallbackValue fallback to return, if the input of {@code calculation} is null
   * @return null safe {@link Calculation}
   * @param <O> type of the input and result
   */
  static <O> Calculation<O> nullSafe(Calculation<O> calculation, O fallbackValue) {
    return new NullSafeCalculation<>(calculation, fallbackValue);
  }
}
