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

package com.github.chaosfirebolt.generator.identifier.calculation;

import com.github.chaosfirebolt.generator.identifier.api.sequential.calculation.Calculation;
import com.github.chaosfirebolt.generator.identifier.api.sequential.calculation.CalculationFactories;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class FallbackCalculationTest {

  private static final String INITIAL_VALUE = "a";

  private static Calculation<String> createActualCalculation() {
    Calculation<String> calculation = mock();
    when(calculation.calculate(any())).thenReturn(INITIAL_VALUE.repeat(3));
    return calculation;
  }

  private static List<Calculation<String>> nullInput_ShouldReturnInitialValue() {
    Calculation<String> constantFallbackCalculation = CalculationFactories.constantFallback(createActualCalculation(), INITIAL_VALUE);
    return List.of(constantFallbackCalculation);
  }

  @ParameterizedTest
  @MethodSource
  public void nullInput_ShouldReturnInitialValue(Calculation<String> fallbackCalculation) {
    String result = assertDoesNotThrow(() -> fallbackCalculation.calculate(null), "Calculating with 'null' input should not have thrown exception");
    assertEquals(INITIAL_VALUE, result, "Should have returned initial value on null input");
  }

  @ParameterizedTest
  @MethodSource
  public void nonNullInput_ShouldInvokeTheActualCalculation(Calculation<String> fallbackCalculation, Calculation<String> actualCalculation) {
    String result = fallbackCalculation.calculate(INITIAL_VALUE);
    assertNotEquals(INITIAL_VALUE, result, "Calculation should not have returned initial value");
    verify(actualCalculation, times(1)).calculate(INITIAL_VALUE);
  }

  private static List<Arguments> nonNullInput_ShouldInvokeTheActualCalculation() {
    Calculation<String> actualCalculationConstant = createActualCalculation();
    Calculation<String> constantFallbackCalculation = CalculationFactories.constantFallback(actualCalculationConstant, INITIAL_VALUE);
    return List.of(
            Arguments.of(constantFallbackCalculation, actualCalculationConstant)
    );
  }
}
