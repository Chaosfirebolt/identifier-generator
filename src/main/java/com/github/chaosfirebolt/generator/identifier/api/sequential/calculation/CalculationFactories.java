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

import java.util.Objects;
import java.util.function.Supplier;

public class CalculationFactories {

  private static final String NULL_CALCULATION_ERROR_MESSAGE = "Null calculation provided";

  private CalculationFactories() {
    throw new RuntimeException("No instances allowed");
  }

  /**
   * Wraps a fallback supplying calculation around the provided one, which returns the fallback value in case of {@code null} input,
   * otherwise invokes the actual calculation.
   *
   * @param calculation   the actual calculation
   * @param fallbackValue fallback to return, if the input of {@code calculation} is null
   * @param <O>           type of the input and result
   * @return fallback {@link Calculation}
   * @throws NullPointerException if any argument is null
   */
  public static <O> Calculation<O> constantFallback(Calculation<O> calculation, O fallbackValue) {
    return new ConstantFallbackCalculation<>(Objects.requireNonNull(calculation, NULL_CALCULATION_ERROR_MESSAGE), Objects.requireNonNull(fallbackValue, "Null fallback value provided"));
  }

  /**
   * Wraps a fallback supplying calculation around the provided one, which invokes the fallback value supplier in case of {@code null} input,
   * otherwise invokes the actual calculation. The fallback supplier should never return null.
   *
   * @param calculation           the actual calculation
   * @param fallbackValueSupplier supplier for a fallback to return, if the input of {@code calculation} is null
   * @param <O>                   type of the input and result
   * @return fallback {@link Calculation}
   * @throws NullPointerException if any argument is null
   */
  public static <O> Calculation<O> computableFallback(Calculation<O> calculation, Supplier<O> fallbackValueSupplier) {
    return new ComputableFallbackCalculation<>(Objects.requireNonNull(calculation, NULL_CALCULATION_ERROR_MESSAGE), Objects.requireNonNull(fallbackValueSupplier, "Null fallback value supplier provided"));
  }
}
