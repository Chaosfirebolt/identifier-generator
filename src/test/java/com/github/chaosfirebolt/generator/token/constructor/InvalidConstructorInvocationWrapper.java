package com.github.chaosfirebolt.generator.token.constructor;

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