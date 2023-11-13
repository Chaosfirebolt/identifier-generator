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

import org.apiguardian.api.API;

/**
 * Factory class for builders of string based identifier generators.
 */
@API(status = API.Status.STABLE)
public final class StringGeneratorBuilders {

    private StringGeneratorBuilders() {
        throw new RuntimeException("No instances allowed");
    }

    /**
     * Returns a new {@link StringIdentifierGeneratorBuilder}.
     * @return a new builder
     */
    public static StringIdentifierGeneratorBuilder stringIdentifierGeneratorBuilder() {
        return new StringIdentifierGeneratorBuilder();
    }

    /**
     * Returns a new builder to set up a generator for alphabetic identifier - 'ikuhYTgbH', 'GhjkYUhnbfJt', etc.
     * @return builder for alphabetic identifier generator
     */
    public static AlphabeticIdentifierGeneratorBuilder alphabeticIdentifierGeneratorBuilder() {
        return new AlphabeticIdentifierGeneratorBuilder();
    }

    /**
     * Returns a new builder to set up a generator for alphanumeric identifier - 'aas5F4eD', 'nj67r6Yh98', etc.
     * @return builder for alphanumeric identifier generator
     */
    public static AlphaNumericIdentifierGeneratorBuilder alphaNumericIdentifierGeneratorBuilder() {
        return new AlphaNumericIdentifierGeneratorBuilder();
    }

    /**
     * Returns a new builder to set up a generator for any character (alphanumeric + special symbol) identifier - 'asdf67gHn8IK$ed#lOf', etc.
     * @return builder for any character identifier generator
     */
    public static AnyCharacterIdentifierGeneratorBuilder anyCharacterIdentifierGeneratorBuilder() {
        return new AnyCharacterIdentifierGeneratorBuilder();
    }

    /**
     * Returns a new builder to set up a generator for lower case alphabetic identifier - 'jhdoimeg', etc.
     * @return builder for lower case alphabetic identifier generator
     */
    public static LowerAlphabeticIdentifierGeneratorBuilder lowerAlphabeticIdentifierGeneratorBuilder() {
        return new LowerAlphabeticIdentifierGeneratorBuilder();
    }
    
    /**
     * Returns a new builder to set up a generator for numeric identifier - '185236970', etc.
     * @return builder for numeric identifier generator
     */
    public static NumericIdentifierGeneratorBuilder numericIdentifierGeneratorBuilder() {
        return new NumericIdentifierGeneratorBuilder();
    }

    /**
     * Returns a new builder to set up a generator for upper case alphabetic identifier - 'YHAMOIESWNBS', etc.
     * @return builder for upper case alphabetic identifier generator
     */
    public static UpperAlphabeticIdentifierGeneratorBuilder upperAlphabeticIdentifierGeneratorBuilder() {
        return new UpperAlphabeticIdentifierGeneratorBuilder();
    }
}
