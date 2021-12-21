package com.github.chaosfirebolt.generator.token.exception;

/**
 * Created by ChaosFire on 21-Dec-21
 */
public class TooManyAttemptsException extends RuntimeException {

    public TooManyAttemptsException(String message) {
        super(message);
    }
}