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

import com.github.chaosfirebolt.generator.identifier.string.part.Part;
import com.github.chaosfirebolt.generator.identifier.string.util.OptionalUtility;

import java.util.List;
import java.util.OptionalInt;

/**
 * Specification of {@link StringIdentifierGeneratorBuilder} to allow easier setup for lower case alphabetic identifier generators.
 */
public class LowerAlphabeticIdentifierGeneratorBuilder extends TypeSpecificStringIdentifierBuilder<LowerAlphabeticIdentifierGeneratorBuilder>
        implements LowerAlphabeticGeneratorBuilder<LowerAlphabeticIdentifierGeneratorBuilder> {

    private int lowerCaseLength;
    private int minLowerCaseLength;

    LowerAlphabeticIdentifierGeneratorBuilder() {
    }

    @Override
    public LowerAlphabeticIdentifierGeneratorBuilder setLowerCaseLength(int lowerCaseLength) {
        this.lowerCaseLength = lowerCaseLength;
        return this;
    }

    @Override
    public LowerAlphabeticIdentifierGeneratorBuilder setMinLowerCaseLength(int minLowerCaseLength) {
        this.minLowerCaseLength = minLowerCaseLength;
        return this;
    }

    @Override
    public int getLowerCaseLength() {
        return this.lowerCaseLength;
    }

    @Override
    public OptionalInt getMinLowerCaseLength() {
        return OptionalUtility.fromInt(this.minLowerCaseLength);
    }

    @Override
    List<Part> getParts() {
        return List.of(createLowerAlphabeticPart());
    }
}