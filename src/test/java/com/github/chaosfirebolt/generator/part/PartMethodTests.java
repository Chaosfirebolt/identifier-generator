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

package com.github.chaosfirebolt.generator.part;

import com.github.chaosfirebolt.generator.identifier.part.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by ChaosFire on 19-Dec-21
 */
public class PartMethodTests {

    @ParameterizedTest
    @MethodSource
    public void getLength_ResultShouldBeCorrect(Part part, int expectedLength) {
        int actual = part.getLength();
        assertEquals(expectedLength, actual);
    }

    private static Stream<Arguments> getLength_ResultShouldBeCorrect() {
        return allData().map(args -> Arguments.of(args.part(), args.expectedLength()));
    }

    @ParameterizedTest
    @MethodSource
    public void getMinLength_ResultShouldBeCorrect(Part part, int expectedMinLength) {
        int actual = part.getMinLength();
        assertEquals(expectedMinLength, actual);
    }

    private static Stream<Arguments> getMinLength_ResultShouldBeCorrect() {
        return allData().map(args -> Arguments.of(args.part(), args.expectedMinLength()));
    }

    @ParameterizedTest
    @MethodSource
    public void getCharacters_ResultShouldBeCorrect(Part part, List<Character> expectedCharacters) {
        List<Character> actual = part.getCharacters();
        assertEquals(expectedCharacters, actual);
    }

    private static Stream<Arguments> getCharacters_ResultShouldBeCorrect() {
        return allData().map(args -> Arguments.of(args.part(), args.expectedCharacters()));
    }

    private static Stream<PartArguments> allData() {
        List<Character> specialChars = Arrays.asList('!', '"', '#', '$', '%', '&', '\'', '(', ')', '*', '+', ',', '-', '.', '/', ':', ';', '<', '=', '>', '?', '@', '[', '\\', ']',
                '^', '_', '`', '{', '|', '}', '~');
        return Stream.of(
                new PartArguments(new SpecialCharacterPart(5, 4), specialChars, 5, 4),
                new PartArguments(new LowerAlphabeticPart(5, 4), codesToChars(97, 123), 5, 4),
                new PartArguments(new NumericPart(7, 3), codesToChars(48, 58), 7, 3),
                new PartArguments(new UpperAlphabeticPart(5, 4), codesToChars(65, 91), 5, 4)
        );
    }

    private static List<Character> codesToChars(int startInclusive, int endExclusive) {
        return IntStream.range(startInclusive, endExclusive).mapToObj(n -> (char) n).collect(Collectors.toList());
    }
}