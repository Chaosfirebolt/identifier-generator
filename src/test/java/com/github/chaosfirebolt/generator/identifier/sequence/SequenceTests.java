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
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SequenceTests {

  @ParameterizedTest
  @MethodSource
  public void correctSequenceOfElements(Sequence<Integer> sequence) {
    List<Integer> expected = List.of(1, 3, 5, 7, 9);
    List<Integer> actual = new ArrayList<>(5);
    for (int i = 0; i < 5; i++) {
      actual.add(sequence.next());
    }
    assertEquals(expected, actual, "Incorrect sequence");
  }

  private static List<Sequence<Integer>> correctSequenceOfElements() {
    Integer initial = 1;
    Calculation<Integer> calculation = i -> i + 2;
    return List.of(Sequence.infinite(initial, calculation), Sequence.finite(initial, calculation, num -> num < 1000));
  }
}
