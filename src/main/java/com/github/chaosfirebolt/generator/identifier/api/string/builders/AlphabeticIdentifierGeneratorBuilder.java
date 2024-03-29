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
import com.github.chaosfirebolt.generator.identifier.internal.builders.mixin.UpperAlphabeticBuilderData;
import com.github.chaosfirebolt.generator.identifier.internal.util.OptionalUtility;
import org.apiguardian.api.API;

import java.util.List;
import java.util.OptionalInt;

/**
 * Specification of {@link StringIdentifierGeneratorBuilder} to allow easier setup for alphabetic identifier generators.
 */
@API(status = API.Status.STABLE, since = "2.0.0")
public final class AlphabeticIdentifierGeneratorBuilder extends TypeSpecificStringIdentifierBuilder<AlphabeticIdentifierGeneratorBuilder>
        implements LowerAlphabeticGeneratorBuilder<AlphabeticIdentifierGeneratorBuilder>, UpperAlphabeticBuilder<AlphabeticIdentifierGeneratorBuilder>,
                   LowerAlphabeticBuilderData<AlphabeticIdentifierGeneratorBuilder>, UpperAlphabeticBuilderData<AlphabeticIdentifierGeneratorBuilder> {

  private int lowerCaseLength;
  private int minLowerCaseLength;
  private int upperCaseLength;
  private int minUpperCaseLength;

  @API(status = API.Status.INTERNAL, since = "2.0.0")
  AlphabeticIdentifierGeneratorBuilder() {
  }

  @Override
  protected List<Part> getParts() {
    return List.of(createLowerAlphabeticPart(), createUpperAlphabeticPart());
  }

  @Override
  public AlphabeticIdentifierGeneratorBuilder setLowerCaseLength(int lowerCaseLength) {
    this.lowerCaseLength = lowerCaseLength;
    return this;
  }

  @Override
  public AlphabeticIdentifierGeneratorBuilder setMinLowerCaseLength(int minLowerCaseLength) {
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
  public AlphabeticIdentifierGeneratorBuilder setUpperCaseLength(int upperCaseLength) {
    this.upperCaseLength = upperCaseLength;
    return this;
  }

  @Override
  public AlphabeticIdentifierGeneratorBuilder setMinUpperCaseLength(int minUpperCaseLength) {
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
