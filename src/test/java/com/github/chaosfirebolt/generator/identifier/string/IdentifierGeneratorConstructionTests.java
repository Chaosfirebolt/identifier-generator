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

package com.github.chaosfirebolt.generator.identifier.string;

import com.github.chaosfirebolt.generator.identifier.api.IdentifierGenerator;
import com.github.chaosfirebolt.generator.identifier.api.exception.InvalidGeneratorRuleException;
import com.github.chaosfirebolt.generator.identifier.api.string.builders.AlphaNumericIdentifierGeneratorBuilder;
import com.github.chaosfirebolt.generator.identifier.api.string.builders.AlphabeticIdentifierGeneratorBuilder;
import com.github.chaosfirebolt.generator.identifier.api.string.builders.AnyCharacterIdentifierGeneratorBuilder;
import com.github.chaosfirebolt.generator.identifier.api.string.builders.StringGeneratorBuilders;
import com.github.chaosfirebolt.generator.identifier.api.string.validation.BaseRuleValidator;
import com.github.chaosfirebolt.generator.identifier.api.string.validation.RuleValidator;
import com.github.chaosfirebolt.generator.identifier.internal.builders.TypeSpecificStringIdentifierBuilder;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by ChaosFire on 23-Dec-21
 */
public class IdentifierGeneratorConstructionTests {

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
    list.add(buildWrapperForIllegalArgument(() -> alphaNumericBuilder(-1, 9, 1).build(), -1));
    list.add(buildWrapperForIllegalArgument(() -> alphaNumericBuilder(9, -3, 1).build(), -3));
    list.add(buildWrapperForIllegalArgument(() -> alphaNumericBuilder(9, 3, -2).build(), -2));
    list.add(buildWrapperForIllegalArgument(() -> alphabeticBuilder(-1, 9).build(), -1));
    list.add(buildWrapperForIllegalArgument(() -> alphabeticBuilder(9, -3).build(), -3));
    list.add(buildWrapperForIllegalArgument(() -> anyCharacterBuilder(-1, 9, 7, 8).build(), -1));
    list.add(buildWrapperForIllegalArgument(() -> anyCharacterBuilder(9, -3, 1, 3).build(), -3));
    list.add(buildWrapperForIllegalArgument(() -> anyCharacterBuilder(9, 3, -1, 3).build(), -1));
    list.add(buildWrapperForIllegalArgument(() -> anyCharacterBuilder(9, 3, 1, -3).build(), -3));
    list.add(buildWrapperForIllegalArgument(() -> StringGeneratorBuilders.lowerAlphabeticIdentifierGeneratorBuilder().setLowerCaseLength(-6).build(), -6));
    list.add(buildWrapperForIllegalArgument(() -> StringGeneratorBuilders.numericIdentifierGeneratorBuilder().setNumericLength(-6).build(), -6));
    list.add(buildWrapperForIllegalArgument(() -> StringGeneratorBuilders.upperAlphabeticIdentifierGeneratorBuilder().setUpperCaseLength(-6).build(), -6));
    return list.stream().map(Arguments::of).collect(Collectors.toList());
  }

  private static AlphaNumericIdentifierGeneratorBuilder alphaNumericBuilder(int lowerCaseLength, int upperCseLength, int numericLength) {
    return StringGeneratorBuilders.alphaNumericIdentifierGeneratorBuilder()
            .setLowerCaseLength(lowerCaseLength)
            .setUpperCaseLength(upperCseLength)
            .setNumericLength(numericLength);
  }

  private static AlphabeticIdentifierGeneratorBuilder alphabeticBuilder(int lowerCaseLength, int upperCaseLength) {
    return StringGeneratorBuilders.alphabeticIdentifierGeneratorBuilder()
            .setLowerCaseLength(lowerCaseLength)
            .setUpperCaseLength(upperCaseLength);
  }

  private static AnyCharacterIdentifierGeneratorBuilder anyCharacterBuilder(int lowerCaseLength, int upperCaseLength, int numericLength, int specialCharLength) {
    return StringGeneratorBuilders.anyCharacterIdentifierGeneratorBuilder()
            .setLowerCaseLength(lowerCaseLength)
            .setUpperCaseLength(upperCaseLength)
            .setNumericLength(numericLength)
            .setSpecialCharacterLength(specialCharLength);
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
    list.add(buildWrapperForIllegalArgument(() -> alphaNumericBuilder(0, 9, 3).build(), 0));
    list.add(buildWrapperForIllegalArgument(() -> alphaNumericBuilder(9, 0, 3).build(), 0));
    list.add(buildWrapperForIllegalArgument(() -> alphaNumericBuilder(9, 3, 0).build(), 0));
    list.add(buildWrapperForIllegalArgument(() -> alphabeticBuilder(0, 9).build(), 0));
    list.add(buildWrapperForIllegalArgument(() -> alphabeticBuilder(9, 0).build(), 0));
    list.add(buildWrapperForIllegalArgument(() -> anyCharacterBuilder(0, 9, 7, 8).build(), 0));
    list.add(buildWrapperForIllegalArgument(() -> anyCharacterBuilder(9, 0, 1, 3).build(), 0));
    list.add(buildWrapperForIllegalArgument(() -> anyCharacterBuilder(9, 3, 0, 3).build(), 0));
    list.add(buildWrapperForIllegalArgument(() -> anyCharacterBuilder(9, 3, 1, 0).build(), 0));
    list.add(buildWrapperForIllegalArgument(() -> StringGeneratorBuilders.lowerAlphabeticIdentifierGeneratorBuilder().setLowerCaseLength(0).build(), 0));
    list.add(buildWrapperForIllegalArgument(() -> StringGeneratorBuilders.numericIdentifierGeneratorBuilder().setNumericLength(0).build(), 0));
    list.add(buildWrapperForIllegalArgument(() -> StringGeneratorBuilders.upperAlphabeticIdentifierGeneratorBuilder().setUpperCaseLength(0).build(), 0));
    return list.stream().map(Arguments::of).collect(Collectors.toList());
  }

  @ParameterizedTest
  @MethodSource
  public void validLengthParams_ShouldNotThrow(Callable<? extends IdentifierGenerator<?>> constructorInvocation) throws Throwable {
    assertInstanceGenerated(constructorInvocation);
  }

  private static List<Arguments> validLengthParams_ShouldNotThrow() {
    List<Callable<? extends IdentifierGenerator<?>>> list = new ArrayList<>();
    list.add(() -> alphaNumericBuilder(5, 5, 1).build());
    list.add(() -> alphabeticBuilder(5, 5).build());
    list.add(() -> anyCharacterBuilder(3, 4, 5, 6).build());
    list.add(() -> StringGeneratorBuilders.lowerAlphabeticIdentifierGeneratorBuilder().setLowerCaseLength(9).build());
    list.add(() -> StringGeneratorBuilders.numericIdentifierGeneratorBuilder().setNumericLength(9).build());
    list.add(() -> StringGeneratorBuilders.upperAlphabeticIdentifierGeneratorBuilder().setUpperCaseLength(9).build());
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
    list.add(buildWrapperForInvalidGeneratorRule(() -> alphaNumericBuilder(9, 9, 9).setRuleValidators(List.of(MIN_IDENTIFIER_LENGTH_VALIDATOR)).build()));
    list.add(buildWrapperForInvalidGeneratorRule(() -> alphabeticBuilder(9, 9).setRuleValidators(List.of(MIN_IDENTIFIER_LENGTH_VALIDATOR)).build()));
    int anyCharLen = 7;
    list.add(buildWrapperForInvalidGeneratorRule(() -> anyCharacterBuilder(anyCharLen, anyCharLen, anyCharLen, anyCharLen).setRuleValidators(List.of(MIN_IDENTIFIER_LENGTH_VALIDATOR)).build()));
    list.add(buildWrapperForInvalidGeneratorRule(() -> StringGeneratorBuilders.lowerAlphabeticIdentifierGeneratorBuilder().setLowerCaseLength(9).addRuleValidator(MIN_IDENTIFIER_LENGTH_VALIDATOR).build()));
    list.add(buildWrapperForInvalidGeneratorRule(() -> StringGeneratorBuilders.numericIdentifierGeneratorBuilder().setNumericLength(9).addRuleValidator(MIN_IDENTIFIER_LENGTH_VALIDATOR).build()));
    list.add(buildWrapperForInvalidGeneratorRule(() -> StringGeneratorBuilders.upperAlphabeticIdentifierGeneratorBuilder().setUpperCaseLength(9).addRuleValidator(MIN_IDENTIFIER_LENGTH_VALIDATOR).build()));
    return list.stream().map(Arguments::of).collect(Collectors.toList());
  }

  @ParameterizedTest
  @MethodSource
  public void lengthValidatorsConstructor_ParamsConformToRules_ShouldNotThrow(Callable<? extends IdentifierGenerator<?>> constructorInvocation) throws Exception {
    assertInstanceGenerated(constructorInvocation);
  }

  private static List<Arguments> lengthValidatorsConstructor_ParamsConformToRules_ShouldNotThrow() {
    List<Callable<? extends IdentifierGenerator<?>>> list = new ArrayList<>();
    list.add(() -> alphaNumericBuilder(10, 10, 10).addRuleValidator(MIN_IDENTIFIER_LENGTH_VALIDATOR).build());
    list.add(() -> alphaNumericBuilder(20, 11, 5).addRuleValidator(MIN_IDENTIFIER_LENGTH_VALIDATOR).build());
    list.add(() -> alphabeticBuilder(15, 15).addRuleValidator(MIN_IDENTIFIER_LENGTH_VALIDATOR).build());
    list.add(() -> alphabeticBuilder(20, 11).addRuleValidator(MIN_IDENTIFIER_LENGTH_VALIDATOR).build());
    list.add(() -> anyCharacterBuilder(10, 10, 5, 5).addRuleValidator(MIN_IDENTIFIER_LENGTH_VALIDATOR).build());
    list.add(() -> StringGeneratorBuilders.lowerAlphabeticIdentifierGeneratorBuilder().setLowerCaseLength(30).addRuleValidator(MIN_IDENTIFIER_LENGTH_VALIDATOR).build());
    list.add(() -> StringGeneratorBuilders.numericIdentifierGeneratorBuilder().setNumericLength(30).addRuleValidator(MIN_IDENTIFIER_LENGTH_VALIDATOR).build());
    list.add(() -> StringGeneratorBuilders.upperAlphabeticIdentifierGeneratorBuilder().setUpperCaseLength(30).addRuleValidator(MIN_IDENTIFIER_LENGTH_VALIDATOR).build());
    return list.stream().map(Arguments::of).collect(Collectors.toList());
  }

  @ParameterizedTest
  @MethodSource
  public void randomIntsValidatorsConstructor_ParamsDoNotConformToRules_ShouldThrowInvalidGeneratorRuleException(InvalidConstructorInvocationWrapper wrapper) {
    assertException(wrapper);
  }

  private static List<Arguments> randomIntsValidatorsConstructor_ParamsDoNotConformToRules_ShouldThrowInvalidGeneratorRuleException() {
    List<InvalidConstructorInvocationWrapper> list = new ArrayList<>();
    list.add(buildWrapperForInvalidGeneratorRule(() -> configureBuilder(alphaNumericBuilder(9, 9, 9)).build()));
    list.add(buildWrapperForInvalidGeneratorRule(() -> configureBuilder(alphabeticBuilder(9, 9)).build()));
    int length = 7;
    list.add(buildWrapperForInvalidGeneratorRule(() -> configureBuilder(anyCharacterBuilder(length, length, length, length)).build()));
    list.add(buildWrapperForInvalidGeneratorRule(() -> configureBuilder(StringGeneratorBuilders.lowerAlphabeticIdentifierGeneratorBuilder().setLowerCaseLength(9)).build()));
    list.add(buildWrapperForInvalidGeneratorRule(() -> configureBuilder(StringGeneratorBuilders.numericIdentifierGeneratorBuilder().setNumericLength(9)).build()));
    list.add(buildWrapperForInvalidGeneratorRule(() -> configureBuilder(StringGeneratorBuilders.upperAlphabeticIdentifierGeneratorBuilder().setUpperCaseLength(9)).build()));
    return list.stream().map(Arguments::of).collect(Collectors.toList());
  }

  @SuppressWarnings("unchecked")
  private static <T extends TypeSpecificStringIdentifierBuilder<?>> T configureBuilder(T builder) {
    return (T) builder.setRandomGenerator(new Random()).addRuleValidator(MIN_IDENTIFIER_LENGTH_VALIDATOR);
  }

  @ParameterizedTest
  @MethodSource
  public void randomIntsValidatorsConstructor_ParamsConformToRules_ShouldNotThrow(Callable<? extends IdentifierGenerator<?>> constructorInvocation) throws Exception {
    assertInstanceGenerated(constructorInvocation);
  }

  private static List<Arguments> randomIntsValidatorsConstructor_ParamsConformToRules_ShouldNotThrow() {
    List<Callable<? extends IdentifierGenerator<?>>> list = new ArrayList<>();
    list.add(() -> configureBuilder(alphaNumericBuilder(10, 10, 10)).build());
    list.add(() -> configureBuilder(alphaNumericBuilder(20, 11, 3)).build());
    list.add(() -> configureBuilder(alphabeticBuilder(15, 15)).build());
    list.add(() -> configureBuilder(alphabeticBuilder(20, 11)).build());
    list.add(() -> configureBuilder(anyCharacterBuilder(10, 10, 5, 5)).build());
    list.add(() -> configureBuilder(StringGeneratorBuilders.lowerAlphabeticIdentifierGeneratorBuilder().setLowerCaseLength(40)).build());
    list.add(() -> configureBuilder(StringGeneratorBuilders.numericIdentifierGeneratorBuilder().setNumericLength(40)).build());
    list.add(() -> configureBuilder(StringGeneratorBuilders.upperAlphabeticIdentifierGeneratorBuilder().setUpperCaseLength(40)).build());
    return list.stream().map(Arguments::of).collect(Collectors.toList());
  }
}