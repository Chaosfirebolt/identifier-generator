/*
 * Copyright 2021 Boyan Georgiev
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

package com.github.chaosfirebolt.generator.part;

import com.github.chaosfirebolt.generator.identifier.part.NumericPart;

import java.util.Arrays;
import java.util.List;

/**
 * Created by ChaosFire on 19-Dec-21
 */
public class NumericPartMethodTests extends PartMethodTests {

    private static final int LENGTH = 7;
    private static final int MIN_LENGTH = 3;
    private static final List<Character> CHARACTERS = Arrays.asList('0', '1', '2', '3', '4', '5', '6', '7', '8', '9');
    private static final NumericPart PART = new NumericPart(LENGTH, MIN_LENGTH);

    public NumericPartMethodTests() {
        super(LENGTH, MIN_LENGTH, CHARACTERS, PART);
    }
}