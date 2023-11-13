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

package com.github.chaosfirebolt.generator.rule;

import com.github.chaosfirebolt.generator.identifier.api.string.part.NumericPart;
import com.github.chaosfirebolt.generator.identifier.api.string.part.Part;
import com.github.chaosfirebolt.generator.identifier.api.string.part.SpecialCharacterPart;
import com.github.chaosfirebolt.generator.identifier.api.string.part.UpperAlphabeticPart;
import com.github.chaosfirebolt.generator.identifier.api.string.rule.BaseGeneratorRule;
import com.github.chaosfirebolt.generator.identifier.api.string.rule.GeneratorRule;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by ChaosFire on 21-Dec-21
 */
public class GeneratorRuleTests {

    @ParameterizedTest
    @MethodSource
    public void getParts_ShouldReturnCorrectNumberOfParts(GeneratorRule generatorRule, int expectedNumberOfParts) {
        int size = generatorRule.getParts().size();
        assertEquals(expectedNumberOfParts, size);
    }

    private static Stream<Arguments> getParts_ShouldReturnCorrectNumberOfParts() {
        return allData().map(args -> Arguments.of(args.rule(), args.expectedNumberOfParts()));
    }

    @ParameterizedTest
    @MethodSource
    public void getLength_ShouldReturnCorrectLength(GeneratorRule generatorRule, int expectedLength) {
        int length = generatorRule.getLength();
        assertEquals(expectedLength, length);
    }

    private static Stream<Arguments> getLength_ShouldReturnCorrectLength() {
        return allData().map(args -> Arguments.of(args.rule(), args.expectedLength()));
    }

    @ParameterizedTest
    @MethodSource
    public void getMinLength_ShouldReturnCorrectMinLength(GeneratorRule generatorRule, int expectedMinLength) {
        int minLength = generatorRule.getMinLength();
        assertEquals(expectedMinLength, minLength);
    }

    private static Stream<Arguments> getMinLength_ShouldReturnCorrectMinLength() {
        return allData().map(args -> Arguments.of(args.rule(), args.expectedMinLength()));
    }

    private static Stream<GeneratorRuleArguments> allData() {
        return Stream.of(
                new GeneratorRuleArguments(new BaseGeneratorRule(Arrays.asList(new SpecialCharacterPart(3, 1), new NumericPart(19, 17))), 2, 22, 18),
                new GeneratorRuleArguments(new UnvalidatedGeneratorRule(Collections.singletonList(new UpperAlphabeticPart(10)), 10), 1, 10, 10)
        );
    }

    private record UnvalidatedGeneratorRule(List<Part> parts, int length) implements GeneratorRule {

        @Override
        public List<Part> getParts() {
            return parts();
        }

        @Override
        public int getLength() {
            return length();
        }
    }
}
