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

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Created by ChaosFire on 19-Dec-21
 */
public abstract class PartConstructorTests {

    @Test
    public void intConstructor_LengthIsNegative_ShouldThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, this.intConstructor_LengthIsNegative());
    }

    protected abstract Executable intConstructor_LengthIsNegative();

    @Test
    public void intConstructor_LengthIsZero_ShouldThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, this.intConstructor_LengthIsZero());
    }

    protected abstract Executable intConstructor_LengthIsZero();

    @Test
    public void intConstructor_LengthIsPositive_ShouldNotThrowException() throws Throwable {
        this.intConstructor_LengthIsPositive().execute();
    }

    protected abstract Executable intConstructor_LengthIsPositive();

    @Test
    public void intIntConstructor_MinLengthIsNegative_ShouldThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, this.intIntConstructor_MinLengthIsNegative());
    }

    protected abstract Executable intIntConstructor_MinLengthIsNegative();

    @Test
    public void intIntConstructor_MinLengthIsZero_ShouldThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, this.intIntConstructor_MinLengthIsZero());
    }

    protected abstract Executable intIntConstructor_MinLengthIsZero();

    @Test
    public void intIntConstructor_MinLengthIsPositive_ShouldNotThrow() throws Throwable {
        this.intIntConstructor_MinLengthIsPositive().execute();
    }

    protected abstract Executable intIntConstructor_MinLengthIsPositive();

    @Test
    public void intIntConstructor_LengthIsLessThanMinLength_ShouldThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, this.intIntConstructor_LengthIsLessThanMinLength());
    }

    protected abstract Executable intIntConstructor_LengthIsLessThanMinLength();
}