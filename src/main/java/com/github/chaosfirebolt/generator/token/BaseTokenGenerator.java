package com.github.chaosfirebolt.generator.token;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Predicate;

/**
 * Provides basic and somewhat naive functionality for generating unique tokens.
 * <br/>
 * New tokens will be generated until the condition proves one to be unique.
 * <br/>
 * Created by ChaosFire on 12/5/2021
 */
public abstract class BaseTokenGenerator<T> implements TokenGenerator<T> {

    private final Logger logger;

    protected BaseTokenGenerator() {
        this.logger = LoggerFactory.getLogger(this.getClass());
    }

    @Override
    public T generate(Predicate<T> uniquenessCondition) {
        T token = this.generate();
        int count = 1;
        while (!uniquenessCondition.test(token)) {
            token = this.regenerateToken(token);
            count++;
        }
        this.logger.trace("Unique token generated after {} attempts", count);
        return token;
    }

    /**
     * Generates a new token, if the previously generated one is not unique.
     * <br/>
     * This implementation will just create a new one from scratch, without taking into account the previous token.
     * <br/>
     * Override this method for better/faster regeneration.
     * @param previousToken previously generated and not unique token
     * @return newly generated token
     */
    @SuppressWarnings("unused")
    protected T regenerateToken(T previousToken) {
        return this.generate();
    }

    @Override
    public T generate(int tokenLength, Predicate<T> uniquenessCondition) {
        T token = this.generate(tokenLength);
        int count = 1;
        while (!uniquenessCondition.test(token)) {
            token = this.regenerateToken(token, tokenLength);
            count++;
        }
        this.logger.trace("Unique token generated after {} attempts", count);
        return token;
    }

    /**
     * Generates a new token, if the previously generated one is not unique.
     * <br/>
     * This implementation will just create a new one from scratch, without taking into account the previous token.
     * <br/>
     * Override this method for better/faster regeneration.
     * @param previousToken previously generated and not unique token
     * @param tokenLength required length of the token
     * @return newly generated token
     */
    @SuppressWarnings("unused")
    protected T regenerateToken(T previousToken, int tokenLength) {
        return this.generate(tokenLength);
    }
}