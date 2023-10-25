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

package com.github.chaosfirebolt.generator.part;

import com.github.chaosfirebolt.generator.identifier.part.Part;

import java.util.List;

public class PartArguments {

    private final Part part;
    private final List<Character> expectedCharacters;
    private final int expectedLength;
    private final int expectedMinLength;

    public PartArguments(Part part, List<Character> expectedCharacters, int expectedLength, int expectedMinLength) {
        this.part = part;
        this.expectedCharacters = expectedCharacters;
        this.expectedLength = expectedLength;
        this.expectedMinLength = expectedMinLength;
    }

    public Part getPart() {
        return this.part;
    }

    public List<Character> getExpectedCharacters() {
        return this.expectedCharacters;
    }

    public int getExpectedLength() {
        return this.expectedLength;
    }

    public int getExpectedMinLength() {
        return this.expectedMinLength;
    }
}
