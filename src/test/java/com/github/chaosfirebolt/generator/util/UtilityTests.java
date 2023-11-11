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

import com.github.chaosfirebolt.generator.identifier.string.util.CalculationUtility;
import com.github.chaosfirebolt.generator.identifier.string.util.CharacterUtility;
import com.github.chaosfirebolt.generator.identifier.string.util.OptionalUtility;
import com.github.chaosfirebolt.generator.identifier.string.util.ShuffleUtility;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UtilityTests {

  private static List<Class<?>> utilityClasses() {
    return List.of(CalculationUtility.class, CharacterUtility.class, OptionalUtility.class, ShuffleUtility.class);
  }

  @ParameterizedTest
  @MethodSource("utilityClasses")
  public void utilityClassesShouldHaveExactlyOneNoArgsPrivateConstructor(Class<?> utilityClass) {
    Constructor<?> constructor = assertAndGetSingleConstructor(utilityClass);
    assertEquals(0, constructor.getParameterCount(), "Utility constructor should not have parameters");
    boolean isPrivate = Modifier.isPrivate(constructor.getModifiers());
    assertTrue(isPrivate, "Constructor should be private");
  }

  private static Constructor<?> assertAndGetSingleConstructor(Class<?> utilityClass) {
    Constructor<?>[] constructors = utilityClass.getDeclaredConstructors();
    assertEquals(1, constructors.length, "Utility class should have exactly one constructor");
    return constructors[0];
  }

  @ParameterizedTest
  @MethodSource("utilityClasses")
  public void invokingUtilityClassConstructorWithReflectionShouldThrowException(Class<?> utilityClass) {
    Constructor<?> constructor = assertAndGetSingleConstructor(utilityClass);
    constructor.setAccessible(true);
    InvocationTargetException exception = assertThrows(InvocationTargetException.class, constructor::newInstance, "Invoking constructor should have thrown exception");
    Throwable cause = exception.getTargetException();
    assertEquals(RuntimeException.class, cause.getClass(), "Cause exception was not runtime exception");
    assertEquals("No instances allowed", cause.getMessage(), "Incorrect exception message");
  }
}
