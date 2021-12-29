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

package com.github.chaosfirebolt.generator.identifier.constructor;

import org.junit.jupiter.api.function.Executable;

/**
 * Created by ChaosFire on 23-Dec-21
 */
class InvalidConstructorInvocationWrapper {

    private final Executable invalidInvocation;
    private final Class<? extends Throwable> expectedException;
    private final String expectedErrorMessage;

    InvalidConstructorInvocationWrapper(Executable invalidInvocation, Class<? extends Throwable> expectedException, String expectedErrorMessage) {
        this.invalidInvocation = invalidInvocation;
        this.expectedException = expectedException;
        this.expectedErrorMessage = expectedErrorMessage;
    }

    public Executable getInvalidInvocation() {
        return this.invalidInvocation;
    }

    public Class<? extends Throwable> getExpectedException() {
        return this.expectedException;
    }

    public String getExpectedErrorMessage() {
        return this.expectedErrorMessage;
    }
}