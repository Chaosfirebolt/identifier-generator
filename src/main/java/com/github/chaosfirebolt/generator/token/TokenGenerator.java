package com.github.chaosfirebolt.generator.token;

import java.util.function.Predicate;

/**
 * This interface defines the basic functionality for generating tokens.
 * <br/>
 * Created by ChaosFire on 12/5/2021
 */
public interface TokenGenerator<T> {

    /**
     * Generates a token
     * @return the generated token
     */
    T generate();

    /**
     * Generates a token which is unique according to the provided condition
     * @param uniquenessCondition condition for uniqueness of the generated token
     * @return the generated unique token
     * @throws com.github.chaosfirebolt.generator.token.exception.TooManyAttemptsException if a maximum number of attempts to generate unique token has been set, and that number is reached
     */
    T generate(Predicate<T> uniquenessCondition);

    /**
     * Generates a token with the specified length
     * @param tokenLength required length of the generated token
     * @return the generated token
     */
    T generate(int tokenLength);

    /**
     * Generates a token with the specified length, which is unique according to the provided condition
     * @param tokenLength required length of the generated token
     * @param uniquenessCondition condition for uniqueness of the generated token
     * @return the generated unique token
     * @throws com.github.chaosfirebolt.generator.token.exception.TooManyAttemptsException if a maximum number of attempts to generate unique token has been set, and that number is reached
     */
    T generate(int tokenLength, Predicate<T> uniquenessCondition);
}