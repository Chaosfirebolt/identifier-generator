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

package com.github.chaosfirebolt.generator.util;

import com.github.chaosfirebolt.generator.token.util.CharacterUtility;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by ChaosFire on 19-Dec-21
 */
public class CharacterUtilityTests {

    @Test
    public void getCharacterListFromRange_ShouldReturnCorrect() {
        List<Character> expected = Arrays.asList('A', 'B', 'C');
        List<Character> actual = CharacterUtility.characterListFromIntRange(65, 68);
        assertEquals(expected, actual);
    }
}