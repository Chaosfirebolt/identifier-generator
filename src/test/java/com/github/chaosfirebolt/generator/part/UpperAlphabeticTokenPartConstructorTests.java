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

import com.github.chaosfirebolt.generator.token.part.UpperAlphabeticTokenPart;
import org.junit.jupiter.api.function.Executable;

/**
 * Created by ChaosFire on 19-Dec-21
 */
public class UpperAlphabeticTokenPartConstructorTests extends TokenPartConstructorTests {

    @Override
    protected Executable intConstructor_LengthIsNegative() {
        return () -> new UpperAlphabeticTokenPart(-4);
    }

    @Override
    protected Executable intConstructor_LengthIsZero() {
        return () -> new UpperAlphabeticTokenPart(0);
    }

    @Override
    protected Executable intConstructor_LengthIsPositive() {
        return () -> new UpperAlphabeticTokenPart(4);
    }

    @Override
    protected Executable intIntConstructor_MinLengthIsNegative() {
        return () -> new UpperAlphabeticTokenPart(4, -9);
    }

    @Override
    protected Executable intIntConstructor_MinLengthIsZero() {
        return () -> new UpperAlphabeticTokenPart(4, 0);
    }

    @Override
    protected Executable intIntConstructor_MinLengthIsPositive() {
        return () -> new UpperAlphabeticTokenPart(4, 2);
    }

    @Override
    protected Executable intIntConstructor_LengthIsLessThanMinLength() {
        return () -> new UpperAlphabeticTokenPart(8, 11);
    }
}