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

package com.github.chaosfirebolt.generator.identifier.string.util;

import com.github.chaosfirebolt.generator.identifier.string.part.Part;

import java.util.List;

/**
 * Calculation utility methods.
 * <br>
 * Created by ChaosFire on 12/6/2021
 */
public final class CalculationUtility {

    private CalculationUtility() {
        throw new RuntimeException("No instances allowed");
    }

    /**
     * Calculates the total length of all parts.
     * @param parts parts whose total length is to be calculated
     * @return the total length
     */
    public static int totalLength(List<Part> parts) {
        int sum = 0;
        for (Part part : parts) {
            sum += part.getLength();
        }
        return sum;
    }

    /**
     * Calculates the minimum length of all parts.
     * @param parts parts whose total minimum length is to be calculated
     * @return the total minimum length
     */
    public static int minimumLength(List<Part> parts) {
        int sum = 0;
        for (Part part : parts) {
            sum += part.minLength();
        }
        return sum;
    }
}
