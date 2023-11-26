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
import com.github.chaosfirebolt.generator.identifier.api.sequential.sequence.Sequence;
import com.github.chaosfirebolt.generator.identifier.api.sequential.sequence.SequenceFactories;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SequentialIdentifierGeneratorTests {

  private SequentialIdentifierGenerator<Long, String> generator;

  @BeforeEach
  public void setUp() {
    Sequence<Long> sequence = SequenceFactories.infinite(1L, i -> i + 1);
    Function<Long, String> mapper = num -> String.format("%010d", num);
    this.generator = SequentialIdentifierGenerator.<Long, String>fluidTypeBuilder()
            .setSequence(sequence)
            .setMapper(mapper)
            .setExceptionFactory(() -> new RuntimeException("Incorrect generator setup"))
            .build();
  }

  @Test
  public void testCorrectIdentifiersGenerated() {
    int count = 1_000_000;
    List<String> actualIds = new ArrayList<>(count);
    for (int i = 0; i < count; i++) {
      actualIds.add(this.generator.generate());
    }
    List<String> expectedIds = IntStream.rangeClosed(1, count).mapToObj(SequentialIdentifierGeneratorTests::padLeft).toList();
    assertEquals(expectedIds, actualIds, "Incorrect ids");
  }

  private static String padLeft(long num) {
    String asString = Long.toString(num);
    if (asString.length() >= 10) {
      return asString;
    }
    int diff = 10 - asString.length();
    return "0".repeat(diff).concat(asString);
  }
}
