package com.github.chaosfirebolt.generator.token.exception;

/**
 * This exception signals, that a {@link com.github.chaosfirebolt.generator.token.rule.GeneratorRule} is not valid
 * <br>
 * Created by ChaosFire on 12/5/2021
 */
public class InvalidGeneratorRuleException extends RuntimeException {

    /**
     * Constructs new InvalidGeneratorRuleException with the specified message
     * @param message the message
     */
    public InvalidGeneratorRuleException(String message) {
        super(message);
    }
}