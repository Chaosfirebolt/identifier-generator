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

package com.github.chaosfirebolt.generator.identifier.sequence;

import com.github.chaosfirebolt.generator.identifier.api.sequential.calculation.Calculation;
import com.github.chaosfirebolt.generator.identifier.api.sequential.sequence.Sequence;
import com.github.chaosfirebolt.generator.identifier.api.sequential.sequence.SequenceFactories;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

public class SequenceTests {

  @ParameterizedTest
  @MethodSource
  public void correctSequenceOfElements(Sequence<Integer> sequence) {
    List<Integer> expected = List.of(1, 3, 5, 7, 9);
    List<Integer> actual = getElements(sequence, 5);
    assertEquals(expected, actual, "Incorrect sequence");
  }

  private static List<Integer> getElements(Sequence<Integer> sequence, int count) {
    return IntStream.range(0, count)
            .sequential()
            .mapToObj(i -> sequence.next())
            .filter(Optional::isPresent)
            .map(Optional::get)
            .toList();
  }

  private static List<Sequence<Integer>> correctSequenceOfElements() {
    Integer initial = 1;
    Calculation<Integer> calculation = i -> i + 2;
    return List.of(SequenceFactories.infinite(initial, calculation), SequenceFactories.finite(initial, calculation, num -> num < 1000));
  }

  @Test
  public void finiteSequence_ShouldProduceCorrectElements() {
    List<Integer> expected = List.of(1, 3, 5, 7, 9);
    Sequence<Integer> finiteSequence = SequenceFactories.finite(1, i -> i + 2, i -> i < 11);
    List<Integer> actual = extractElements(finiteSequence);
    assertEquals(expected, actual, "Incorrect result from finite sequence");
  }

  @Test
  public void finiteSequence_ShouldReturnEmptyWhenCannotProduceMore() {
    Sequence<Integer> finiteSequence = SequenceFactories.finite(1, i -> i + 2, i -> i < 11);
    extractElements(finiteSequence);
    Optional<Integer> next = finiteSequence.next();
    assertTrue(next.isEmpty(), "Sequence should not have produced a result");
  }

  @Test
  public void finiteSequence_ShouldProduceSameElementsAfterReset() {
    Sequence<Integer> finiteSequence = SequenceFactories.finite(1, i -> i + 2, i -> i < 11);
    List<Integer> firstElements = extractElements(finiteSequence);
    Optional<Integer> next = finiteSequence.next();
    assertTrue(next.isEmpty(), "Sequence should not have produced a result");

    finiteSequence.reset();
    List<Integer> secondElements = extractElements(finiteSequence);
    assertEquals(firstElements, secondElements, "Incorrect result from finite sequence after reset");
  }

  private static List<Integer> extractElements(Sequence<Integer> sequence) {
    List<Integer> elements = new ArrayList<>();
    Optional<Integer> next = sequence.next();
    while (next.isPresent()) {
      elements.add(next.get());
      next = sequence.next();
    }
    return elements;
  }

  @Test
  public void infiniteSequence_ShouldContinueProducingCorrectElements() {
    int target = 1_000_000;//can't really test infinite, so going with big enough
    int initialValue = 0;
    Sequence<Integer> infiniteSequence = SequenceFactories.infinite(initialValue, i -> i + 1);
    List<Integer> actual = getElements(infiniteSequence, target);
    List<Integer> expected = IntStream.range(0, target).sequential().boxed().toList();
    assertEquals(expected, actual, "Incorrect elements produced by infinite sequence");
  }

  @Test
  public void infiniteSequence_ShouldContinueProducingElements() {
    int target = 1_000_000;//can't really test infinite, so going with big enough
    int initialValue = 0;
    Sequence<Integer> infiniteSequence = SequenceFactories.infinite(initialValue, i -> i + 1);
    getElements(infiniteSequence, target);
    for (int i = 0; i < target; i++) {
      assertDoesNotThrow(infiniteSequence::next, "Producing elements should not have thrown");
    }
  }

  @Test
  public void infiniteSequence_ShouldProduceSameElementsAfterReset() {
    Sequence<Integer> infiniteSequence = SequenceFactories.infinite(0, i -> i + 1);

    int targetElementsCount = 10_000;
    List<Integer> firstElements = getElementsFromInfiniteSequence(infiniteSequence, targetElementsCount);

    infiniteSequence.reset();
    List<Integer> secondElements = getElementsFromInfiniteSequence(infiniteSequence, targetElementsCount);

    assertEquals(firstElements, secondElements, "Incorrect elements after sequence reset");
  }

  private static List<Integer> getElementsFromInfiniteSequence(Sequence<Integer> infiniteSequence, int count) {
    List<Integer> result = new ArrayList<>(count);
    Optional<Integer> next = infiniteSequence.next();
    while (next.isPresent()) {
      result.add(next.get());
      if (result.size() == count) {
        break;
      }
      next = infiniteSequence.next();
    }
    return result;
  }

  @Test
  public void resettingSequence_ShouldProduceSame() {
    Sequence<Integer> finiteSequence = SequenceFactories.finite(1, i -> i + 1, i -> i <= 3);
    Sequence<Integer> resettableSequence = SequenceFactories.resettable(finiteSequence);
    List<Integer> actualResult = new ArrayList<>();
    for (int i = 0; i < 9; i++) {
      actualResult.add(resettableSequence.next().orElseThrow(() -> new AssertionError("Resettable sequence should not have returned empty optional")));
    }
    List<Integer> expectedResult = List.of(1, 2, 3, 1, 2, 3, 1, 2, 3);
    assertEquals(expectedResult, actualResult, "Result not as expected");
  }

  @Test
  public void decoratedSequenceWithMapperTest() {
    ZoneId zone = ZoneId.of("Europe/Sofia");
    Instant start = ZonedDateTime.of(LocalDate.of(2023, 1, 1), LocalTime.MIDNIGHT, zone).toInstant();
    DurationSkippingClock clock = new DurationSkippingClock(zone, Duration.ofHours(2), start);

    Sequence<Integer> numberSequence = SequenceFactories.infinite(1, num -> num + 1);

    Sequence<String> decorated = SequenceFactories.decorated(numberSequence, new NumberPaddingMapper('0', 5), new DateDecoration(clock));
    Sequence<String> selfResettingSequence = SequenceFactories.resettable(decorated);

    List<String> actualElements = new ArrayList<>();
    for (int i = 0; i < 15; i++) {
      actualElements.add(selfResettingSequence.next().orElseThrow(() -> new RuntimeException("Something went wrong")));
    }

    List<String> expectedElements = List.of(
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
    assertEquals(expectedElements, actualElements, "Incorrect sequence");
  }
}
