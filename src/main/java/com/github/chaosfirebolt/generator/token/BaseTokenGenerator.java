package com.github.chaosfirebolt.generator.token;

import java.util.function.Predicate;

/**
 * Provides basic and somewhat naive functionality for generating unique tokens.
 * <br/>
 * New tokens will be generated until the condition proves one to be unique.
 * <br/>
 * Created by ChaosFire on 12/5/2021
 */
public abstract class BaseTokenGenerator<T> implements TokenGenerator<T> {

    @Override
    public T generate(Predicate<T> uniquenessCondition) {
        T token = this.generate();
        while (!uniquenessCondition.test(token)) {
            token = this.generate();
        }
        return token;
    }

    @Override
    public T generate(int tokenLength, Predicate<T> uniquenessCondition) {
        T token = this.generate(tokenLength);
        while (!uniquenessCondition.test(token)) {
            token = this.generate(tokenLength);
        }
        return token;
    }
}