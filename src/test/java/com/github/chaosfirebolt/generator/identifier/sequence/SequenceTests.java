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
import com.github.chaosfirebolt.generator.identifier.api.sequential.export.ExportStrategy;
import com.github.chaosfirebolt.generator.identifier.api.sequential.export.SequenceExport;
import com.github.chaosfirebolt.generator.identifier.api.sequential.sequence.Sequence;
import com.github.chaosfirebolt.generator.identifier.api.sequential.sequence.SequenceFactories;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

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
    List<Integer> source = IntStream.iterate(1, i -> i + 2)
            .boxed()
            .limit(100)
            .toList();
    Integer initial = 1;
    Calculation<Integer> calculation = i -> i + 2;
    return List.of(
            SequenceFactories.infinite(initial, calculation),
            SequenceFactories.finite(initial, calculation, num -> num < 1000),
            SequenceFactories.iterator(source.iterator()),
            SequenceFactories.stream(source.stream()),
            SequenceFactories.spliterator(source.spliterator()),
            SequenceFactories.iterable(source)
    );
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

  @ParameterizedTest
  @MethodSource
  public void selfResettingSequence_ShouldProduceSame(Sequence<Integer> sequence) {
    Sequence<Integer> resettableSequence = SequenceFactories.resettable(sequence);
    List<Integer> actualResult = new ArrayList<>();
    for (int i = 0; i < 9; i++) {
      actualResult.add(resettableSequence.next().orElseThrow(() -> new AssertionError("Resettable sequence should not have returned empty optional")));
    }
    List<Integer> expectedResult = List.of(1, 2, 3, 1, 2, 3, 1, 2, 3);
    assertEquals(expectedResult, actualResult, "Result not as expected");
  }

  private static List<Sequence<Integer>> selfResettingSequence_ShouldProduceSame() {
    return List.of(
            SequenceFactories.finite(1, i -> i + 1, i -> i <= 3),
            SequenceFactories.iterable(List.of(1, 2, 3))
    );
  }

  @ParameterizedTest
  @MethodSource("nonExportableNonResettableSequences_ShouldThrowUnsupportedOperationException")
  public void nonExportableSequences_ShouldThrowUnsupportedOperationException(Sequence<Integer> sequence) {
    assertUnsupportedOperationException(sequence::export);
    assertUnsupportedOperationException(() -> sequence.export(SequenceExport::new));
  }

  private static void assertUnsupportedOperationException(Executable executable) {
    UnsupportedOperationException exc = assertThrows(UnsupportedOperationException.class, executable, "Expected exception not thrown");
    String message = exc.getMessage();
    final String missingMessage = "No exception message";
    assertNotNull(message, missingMessage);
    assertFalse(message.isBlank(), missingMessage);
  }

  private static List<Sequence<Integer>> nonExportableNonResettableSequences_ShouldThrowUnsupportedOperationException() {
    List<Integer> source = List.of(1, 2, 3, 4, 5);
    return List.of(
            SequenceFactories.iterator(source.iterator()),
            SequenceFactories.spliterator(source.spliterator()),
            SequenceFactories.stream(source.stream())
    );
  }

  @ParameterizedTest
  @MethodSource("nonExportableNonResettableSequences_ShouldThrowUnsupportedOperationException")
  public void nonResettableSequences_ShouldThrowUnsupportedOperationException(Sequence<Integer> sequence) {
    assertUnsupportedOperationException(sequence::reset);
  }

  @Test
  public void resettableSequence_ShouldDelegateTheOperations() {
    Sequence<Integer> delegate = spy(SequenceFactories.infinite(1, i -> i + 1));
    Sequence<Integer> resettableSequence = SequenceFactories.resettable(delegate);

    resettableSequence.next();
    verify(delegate).next();

    resettableSequence.reset();
    verify(delegate).reset();

    ExportStrategy<Integer> exportStrategy = SequenceExport::new;
    resettableSequence.export(exportStrategy);
    verify(delegate).export(exportStrategy);
  }
}
