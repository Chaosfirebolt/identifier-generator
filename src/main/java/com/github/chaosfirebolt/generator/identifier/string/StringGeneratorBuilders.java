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

package com.github.chaosfirebolt.generator.identifier.string;

/**
 * Factory class for builders of string based identifier generators.
 */
public final class StringGeneratorBuilders {

    private StringGeneratorBuilders() {
        throw new RuntimeException("No instances allowed");
    }

    /**
     * Gets a new {@link StringIdentifierGeneratorBuilder}.
     * @return a new builder
     */
    public static StringIdentifierGeneratorBuilder stringIdentifierGeneratorBuilder() {
        return new StringIdentifierGeneratorBuilder();
    }

    /**
     * Gets a new builder to set up a generator for alphabetic identifier - 'ikuhYTgbH', 'GhjkYUhnbfJt', etc.
     * @return builder for alphabetic identifier generator
     */
    public static AlphabeticIdentifierGeneratorBuilder alphabeticIdentifierGeneratorBuilder() {
        return new AlphabeticIdentifierGeneratorBuilder();
    }
}
