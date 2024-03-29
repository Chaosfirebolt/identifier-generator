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

import com.github.chaosfirebolt.generator.identifier.api.exception.InvalidGeneratorRuleException;
import com.github.chaosfirebolt.generator.identifier.api.string.StringIdentifierGenerator;
import com.github.chaosfirebolt.generator.identifier.api.string.part.Part;
import com.github.chaosfirebolt.generator.identifier.api.string.rule.GeneratorRule;
import com.github.chaosfirebolt.generator.identifier.api.string.validation.MinimumLengthEqualOrLessThanLengthRuleValidator;
import com.github.chaosfirebolt.generator.identifier.internal.util.CharacterUtility;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Created by ChaosFire on 24-Dec-21
 */
public class StringIdentifierGeneratorValidationTests {

  private static final List<Character> CHARACTERS = CharacterUtility.characterListFromIntRange(97, 123);

  private static void assertException(Executable executable, String expectedMessage) {
    InvalidGeneratorRuleException exception = assertThrows(InvalidGeneratorRuleException.class, executable);
    String actualMessage = exception.getMessage();
    assertEquals(expectedMessage, actualMessage);
  }

  @Test
  public void minimumLengthHigherThanLength_ShouldThrowInvalidGeneratorRuleException() {
    Part part = new TestPart(15, 10, CHARACTERS);
    GeneratorRule rule = new TestGeneratorRule(part, 15, 16);
    String expectedErrorMessage = "Required minimum length of '16' must be equal to or less than total length, which is '15'";
    assertException(() -> new StringIdentifierGenerator(rule, new MinimumLengthEqualOrLessThanLengthRuleValidator()), expectedErrorMessage);
  }

  @Test
  public void ruleMinimumLengthLessThanPartsMinLength_ShouldThrowInvalidGeneratorRuleException() {
    Part part = new TestPart(15, 10, CHARACTERS);
    GeneratorRule rule = new TestGeneratorRule(part, 15, 9);
    String expectedErrorMessage = "Required minimum length of '9' must be equal to sum of parts minimum lengths, which is '10'";
    assertException(() -> new StringIdentifierGenerator(rule), expectedErrorMessage);
  }

  @Test
  public void ruleMinimumLengthMoreThanPartsMinLength_ShouldThrowInvalidGeneratorRuleException() {
    Part part = new TestPart(15, 10, CHARACTERS);
    GeneratorRule rule = new TestGeneratorRule(part, 15, 11);
    String expectedErrorMessage = "Required minimum length of '11' must be equal to sum of parts minimum lengths, which is '10'";
    assertException(() -> new StringIdentifierGenerator(rule), expectedErrorMessage);
  }

  @Test
  public void ruleLengthLessThanPartsLength_ShouldThrowInvalidGeneratorRuleException() {
    Part part = new TestPart(15, 10, CHARACTERS);
    GeneratorRule rule = new TestGeneratorRule(part, 14, 10);
    String expectedErrorMessage = "Required length of '14' must be equal to sum of parts lengths, which is '15'";
    assertException(() -> new StringIdentifierGenerator(rule), expectedErrorMessage);
  }

  @Test
  public void ruleLengthMoreThanPartsLength_ShouldThrowInvalidGeneratorRuleException() {
    Part part = new TestPart(15, 10, CHARACTERS);
    GeneratorRule rule = new TestGeneratorRule(part, 16, 10);
    String expectedErrorMessage = "Required length of '16' must be equal to sum of parts lengths, which is '15'";
    assertException(() -> new StringIdentifierGenerator(rule), expectedErrorMessage);
  }

  private record TestPart(int length, int minLength, List<Character> characters) implements Part {

    @Override
    public int getLength() {
      return length();
    }

    @Override
    public List<Character> getCharacters() {
      return characters();
    }
  }

  private static final class TestGeneratorRule implements GeneratorRule {

    private final List<Part> parts;
    private final int length;
    private final int minLength;

    private TestGeneratorRule(Part part, int length, int minLength) {
      this.parts = Collections.singletonList(part);
      this.length = length;
      this.minLength = minLength;
    }

    @Override
    public List<Part> getParts() {
      return this.parts;
    }

    @Override
    public int getLength() {
      return this.length;
    }

    @Override
    public int getMinLength() {
      return this.minLength;
    }
  }
}