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

package com.github.chaosfirebolt.generator.identifier.part;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Basic implementation of {@link Part}
 * <br>
 * Created by ChaosFire on 12/5/2021
 */
public class BasePart implements Part {

    private static final String LENGTH_PARAM_NAME = "Part length";
    private static final String MIN_LENGTH_PARAM_NAME = "Minimum part length";
    private static final int MIN_PART_LENGTH = 1;
    private static final String NULL_CHARACTERS_ERROR_MESSAGE = "Characters list can't be null";
    private static final String EMPTY_CHARACTERS_ERROR_MESSAGE = "Characters list can't be empty";

    /**
     * Desired length of this part.
     */
    private final int length;

    /**
     * Desired minimum length of this part.
     */
    private final int minLength;

    /**
     * Possible characters fot this part.
     */
    private final List<Character> characters;

    /**
     * Constructor creating identifier part from supplied length and characters.
     * <br>
     * Collection of characters is changed to unmodifiable list.
     * @param length required length of the part
     * @param characters possible characters for the part
     * @throws IllegalArgumentException if length is less than 1, or characters is empty
     * @throws NullPointerException if characters is null
     */
    public BasePart(int length, List<Character> characters) {
        this(length, length, characters);
    }

    /**
     * Constructor creating identifier part from supplied length, minimum length and characters.
     * <br>
     * Collection of characters is changed to unmodifiable list.
     * @param length required length of the part
     * @param minLength required minimum length of the part
     * @param characters possible characters for the part
     * @throws IllegalArgumentException if length or minLength are less than 1, length is less than minLength, or characters is empty
     * @throws NullPointerException if characters is null
     */
    public BasePart(int length, int minLength, List<Character> characters) {
        validateLength(LENGTH_PARAM_NAME, MIN_PART_LENGTH, length);
        validateLength(MIN_LENGTH_PARAM_NAME, MIN_PART_LENGTH, minLength);
        validateLength(LENGTH_PARAM_NAME, minLength, length);
        validateCharacters(characters);
        this.length = length;
        this.minLength = minLength;
        this.characters = Collections.unmodifiableList(characters);
    }

    private static void validateLength(String parameter, int minValue, int actualValue) {
        if (actualValue < minValue) {
            String errorMessage = String.format("%s can't be less than '%d', but was '%d'", parameter, minValue, actualValue);
            throw new IllegalArgumentException(errorMessage);
        }
    }

    private static void validateCharacters(List<Character> characters) {
        Objects.requireNonNull(characters, NULL_CHARACTERS_ERROR_MESSAGE);
        if (characters.isEmpty()) {
            throw new IllegalArgumentException(EMPTY_CHARACTERS_ERROR_MESSAGE);
        }
    }

    @Override
    public int getLength() {
        return this.length;
    }

    @Override
    public int getMinLength() {
        return this.minLength;
    }

    @Override
    public List<Character> getCharacters() {
        return this.characters;
    }
}