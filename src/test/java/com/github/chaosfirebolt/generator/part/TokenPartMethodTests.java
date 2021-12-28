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

import com.github.chaosfirebolt.generator.token.part.TokenPart;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by ChaosFire on 19-Dec-21
 */
public abstract class TokenPartMethodTests {

    private final int expectedLength;
    private final int expectedMinLength;
    private final List<Character> expectedCharacters;
    private final TokenPart tokenPart;

    protected TokenPartMethodTests(int expectedLength, int expectedMinLength, List<Character> expectedCharacters, TokenPart tokenPart) {
        this.expectedLength = expectedLength;
        this.expectedMinLength = expectedMinLength;
        this.expectedCharacters = expectedCharacters;
        this.tokenPart = tokenPart;
    }

    @Test
    public void getLength_ResultShouldBeCorrect() {
        int actual = this.tokenPart.getLength();
        assertEquals(this.expectedLength, actual);
    }

    @Test
    public void getMinLength_ResultShouldBeCorrect() {
        int actual = this.tokenPart.getMinLength();
        assertEquals(this.expectedMinLength, actual);
    }

    @Test
    public void getCharacters_ResultShouldBeCorrect() {
        List<Character> actual = this.tokenPart.getCharacters();
        assertEquals(this.expectedCharacters, actual);
    }
}