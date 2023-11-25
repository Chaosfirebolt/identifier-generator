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

package com.github.chaosfirebolt.generator.identifier.sequential;

import com.github.chaosfirebolt.generator.identifier.api.sequential.SequentialIdentifierGenerator;
import com.github.chaosfirebolt.generator.identifier.api.sequential.builders.SequentialIdentifierGeneratorBuilder;
import com.github.chaosfirebolt.generator.identifier.api.sequential.sequence.Sequence;
import com.github.chaosfirebolt.generator.identifier.api.sequential.sequence.SequenceFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SequentialGeneratorBuilderTests {

  @ParameterizedTest
  @MethodSource
  public void noSequenceSet_ShouldThrowException(SequentialIdentifierGeneratorBuilder<?, ?> builder) {
    assertThrows(RuntimeException.class, builder::build, "Creating generator without sequence should have throw exception");
  }

  private static List<SequentialIdentifierGeneratorBuilder<String, String>> noSequenceSet_ShouldThrowException() {
    Function<String, String> mapper = Function.identity();
    Supplier<? extends RuntimeException> exceptionSupplier = () -> new RuntimeException("msg");
    return List.of(SequentialIdentifierGenerator.<String, String>fluidTypeBuilder().setMapper(mapper).setExceptionFactory(exceptionSupplier),
            SequentialIdentifierGenerator.<String>constantTypeBuilder().setMapper(mapper).setExceptionFactory(exceptionSupplier));
  }

  @ParameterizedTest
  @MethodSource
  public void noExceptionFactorySet_ShouldNotThrow(SequentialIdentifierGeneratorBuilder<?, ?> builder) {
    assertDoesNotThrow(builder::build, "Creating generator without exception factory should be successful");
  }

  private static List<SequentialIdentifierGeneratorBuilder<String, String>> noExceptionFactorySet_ShouldNotThrow() {
    Sequence<String> sequence = SequenceFactory.infinite("a", s -> s + s);
    Function<String, String> mapper = Function.identity();
    return List.of(SequentialIdentifierGenerator.<String, String>fluidTypeBuilder().setSequence(sequence).setMapper(mapper),
            SequentialIdentifierGenerator.<String>constantTypeBuilder().setSequence(sequence).setMapper(mapper));
  }

  @Test
  public void fluidBuilderWithoutMapper_ShouldThrowException() {
    SequentialIdentifierGeneratorBuilder<String, String> builder = SequentialIdentifierGenerator.<String, String>fluidTypeBuilder()
            .setSequence(SequenceFactory.infinite("a", s -> s + s))
            .setExceptionFactory(() -> new RuntimeException("msg"));
    assertThrows(NullPointerException.class, builder::build, "Fluid type builder without mapper should have thrown NPE");
  }

  @Test
  public void constantBuilderWithoutMapper_ShouldCreateSuccessfully() {
    SequentialIdentifierGeneratorBuilder<String, String> builder = SequentialIdentifierGenerator.<String>constantTypeBuilder()
            .setSequence(SequenceFactory.infinite("a", s -> s + s))
            .setExceptionFactory(() -> new RuntimeException("msg"));
    assertDoesNotThrow(builder::build, "Constant type builder without mapper should have created a generator successfully");
  }
}
