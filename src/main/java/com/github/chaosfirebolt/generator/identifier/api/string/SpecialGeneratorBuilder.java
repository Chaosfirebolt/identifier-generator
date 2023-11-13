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

import com.github.chaosfirebolt.generator.identifier.api.string.part.SpecialCharacterPart;
import com.github.chaosfirebolt.generator.identifier.internal.builders.TypeSpecificStringIdentifierBuilder;

import java.util.OptionalInt;

/**
 * Internal!
 * @param <T> concrete type of the builder
 */
public interface SpecialGeneratorBuilder<T extends TypeSpecificStringIdentifierBuilder<T>> {

    /**
     * @param specialCharacterLength desired length of special characters
     * @return this builder
     */
    T setSpecialCharacterLength(int specialCharacterLength);

    /**
     * @param minSpecialCharacterLength desired minimum length of special characters
     * @return this builder
     */
    T setMinSpecialCharacterLength(int minSpecialCharacterLength);

    /**
     * @return special character length currently set in the builder
     */
    int getSpecialCharacterLength();

    /**
     * @return optional describing the minimum special character length currently set in the builder
     */
    OptionalInt getMinSpecialCharacterLength();

    /**
     * Factory method creating a {@link SpecialCharacterPart} with the current data.
     * @return a new part
     */
    default SpecialCharacterPart createSpecialCharacterPart() {
        int minLength = getMinSpecialCharacterLength().orElseGet(this::getSpecialCharacterLength);
        return new SpecialCharacterPart(getSpecialCharacterLength(), minLength);
    }
}
