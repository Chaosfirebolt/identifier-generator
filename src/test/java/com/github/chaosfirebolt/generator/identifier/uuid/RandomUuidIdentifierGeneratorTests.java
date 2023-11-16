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

package com.github.chaosfirebolt.generator.identifier.uuid;

import com.github.chaosfirebolt.generator.identifier.api.exception.TooManyAttemptsException;
import com.github.chaosfirebolt.generator.identifier.api.uuid.RandomUuidIdentifierGenerator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class RandomUuidIdentifierGeneratorTests {

  private RandomUuidIdentifierGenerator generator;
  private Set<UUID> generatedUuids;

  @BeforeEach
  public void setUp() {
    RandomUuidIdentifierGenerator generator = new RandomUuidIdentifierGenerator();
    generator.setMaximumAttempts(10);
    this.generator = generator;
    this.generatedUuids = new HashSet<>();
  }

  @AfterEach
  public void tearDown() {
    this.generator = null;
    this.generatedUuids.clear();
    this.generatedUuids = null;
  }

  @ParameterizedTest
  @ValueSource(ints = { 1, 11, 111, 1111, 11111 })
  public void generateShouldReturnDifferentUuidsAndNotThrow(int iterations) {
    for (int i = 0; i < iterations; i++) {
      this.generator.generate(this.generatedUuids::add);
    }
  }

  @ParameterizedTest
  @ValueSource(ints = { 1, 11, 111, 1111, 11111 })
  public void generateWithLengthShouldReturnDifferentUuidsAndNotThrow(int iterations) {
    for (int i = 0; i < iterations; i++) {
      this.generator.generate(11, this.generatedUuids::add);
    }
  }

  @Test
  public void generateNonUnique_ShouldThrowWhenMaxAttemptsAreReached() {
    UUID result = UUID.randomUUID();
    try (MockedStatic<UUID> mockedUUID = Mockito.mockStatic(UUID.class)) {
      mockedUUID.when(UUID::randomUUID).thenReturn(result);
      this.generator.generate(this.generatedUuids::add);
      assertThrows(TooManyAttemptsException.class, () -> this.generator.generate(this.generatedUuids::add), "Last invocation of generating same uuid should have thrown exception");
    }
  }
}
