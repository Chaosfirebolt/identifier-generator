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

import com.github.chaosfirebolt.generator.identifier.string.part.NumericPart;

import java.util.OptionalInt;

/**
 * Internal!
 * @param <T> concrete type of the builder
 */
interface NumericGeneratorBuilder<T extends TypeSpecificStringIdentifierBuilder<T>> {

    /**
     * @param numericLength desired length of numeric characters
     * @return this builder
     */
    T setNumericLength(int numericLength);

    /**
     * @param minNumericLength desired minimum length of numeric characters
     * @return this builder
     */
    T setMinNumericLength(int minNumericLength);

    /**
     * @return numeric length currently set in the builder
     */
    int getNumericLength();

    /**
     * @return optional describing the minimum numeric length currently set in the builder
     */
    OptionalInt getMinNumericLength();

    /**
     * Factory method creating a {@link NumericPart} with the current data.
     * @return a new part
     */
    default NumericPart createNumericPart() {
        int minLowerCaseLength = getMinNumericLength().orElseGet(this::getNumericLength);
        return new NumericPart(this.getNumericLength(), minLowerCaseLength);
    }
}