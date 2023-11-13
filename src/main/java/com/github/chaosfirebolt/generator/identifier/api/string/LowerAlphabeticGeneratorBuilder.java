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

import com.github.chaosfirebolt.generator.identifier.api.string.part.LowerAlphabeticPart;
import com.github.chaosfirebolt.generator.identifier.internal.builders.TypeSpecificStringIdentifierBuilder;

import java.util.OptionalInt;

/**
 * Internal!
 * @param <T> concrete type of the builder
 */
public interface LowerAlphabeticGeneratorBuilder<T extends TypeSpecificStringIdentifierBuilder<T>> {

    /**
     * @param lowerCaseLength desired length of lower case alphabetic characters
     * @return this builder
     */
    T setLowerCaseLength(int lowerCaseLength);

    /**
     * @param minLowerCaseLength desired minimum length of lower case alphabetic characters
     * @return this builder
     */
    T setMinLowerCaseLength(int minLowerCaseLength);

    /**
     * @return lower case length currently set in the builder
     */
    int getLowerCaseLength();

    /**
     * @return optional describing minimum the lower case length currently set in the builder
     */
    OptionalInt getMinLowerCaseLength();

    /**
     * Factory method creating a {@link LowerAlphabeticPart} with the current data.
     * @return a new part
     */
    default LowerAlphabeticPart createLowerAlphabeticPart() {
        int minLowerCaseLength = getMinLowerCaseLength().orElseGet(this::getLowerCaseLength);
        return new LowerAlphabeticPart(this.getLowerCaseLength(), minLowerCaseLength);
    }
}
