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

@API(status = API.Status.INTERNAL, since = "2.1.0")
abstract class FallbackCalculation<R> implements Calculation<R> {

  private final Calculation<R> actualCalculation;

  FallbackCalculation(Calculation<R> actualCalculation) {
    this.actualCalculation = actualCalculation;
  }

  @Override
  public R calculate(R input) {
    if (input == null) {
      return this.calculateFallbackValue();
    }
    return this.actualCalculation.calculate(input);
  }

  protected abstract R calculateFallbackValue();
}
