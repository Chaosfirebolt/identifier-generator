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

package com.github.chaosfirebolt.generator.identifier.api.string;

import com.github.chaosfirebolt.generator.identifier.api.string.part.UpperAlphabeticPart;
import com.github.chaosfirebolt.generator.identifier.internal.builders.TypeSpecificStringIdentifierBuilder;

import java.util.OptionalInt;

/**
 * Internal!
 * @param <T> concrete type of the builder
 */
public interface UpperCaseAlphabeticBuilder<T extends TypeSpecificStringIdentifierBuilder<T>> {

    /**
     * @param upperCaseLength desired length of upper case alphabetic characters
     * @return this builder
     */
    T setUpperCaseLength(int upperCaseLength);

    /**
     * @param minUpperCaseLength desired minimum length of upper case alphabetic characters
     * @return this builder
     */
    T setMinUpperCaseLength(int minUpperCaseLength);

    /**
     * @return upper case length currently set in the builder
     */
    int getUpperCaseLength();

    /**
     * @return optional describing the minimum lower case length currently set in the builder
     */
    OptionalInt getMinUpperCaseLength();

    /**
     * Factory method creating an {@link UpperAlphabeticPart} with the current data.
     * @return a new part
     */
    default UpperAlphabeticPart createUpperAlphabeticPart() {
        int minUpperCaseLength = getMinUpperCaseLength().orElseGet(this::getUpperCaseLength);
        return new UpperAlphabeticPart(getUpperCaseLength(), minUpperCaseLength);
    }
}
