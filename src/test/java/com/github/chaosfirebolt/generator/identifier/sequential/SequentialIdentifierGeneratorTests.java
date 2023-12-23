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
import com.github.chaosfirebolt.generator.identifier.api.sequential.sequence.SequenceFactories;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SequentialIdentifierGeneratorTests {

  private static final String INCORRECT_IDS_ERROR_MESSAGE = "Incorrect ids";

  private SequentialIdentifierGeneratorBuilder<Long, String> generatorBuilder;

  @BeforeEach
  public void setUp() {
    Sequence<Long> sequence = SequenceFactories.infinite(1L, i -> i + 1);
    Function<Long, String> mapper = num -> String.format("%05d", num);
    this.generatorBuilder = SequentialIdentifierGenerator.<Long, String>fluidTypeBuilder()
            .setSequence(sequence)
            .setMapper(mapper)
            .setExceptionFactory(() -> new RuntimeException("Incorrect generator setup"));
  }

  @Test
  public void testCorrectIdentifiersGenerated() {
    SequentialIdentifierGenerator<Long, String> generator = this.generatorBuilder.build();
    int count = 1_000_000;
    List<String> actualIds = new ArrayList<>(count);
    for (int i = 0; i < count; i++) {
      actualIds.add(generator.generate());
    }
    List<String> expectedIds = IntStream.rangeClosed(1, count).mapToObj(SequentialIdentifierGeneratorTests::padLeft).toList();
    assertEquals(expectedIds, actualIds, INCORRECT_IDS_ERROR_MESSAGE);
  }

  private static String padLeft(long num) {
    String asString = Long.toString(num);
    int targetLength = 5;
    if (asString.length() >= targetLength) {
      return asString;
    }
    int diff = targetLength - asString.length();
    return "0".repeat(diff).concat(asString);
  }

  @Test
  public void testCorrectIdentifiersGeneratedWithDecoration() {
    ZoneId zone = ZoneId.of("Europe/Sofia");
    Instant start = ZonedDateTime.of(LocalDate.of(2023, 1, 1), LocalTime.MIDNIGHT, zone).toInstant();
    DurationSkippingClock clock = new DurationSkippingClock(zone, Duration.ofHours(2), start);
    DateDecoration dateDecoration = new DateDecoration(clock);

    SequentialIdentifierGenerator<Long, String> generator = this.generatorBuilder.setDecoration(dateDecoration).build();
    List<String> actualIdentifiers = new ArrayList<>();
    for (int i = 0; i < 15; i++) {
      actualIdentifiers.add(generator.generate());
    }

    List<String> expectedIdentifiers = List.of(
            "2023010100001",
            "2023010100002",
            "2023010100003",
            "2023010100004",
            "2023010100005",
            "2023010100006",
            "2023010100007",
            "2023010100008",
            "2023010100009",
            "2023010100010",
            "2023010100011",
            "2023010100012",
            "2023010200001",
            "2023010200002",
            "2023010200003"
    );
    assertEquals(expectedIdentifiers, actualIdentifiers, "Incorrect identifiers");
  }
}
