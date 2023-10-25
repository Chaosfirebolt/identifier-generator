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

package com.github.chaosfirebolt.generator.identifier.constructor;

import com.github.chaosfirebolt.generator.identifier.IdentifierGenerator;
import com.github.chaosfirebolt.generator.identifier.exception.InvalidGeneratorRuleException;
import com.github.chaosfirebolt.generator.identifier.impl.*;
import com.github.chaosfirebolt.generator.identifier.validation.BaseRuleValidator;
import com.github.chaosfirebolt.generator.identifier.validation.RuleValidator;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.*;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by ChaosFire on 23-Dec-21
 */
public class IdentifierGeneratorConstructorTests {

    private static final String ERROR_MESSAGE = "identifier length can't be less than 30";
    private static final RuleValidator MIN_IDENTIFIER_LENGTH_VALIDATOR = new BaseRuleValidator(rule -> rule.getLength() >= 30, rule -> ERROR_MESSAGE);

    private static InvalidConstructorInvocationWrapper buildWrapperForIllegalArgument(Executable executable, int length) {
        return new InvalidConstructorInvocationWrapper(executable, IllegalArgumentException.class, String.format("Part length can't be less than '1', but was '%d'", length));
    }

    private static InvalidConstructorInvocationWrapper buildWrapperForInvalidGeneratorRule(Executable executable) {
        return new InvalidConstructorInvocationWrapper(executable, InvalidGeneratorRuleException.class, ERROR_MESSAGE);
    }

    @ParameterizedTest
    @MethodSource
    public void anyLengthParamIsNegative_ShouldThrowInvalidArgumentException(InvalidConstructorInvocationWrapper wrapper) {
        assertException(wrapper);
    }

    private static List<Arguments> anyLengthParamIsNegative_ShouldThrowInvalidArgumentException() {
        List<InvalidConstructorInvocationWrapper> list = new ArrayList<>();
        list.add(buildWrapperForIllegalArgument(() -> new AlphaNumericIdentifierGenerator(-1, 9, 1), -1));
        list.add(buildWrapperForIllegalArgument(() -> new AlphaNumericIdentifierGenerator(9, -3, 1), -3));
        list.add(buildWrapperForIllegalArgument(() -> new AlphaNumericIdentifierGenerator(9, 3, -2), -2));
        list.add(buildWrapperForIllegalArgument(() -> new AlphabeticIdentifierGenerator(-1, 9), -1));
        list.add(buildWrapperForIllegalArgument(() -> new AlphabeticIdentifierGenerator(9, -3), -3));
        list.add(buildWrapperForIllegalArgument(() -> new AnyCharacterIdentifierGenerator(-1, 9, 7, 8), -1));
        list.add(buildWrapperForIllegalArgument(() -> new AnyCharacterIdentifierGenerator(9, -3, 1, 3), -3));
        list.add(buildWrapperForIllegalArgument(() -> new AnyCharacterIdentifierGenerator(9, 3, -1, 3), -1));
        list.add(buildWrapperForIllegalArgument(() -> new AnyCharacterIdentifierGenerator(9, 3, 1, -3), -3));
        list.add(buildWrapperForIllegalArgument(() -> new LowerAlphabeticIdentifierGenerator(-6), -6));
        list.add(buildWrapperForIllegalArgument(() -> new NumericIdentifierGenerator(-6), -6));
        list.add(buildWrapperForIllegalArgument(() -> new UpperAlphabeticIdentifierGenerator(-6), -6));
        return list.stream().map(Arguments::of).collect(Collectors.toList());
    }

    private static void assertException(InvalidConstructorInvocationWrapper wrapper) {
        Class<? extends Throwable> exceptionClass = wrapper.expectedException();
        Throwable throwable = assertThrows(exceptionClass, wrapper.invalidInvocation(), () -> String.format("Expected '%s' was not thrown", exceptionClass.getSimpleName()));
        assertEquals(wrapper.expectedErrorMessage(), throwable.getMessage(), "Incorrect exception message");
    }

    @ParameterizedTest
    @MethodSource
    public void anyLengthParamIsZero_ShouldThrowInvalidArgumentException(InvalidConstructorInvocationWrapper wrapper) {
        assertException(wrapper);
    }

