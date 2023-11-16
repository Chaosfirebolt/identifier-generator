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

package com.github.chaosfirebolt.generator.identifier.validation;

import com.github.chaosfirebolt.generator.identifier.api.string.validation.CompositeRuleValidator;
import com.github.chaosfirebolt.generator.identifier.api.string.validation.RuleValidator;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

public class CompositeRuleValidatorTests {

  @NullAndEmptySource
  @ParameterizedTest
  public void listConstructor_MissingDelegates_ShouldThrowIllegalArgumentException(List<RuleValidator> validators) {
    assertRequiredException(() -> new CompositeRuleValidator(validators));
  }

  private static void assertRequiredException(Executable constructorInvocation) {
    IllegalArgumentException exc = assertThrows(IllegalArgumentException.class, constructorInvocation, "Constructor invocation should have thrown exception");
    String errorMessage = exc.getMessage();
    assertTrue(errorMessage != null && !errorMessage.isBlank(), "Missing error message");
  }

  @NullAndEmptySource
  @ParameterizedTest
  public void varArgsConstructor_MissingDelegates_ShouldThrowIllegalArgumentException(RuleValidator[] validators) {
    assertRequiredException(() -> new CompositeRuleValidator(validators));
  }

  @ParameterizedTest
  @MethodSource("validatorStream")
  public void listConstructor_validDelegates_ShouldNotThrowException(Stream<RuleValidator> validatorStream) {
    Executable constructorInvocation = () -> new CompositeRuleValidator(validatorStream.toList());
    assertNoException(constructorInvocation);
  }

  private static List<Arguments> validatorStream() {
    return List.of(
            Arguments.of(Stream.of(mock(RuleValidator.class))),
            Arguments.of(Stream.of(mock(RuleValidator.class), mock(RuleValidator.class))),
            Arguments.of(Stream.of(mock(RuleValidator.class), mock(RuleValidator.class), mock(RuleValidator.class)))
    );
  }

  private static void assertNoException(Executable constructorInvocation) {
    assertDoesNotThrow(constructorInvocation, "Constructor invocation should not have thrown exception");
  }

  @ParameterizedTest
  @MethodSource("validatorStream")
  public void varargsConstructor_validDelegates_ShouldNotThrowException(Stream<RuleValidator> validatorStream) {
    Executable constructorInvocation = () -> new CompositeRuleValidator(validatorStream.toArray(RuleValidator[]::new));
    assertNoException(constructorInvocation);
  }
}
