/*
 * Copyright 2021-2022 Boyan Georgiev
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

import com.github.chaosfirebolt.generator.identifier.util.ShuffleUtility;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 * Created by ChaosFire on 19-Dec-21
 */
public class ShuffleUtilityTests {

    @Test
    public void afterShuffle_ArrayShouldBeDifferent() {
        char[] array = new char[]{'a', 'b', 'c', 'd', 'e', '5', '7', '1', '#', '$', '^'};
        char[] copy = new char[array.length];
        System.arraycopy(array, 0, copy, 0, array.length);

        ShuffleUtility.shuffleFisherYates(array);
        assertNotEquals(copy, array);
    }
}