    private static List<Arguments> anyLengthParamIsZero_ShouldThrowInvalidArgumentException() {
        List<InvalidConstructorInvocationWrapper> list = new ArrayList<>();
        list.add(buildWrapperForIllegalArgument(() -> new AlphaNumericIdentifierGenerator(0, 9, 3), 0));
        list.add(buildWrapperForIllegalArgument(() -> new AlphaNumericIdentifierGenerator(9, 0, 3), 0));
        list.add(buildWrapperForIllegalArgument(() -> new AlphaNumericIdentifierGenerator(9, 3, 0), 0));
        list.add(buildWrapperForIllegalArgument(() -> new AlphabeticIdentifierGenerator(0, 9), 0));
        list.add(buildWrapperForIllegalArgument(() -> new AlphabeticIdentifierGenerator(9, 0), 0));
        list.add(buildWrapperForIllegalArgument(() -> new AnyCharacterIdentifierGenerator(0, 9, 7, 8), 0));
        list.add(buildWrapperForIllegalArgument(() -> new AnyCharacterIdentifierGenerator(9, 0, 1, 3), 0));
        list.add(buildWrapperForIllegalArgument(() -> new AnyCharacterIdentifierGenerator(9, 3, 0, 3), 0));
        list.add(buildWrapperForIllegalArgument(() -> new AnyCharacterIdentifierGenerator(9, 3, 1, 0), 0));
        list.add(buildWrapperForIllegalArgument(() -> new LowerAlphabeticIdentifierGenerator(0), 0));
        list.add(buildWrapperForIllegalArgument(() -> new NumericIdentifierGenerator(0), 0));
        list.add(buildWrapperForIllegalArgument(() -> new UpperAlphabeticIdentifierGenerator(0), 0));
        return list.stream().map(Arguments::of).collect(Collectors.toList());
    }

    @ParameterizedTest
    @MethodSource
    public void validLengthParams_ShouldNotThrow(Callable<? extends IdentifierGenerator<?>> constructorInvocation) throws Throwable {
        assertInstanceGenerated(constructorInvocation);
    }

    private static List<Arguments> validLengthParams_ShouldNotThrow() {
        List<Callable<? extends IdentifierGenerator<?>>> list = new ArrayList<>();
        list.add(() -> new AlphaNumericIdentifierGenerator(5, 5, 1));
        list.add(() -> new AlphabeticIdentifierGenerator(5, 5));
        list.add(() -> new AnyCharacterIdentifierGenerator(3, 4, 5, 6));
        list.add(() -> new LowerAlphabeticIdentifierGenerator(9));
        list.add(() -> new NumericIdentifierGenerator(9));
        list.add(() -> new UpperAlphabeticIdentifierGenerator(9));
        return list.stream().map(Arguments::of).collect(Collectors.toList());
    }

    private static void assertInstanceGenerated(Callable<? extends IdentifierGenerator<?>> constructorInvocation) throws Exception {
        IdentifierGenerator<?> instance = constructorInvocation.call();
        assertNotNull(instance, "New instance was null");
    }

    @ParameterizedTest
    @MethodSource
    public void lengthValidatorsConstructor_ParamsDoNotConformToRules_ShouldThrowInvalidGeneratorRuleException(InvalidConstructorInvocationWrapper wrapper) {
        assertException(wrapper);
    }

    private static List<Arguments> lengthValidatorsConstructor_ParamsDoNotConformToRules_ShouldThrowInvalidGeneratorRuleException() {
        List<InvalidConstructorInvocationWrapper> list = new ArrayList<>();
        list.add(buildWrapperForInvalidGeneratorRule(() -> new AlphaNumericIdentifierGenerator(9, 9, 9, Collections.singletonList(MIN_IDENTIFIER_LENGTH_VALIDATOR))));
        list.add(buildWrapperForInvalidGeneratorRule(() -> new AlphabeticIdentifierGenerator(9, 9, Collections.singletonList(MIN_IDENTIFIER_LENGTH_VALIDATOR))));
        int anyCharLen = 7;
        list.add(buildWrapperForInvalidGeneratorRule(() -> new AnyCharacterIdentifierGenerator(anyCharLen, anyCharLen, anyCharLen, anyCharLen, Collections.singletonList(MIN_IDENTIFIER_LENGTH_VALIDATOR))));
        list.add(buildWrapperForInvalidGeneratorRule(() -> new LowerAlphabeticIdentifierGenerator(9, Collections.singletonList(MIN_IDENTIFIER_LENGTH_VALIDATOR))));
        list.add(buildWrapperForInvalidGeneratorRule(() -> new NumericIdentifierGenerator(9, Collections.singletonList(MIN_IDENTIFIER_LENGTH_VALIDATOR))));
        list.add(buildWrapperForInvalidGeneratorRule(() -> new UpperAlphabeticIdentifierGenerator(9, Collections.singletonList(MIN_IDENTIFIER_LENGTH_VALIDATOR))));
        return list.stream().map(Arguments::of).collect(Collectors.toList());
    }

    @ParameterizedTest
    @MethodSource
    public void lengthValidatorsConstructor_ParamsConformToRules_ShouldNotThrow(Callable<? extends IdentifierGenerator<?>> constructorInvocation) throws Exception {
        assertInstanceGenerated(constructorInvocation);
    }

