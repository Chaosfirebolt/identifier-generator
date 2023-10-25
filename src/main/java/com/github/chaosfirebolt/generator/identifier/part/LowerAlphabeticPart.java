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

import com.github.chaosfirebolt.generator.identifier.util.CharacterUtility;

import java.util.List;

/**
 * identifier part for lower alphabetic characters.
 * <br>
 * Created by ChaosFire on 12/5/2021
 */
public class LowerAlphabeticPart extends BasePart {

    private static final List<Character> CHARACTERS = CharacterUtility.characterListFromIntRange(97, 123);

    /**
     * Constructor creating instance of identifier part for lower alphabetic chars, with desired length.
     * <br>
     * Minimum length is equal to the specified length.
     * @param length required length of the part
     * @throws IllegalArgumentException if length is less than 1
     */
    public LowerAlphabeticPart(int length) {
        this(length, length);
    }

    /**
     * Constructor creating instance of identifier part for lower alphabetic chars, with desired length and minimum length
     * @param length required length of the part
     * @param minLength required minimum length of the part
     * @throws IllegalArgumentException if length or minLength are less than 1 or length is less than minLength
     */
    public LowerAlphabeticPart(int length, int minLength) {
        super(length, minLength, CHARACTERS);
    }
}