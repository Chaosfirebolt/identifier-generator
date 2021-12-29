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

import com.github.chaosfirebolt.generator.identifier.part.LowerAlphabeticTokenPart;
import com.github.chaosfirebolt.generator.identifier.part.NumericTokenPart;
import com.github.chaosfirebolt.generator.identifier.part.TokenPart;
import com.github.chaosfirebolt.generator.identifier.part.UpperAlphabeticTokenPart;
import com.github.chaosfirebolt.generator.identifier.util.CalculationUtility;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by ChaosFire on 19-Dec-21
 */
public class CalculationUtilityTests {

    private final List<TokenPart> parts = new ArrayList<>();

    @BeforeEach
    void setUp() {
        this.parts.add(new LowerAlphabeticTokenPart(5, 3));
        this.parts.add(new UpperAlphabeticTokenPart(15, 10));
        this.parts.add(new NumericTokenPart(35, 20));
    }

    @AfterEach
    void tearDown() {
        this.parts.clear();
    }

    @Test
    public void calculateTotalLength_ShouldReturnCorrect() {
        int expected = 55;
        int actual = CalculationUtility.totalLength(this.parts);
        assertEquals(expected, actual);
    }

    @Test
    public void calculateMinimumLength_ShouldReturnCorrect() {
        int expected = 33;
        int actual = CalculationUtility.minimumLength(this.parts);
        assertEquals(expected, actual);
    }
}