    private static List<Arguments> lengthValidatorsConstructor_ParamsConformToRules_ShouldNotThrow() {
        List<Callable<? extends IdentifierGenerator<?>>> list = new ArrayList<>();
        list.add(() -> new AlphaNumericIdentifierGenerator(10, 10, 10, Collections.singletonList(MIN_IDENTIFIER_LENGTH_VALIDATOR)));
        list.add(() -> new AlphaNumericIdentifierGenerator(20, 11, 5, Collections.singletonList(MIN_IDENTIFIER_LENGTH_VALIDATOR)));
        list.add(() -> new AlphabeticIdentifierGenerator(15, 15, Collections.singletonList(MIN_IDENTIFIER_LENGTH_VALIDATOR)));
        list.add(() -> new AlphabeticIdentifierGenerator(20, 11, Collections.singletonList(MIN_IDENTIFIER_LENGTH_VALIDATOR)));
        list.add(() -> new AnyCharacterIdentifierGenerator(10, 10, 5, 5, Collections.singletonList(MIN_IDENTIFIER_LENGTH_VALIDATOR)));
        list.add(() -> new LowerAlphabeticIdentifierGenerator(30, Collections.singletonList(MIN_IDENTIFIER_LENGTH_VALIDATOR)));
        list.add(() -> new NumericIdentifierGenerator(30, Collections.singletonList(MIN_IDENTIFIER_LENGTH_VALIDATOR)));
        list.add(() -> new UpperAlphabeticIdentifierGenerator(30, Collections.singletonList(MIN_IDENTIFIER_LENGTH_VALIDATOR)));
        return list.stream().map(Arguments::of).collect(Collectors.toList());
    }

    @ParameterizedTest
    @MethodSource
    public void randomIntsValidatorsConstructor_ParamsDoNotConformToRules_ShouldThrowInvalidGeneratorRuleException(InvalidConstructorInvocationWrapper wrapper) {
        assertException(wrapper);
    }

    private static List<Arguments> randomIntsValidatorsConstructor_ParamsDoNotConformToRules_ShouldThrowInvalidGeneratorRuleException() {
        List<InvalidConstructorInvocationWrapper> list = new ArrayList<>();
        list.add(buildWrapperForInvalidGeneratorRule(() -> new AlphaNumericIdentifierGenerator(new Random(), 9, 9, 9, Collections.singletonList(MIN_IDENTIFIER_LENGTH_VALIDATOR))));
        list.add(buildWrapperForInvalidGeneratorRule(() -> new AlphabeticIdentifierGenerator(new Random(), 9, 9, Collections.singletonList(MIN_IDENTIFIER_LENGTH_VALIDATOR))));
        int length = 7;
        list.add(buildWrapperForInvalidGeneratorRule(() -> new AnyCharacterIdentifierGenerator(new Random(), length, length, length, length, Collections.singletonList(MIN_IDENTIFIER_LENGTH_VALIDATOR))));
        list.add(buildWrapperForInvalidGeneratorRule(() -> new LowerAlphabeticIdentifierGenerator(new Random(), 9, Collections.singletonList(MIN_IDENTIFIER_LENGTH_VALIDATOR))));
        list.add(buildWrapperForInvalidGeneratorRule(() -> new NumericIdentifierGenerator(new Random(), 9, Collections.singletonList(MIN_IDENTIFIER_LENGTH_VALIDATOR))));
        list.add(buildWrapperForInvalidGeneratorRule(() -> new UpperAlphabeticIdentifierGenerator(new Random(), 9, Collections.singletonList(MIN_IDENTIFIER_LENGTH_VALIDATOR))));
        return list.stream().map(Arguments::of).collect(Collectors.toList());
    }

    @ParameterizedTest
    @MethodSource
    public void randomIntsValidatorsConstructor_ParamsConformToRules_ShouldNotThrow(Callable<? extends IdentifierGenerator<?>> constructorInvocation) throws Exception {
        assertInstanceGenerated(constructorInvocation);
    }

    private static List<Arguments> randomIntsValidatorsConstructor_ParamsConformToRules_ShouldNotThrow() {
        List<Callable<? extends IdentifierGenerator<?>>> list = new ArrayList<>();
        list.add(() -> new AlphaNumericIdentifierGenerator(new Random(), 10, 10, 10, Collections.singletonList(MIN_IDENTIFIER_LENGTH_VALIDATOR)));
        list.add(() -> new AlphaNumericIdentifierGenerator(new Random(), 20, 11, 3, Collections.singletonList(MIN_IDENTIFIER_LENGTH_VALIDATOR)));
        list.add(() -> new AlphabeticIdentifierGenerator(new Random(), 15, 15, Collections.singletonList(MIN_IDENTIFIER_LENGTH_VALIDATOR)));
        list.add(() -> new AlphabeticIdentifierGenerator(new Random(), 20, 11, Collections.singletonList(MIN_IDENTIFIER_LENGTH_VALIDATOR)));
        list.add(() -> new AnyCharacterIdentifierGenerator(new Random(), 10, 10, 5, 5, Collections.singletonList(MIN_IDENTIFIER_LENGTH_VALIDATOR)));
        list.add(() -> new LowerAlphabeticIdentifierGenerator(new Random(), 40, Collections.singletonList(MIN_IDENTIFIER_LENGTH_VALIDATOR)));
        list.add(() -> new NumericIdentifierGenerator(new Random(), 40, Collections.singletonList(MIN_IDENTIFIER_LENGTH_VALIDATOR)));
        list.add(() -> new UpperAlphabeticIdentifierGenerator(new Random(), 40, Collections.singletonList(MIN_IDENTIFIER_LENGTH_VALIDATOR)));
        return list.stream().map(Arguments::of).collect(Collectors.toList());
    }
}