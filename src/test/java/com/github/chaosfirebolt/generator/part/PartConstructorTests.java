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

import com.github.chaosfirebolt.generator.identifier.string.part.*;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.IntFunction;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Created by ChaosFire on 19-Dec-21
 */
public class PartConstructorTests {

    @ParameterizedTest
    @MethodSource
    public void positiveLength_ShouldNotThrowException(Executable constructorInvocation) {
        assertDoesNotThrow(constructorInvocation, "Supplying positive length to part constructor should not have thrown exception");
    }

    private static List<Executable> positiveLength_ShouldNotThrowException() {
        List<Executable> invocations = new ArrayList<>();
        for (IntFunction<Part> constructorInvocation : lengthConstructorInvocations()) {
            for (Integer validLength : validLengths()) {
                invocations.add(() -> constructorInvocation.apply(validLength));
            }
        }
        for (BiFunction<Integer, Integer, Part> constructorInvocation : lengthMinLengthConstructorInvocations()) {
            for (Integer validLength1 : validLengths()) {
                for (Integer validLength2 : validLengths()) {
                    addIfLengthIsGreaterThanOrEqualToMinLength(invocations, constructorInvocation, validLength1, validLength2);
                    addIfLengthIsGreaterThanOrEqualToMinLength(invocations, constructorInvocation, validLength2, validLength1);
                }
            }
        }
        return invocations;
    }

    private static void addIfLengthIsGreaterThanOrEqualToMinLength(List<Executable> invocations, BiFunction<Integer, Integer, Part> constructorInvocation, int length, int minLength) {
        if (length >= minLength) {
            invocations.add(() -> constructorInvocation.apply(length, minLength));
        }
    }

    @ParameterizedTest
    @MethodSource
    public void negativeOrZeroLength_ShouldThrowException(Executable constructorInvocation) {
        assertThrows(IllegalArgumentException.class, constructorInvocation, "Supplying negative or zero length to part constructor should have thrown 'IllegalArgumentException'");
    }

    private static List<Executable> negativeOrZeroLength_ShouldThrowException() {
        List<Executable> invocations = new ArrayList<>();
        for (IntFunction<Part> constructorInvocation : lengthConstructorInvocations()) {
            for (Integer invalidLength : invalidLengths()) {
                invocations.add(() -> constructorInvocation.apply(invalidLength));
            }
        }
        for (BiFunction<Integer, Integer, Part> constructorInvocation : lengthMinLengthConstructorInvocations()) {
            for (Integer invalidLength : invalidLengths()) {
                for (Integer validLength : validLengths()) {
                    invocations.add(() -> constructorInvocation.apply(invalidLength, validLength));
                    invocations.add(() -> constructorInvocation.apply(validLength, invalidLength));
                }
            }
        }
        return invocations;
    }

    private static List<IntFunction<Part>> lengthConstructorInvocations() {
        return Arrays.asList(LowerAlphabeticPart::new, NumericPart::new, SpecialCharacterPart::new, UpperAlphabeticPart::new);
    }

    private static List<BiFunction<Integer, Integer, Part>> lengthMinLengthConstructorInvocations() {
        return Arrays.asList(LowerAlphabeticPart::new, NumericPart::new, SpecialCharacterPart::new, UpperAlphabeticPart::new);
    }

    private static List<Integer> invalidLengths() {
        return Arrays.asList(0, -3, -4);
    }

    private static List<Integer> validLengths() {
        return Arrays.asList(1, 11, 23);
    }
}