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

package com.github.chaosfirebolt.generator.identifier.util;

import java.security.SecureRandom;
import java.util.random.RandomGenerator;

/**
 * Shuffle utility methods
 * <br>
 * Created by ChaosFire on 12-Dec-21
 */
public class ShuffleUtility {

    /**
     * Shuffles the array using Fisher-Yates algorithm.
     * @param randomGenerator rng to generate random indexes
     * @param array array to be shuffled
     */
    public static void shuffleFisherYates(RandomGenerator randomGenerator, char[] array) {
        for (int i = array.length - 1; i > 0; i--) {
            int randomIndex = randomGenerator.nextInt(i + 1);
            char temp = array[i];
            array[i] = array[randomIndex];
            array[randomIndex] = temp;
        }
    }

    /**
     * Shuffles the array using Fisher-Yates algorithm.
     * @param array array to be shuffled
     */
    public static void shuffleFisherYates(char[] array) {
        shuffleFisherYates(new SecureRandom(), array);
    }
}
