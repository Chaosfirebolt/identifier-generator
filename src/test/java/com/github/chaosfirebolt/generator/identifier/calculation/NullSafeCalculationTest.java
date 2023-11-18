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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class NullSafeCalculationTest {

  private final String initialValue = "a";
  private Calculation<String> actualCalculation;
  private Calculation<String> nullSafeCalculation;

  @BeforeEach
  public void setUp() {
    Calculation<String> calculation = mock();
    when(calculation.calculate(any())).thenReturn(this.initialValue.repeat(3));
    this.actualCalculation = calculation;
    this.nullSafeCalculation = Calculation.nullSafe(this.actualCalculation, this.initialValue);
  }

  @Test
  public void nullInput_ShouldReturnInitialValue() {
    String result = assertDoesNotThrow(() -> this.nullSafeCalculation.calculate(null), "Calculating with 'null' input should not have thrown exception");
    assertEquals(this.initialValue, result, "Should have returned initial value on null input");
  }

  @Test
  public void nonNullInput_ShouldInvokeTheActualCalculation() {
    String result = this.nullSafeCalculation.calculate(this.initialValue);
    assertNotEquals(this.initialValue, result, "Calculation should not have returned initial value");
    verify(this.actualCalculation, times(1)).calculate(this.initialValue);
  }
}
