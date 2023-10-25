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

package com.github.chaosfirebolt.generator.identifier;

public class IdentifierArguments {

    private final BaseIdentifierGenerator<String> generator;
    private final int maxAttempts;
    private final int expectedLength;
    private final int expectedMinLength;

    public IdentifierArguments(BaseIdentifierGenerator<String> generator, int maxAttempts, int expectedLength, int expectedMinLength) {
        this.generator = generator;
        this.maxAttempts = maxAttempts;
        this.expectedLength = expectedLength;
        this.expectedMinLength = expectedMinLength;
    }

    public static IdentifierArguments of(BaseIdentifierGenerator<String> generator, int maxAttempts, int expectedLength, int expectedMinLength) {
        return new IdentifierArguments(generator, maxAttempts, expectedLength, expectedMinLength);
    }

    public BaseIdentifierGenerator<String> getGenerator() {
        return this.generator;
    }

    public int getMaxAttempts() {
        return this.maxAttempts;
    }

    public int getExpectedLength() {
        return this.expectedLength;
    }

    public int getExpectedMinLength() {
        return this.expectedMinLength;
    }
}
