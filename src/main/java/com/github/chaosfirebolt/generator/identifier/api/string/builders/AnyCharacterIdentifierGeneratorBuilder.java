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

package com.github.chaosfirebolt.generator.identifier.api.string.builders;

import com.github.chaosfirebolt.generator.identifier.api.string.part.Part;
import com.github.chaosfirebolt.generator.identifier.internal.builders.TypeSpecificStringIdentifierBuilder;
import com.github.chaosfirebolt.generator.identifier.internal.builders.mixin.LowerAlphabeticBuilderData;
import com.github.chaosfirebolt.generator.identifier.internal.builders.mixin.NumericBuilderData;
import com.github.chaosfirebolt.generator.identifier.internal.builders.mixin.SpecialBuilderData;
import com.github.chaosfirebolt.generator.identifier.internal.builders.mixin.UpperAlphabeticBuilderData;
import com.github.chaosfirebolt.generator.identifier.internal.util.OptionalUtility;
import org.apiguardian.api.API;

import java.util.List;
import java.util.OptionalInt;

/**
 * Specification of {@link StringIdentifierGeneratorBuilder} to allow easier setup for any character (alphanumeric + special; symbols) identifier generators.
 */
@API(status = API.Status.STABLE, since = "2.0.0")
public final class AnyCharacterIdentifierGeneratorBuilder extends TypeSpecificStringIdentifierBuilder<AnyCharacterIdentifierGeneratorBuilder>
        implements LowerAlphabeticGeneratorBuilder<AnyCharacterIdentifierGeneratorBuilder>, UpperAlphabeticBuilder<AnyCharacterIdentifierGeneratorBuilder>,
                   NumericGeneratorBuilder<AnyCharacterIdentifierGeneratorBuilder>, SpecialGeneratorBuilder<AnyCharacterIdentifierGeneratorBuilder>,
                   LowerAlphabeticBuilderData<AnyCharacterIdentifierGeneratorBuilder>, UpperAlphabeticBuilderData<AnyCharacterIdentifierGeneratorBuilder>,
                   NumericBuilderData<AnyCharacterIdentifierGeneratorBuilder>, SpecialBuilderData<AnyCharacterIdentifierGeneratorBuilder> {

  private int lowerCaseLength;
  private int minLowerCaseLength;
  private int upperCaseLength;
  private int minUpperCaseLength;
  private int numericLength;
  private int minNumericLength;
  private int specialCharacterLength;
  private int minSpecialCharacterLength;

  @API(status = API.Status.INTERNAL, since = "2.0.0")
  AnyCharacterIdentifierGeneratorBuilder() {
  }

  @Override
  public AnyCharacterIdentifierGeneratorBuilder setLowerCaseLength(int lowerCaseLength) {
    this.lowerCaseLength = lowerCaseLength;
    return this;
  }

  @Override
  public AnyCharacterIdentifierGeneratorBuilder setMinLowerCaseLength(int minLowerCaseLength) {
    this.minLowerCaseLength = minLowerCaseLength;
    return this;
  }

  @Override
  @API(status = API.Status.INTERNAL, since = "2.0.0")
  public int getLowerCaseLength() {
    return this.lowerCaseLength;
  }

  @Override
  @API(status = API.Status.INTERNAL, since = "2.0.0")
  public OptionalInt getMinLowerCaseLength() {
    return OptionalUtility.fromInt(this.minLowerCaseLength);
  }

  @Override
  public AnyCharacterIdentifierGeneratorBuilder setNumericLength(int numericLength) {
    this.numericLength = numericLength;
    return this;
  }

  @Override
  public AnyCharacterIdentifierGeneratorBuilder setMinNumericLength(int minNumericLength) {
    this.minNumericLength = minNumericLength;
    return this;
  }

  @Override
  @API(status = API.Status.INTERNAL, since = "2.0.0")
  public int getNumericLength() {
    return this.numericLength;
  }

  @Override
  @API(status = API.Status.INTERNAL, since = "2.0.0")
  public OptionalInt getMinNumericLength() {
    return OptionalUtility.fromInt(this.minNumericLength);
  }

  @Override
  public AnyCharacterIdentifierGeneratorBuilder setSpecialCharacterLength(int specialCharacterLength) {
    this.specialCharacterLength = specialCharacterLength;
    return this;
  }

  @Override
  public AnyCharacterIdentifierGeneratorBuilder setMinSpecialCharacterLength(int minSpecialCharacterLength) {
    this.minSpecialCharacterLength = minSpecialCharacterLength;
    return this;
  }

  @Override
  @API(status = API.Status.INTERNAL, since = "2.0.0")
  public int getSpecialCharacterLength() {
    return this.specialCharacterLength;
  }

  @Override
  @API(status = API.Status.INTERNAL, since = "2.0.0")
  public OptionalInt getMinSpecialCharacterLength() {
    return OptionalUtility.fromInt(this.minSpecialCharacterLength);
  }

  @Override
  protected List<Part> getParts() {
    return List.of(createLowerAlphabeticPart(), createUpperAlphabeticPart(), createNumericPart(), createSpecialCharacterPart());
  }

  @Override
  public AnyCharacterIdentifierGeneratorBuilder setUpperCaseLength(int upperCaseLength) {
    this.upperCaseLength = upperCaseLength;
    return this;
  }

  @Override
  public AnyCharacterIdentifierGeneratorBuilder setMinUpperCaseLength(int minUpperCaseLength) {
    this.minUpperCaseLength = minUpperCaseLength;
    return this;
  }

  @Override
  @API(status = API.Status.INTERNAL, since = "2.0.0")
  public int getUpperCaseLength() {
    return this.upperCaseLength;
  }

  @Override
  @API(status = API.Status.INTERNAL, since = "2.0.0")
  public OptionalInt getMinUpperCaseLength() {
    return OptionalUtility.fromInt(this.minUpperCaseLength);
  }
}
