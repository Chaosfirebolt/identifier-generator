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

package com.github.chaosfirebolt.generator.identifier.export;

import com.github.chaosfirebolt.generator.identifier.api.sequential.SequentialIdentifierGenerator;
import com.github.chaosfirebolt.generator.identifier.api.sequential.calculation.Calculation;
import com.github.chaosfirebolt.generator.identifier.api.sequential.export.Export;
import com.github.chaosfirebolt.generator.identifier.api.sequential.sequence.Sequence;
import com.github.chaosfirebolt.generator.identifier.api.sequential.sequence.SequenceFactories;
import com.github.chaosfirebolt.generator.identifier.sequential.DateDecoration;
import com.github.chaosfirebolt.generator.identifier.sequential.DurationSkippingClock;
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
import java.util.function.Predicate;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SequenceExportTests {

  @Test
  public void exportImportSequence() {
    Calculation<Integer> calculation = num -> num + 1;
    Sequence<Integer> sequence = SequenceFactories.infinite(1, calculation);
    List<Integer> actual = new ArrayList<>();
    extractElements(actual, sequence);

    byte[] serializedSequence = ExportSerializer.serialize(sequence.export());
    Export<Integer> deserializedExport = ExportSerializer.deserialize(serializedSequence);
    Sequence<Integer> importedSequence = SequenceFactories.infinite(deserializedExport.toImport(), calculation);
    extractElements(actual, importedSequence);

    List<Integer> expected = IntStream.rangeClosed(1, 20).boxed().toList();
    assertEquals(expected, actual, "Incorrect sequence after export/import");
  }

  private static void extractElements(List<Integer> integers, Sequence<Integer> sequence) {
    for (int i = 0; i < 10; i++) {
      integers.add(sequence.next().orElseThrow());
    }
  }

  @Test
  public void exportImportGenerator() {
    Calculation<Integer> calculation = num -> num + 1;
    Predicate<Integer> condition = num -> num < 100;
    Sequence<Integer> sequence = SequenceFactories.finite(1, calculation, condition);
    Function<Integer, String> mapper = num -> String.format("%05d", num);

    ZoneId zone = ZoneId.of("Europe/Sofia");
    Instant start = ZonedDateTime.of(LocalDate.of(2023, 1, 1), LocalTime.MIDNIGHT, zone).toInstant();
    DurationSkippingClock clock = new DurationSkippingClock(zone, Duration.ofHours(2), start);
    DateDecoration dateDecoration = new DateDecoration(clock);

    SequentialIdentifierGenerator<Integer, String> generator = SequentialIdentifierGenerator.<Integer, String>fluidTypeBuilder()
            .setSequence(sequence)
            .setMapper(mapper)
            .setDecoration(dateDecoration)
            .setExceptionFactory(() -> new RuntimeException("Incorrect generator setup"))
            .build();
    List<String> actual = new ArrayList<>();
    extractIds(generator, actual, 10);

    byte[] serializedSequence = ExportSerializer.serialize(generator.export());
    Export<Integer> deserializedExport = ExportSerializer.deserialize(serializedSequence);
    Sequence<Integer> importedSequence = SequenceFactories.finite(deserializedExport.toImport(), calculation, condition);

    SequentialIdentifierGenerator<Integer, String> recreatedGenerator = SequentialIdentifierGenerator.<Integer, String>fluidTypeBuilder()
            .setSequence(importedSequence)
            .setMapper(mapper)
            .setDecoration(dateDecoration)
            .setExceptionFactory(() -> new RuntimeException("Incorrect generator setup"))
            .build();
    extractIds(recreatedGenerator, actual, 5);

    List<String> expected = List.of(
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
    assertEquals(expected, actual, "Incorrect identifiers after export/import");
  }

  private static void extractIds(SequentialIdentifierGenerator<Integer, String> generator, List<String> ids, int count) {
    for (int i = 0; i < count; i++) {
      ids.add(generator.generate());
    }
  }
}
