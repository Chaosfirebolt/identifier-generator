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

package com.github.chaosfirebolt.generator.util;

import com.github.chaosfirebolt.generator.identifier.internal.util.OptionalUtility;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.OptionalInt;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class OptionalUtilityTests {

  @ParameterizedTest
  @ValueSource(ints = { 1, 5, 9 })
  public void conditionIsFulfilled_ShouldBeEmpty(int value) {
    OptionalInt optionalInt = OptionalUtility.fromInt(value, num -> num > 0);
    assertTrue(optionalInt.isEmpty(), "Optional should have been empty");
  }

  @ParameterizedTest
  @ValueSource(ints = { 0, -5, -7, -11, -1371 })
  public void conditionIsNotFulfilled_ShouldBePresent(int value) {
    OptionalInt optionalInt = OptionalUtility.fromInt(value, num -> num > 0);
    assertTrue(optionalInt.isPresent(), "Optional should have contained a value");
    assertEquals(value, optionalInt.getAsInt(), "Wrong value in optional");
  }

  @ParameterizedTest
  @ValueSource(ints = { 0, -3, -19 })
  public void defaultConditionIsFulfilled_ShouldBeEmpty(int value) {
    OptionalInt optionalInt = OptionalUtility.fromInt(value);
    assertTrue(optionalInt.isEmpty(), "Optional should have been empty");
  }

  @ParameterizedTest
  @ValueSource(ints = { 1, 5, 7, 11, 1371 })
  public void defaultConditionIsNotFulfilled_ShouldBePresent(int value) {
    OptionalInt optionalInt = OptionalUtility.fromInt(value);
    assertTrue(optionalInt.isPresent(), "Optional should have contained a value");
    assertEquals(value, optionalInt.getAsInt(), "Wrong value in optional");
  }
}
