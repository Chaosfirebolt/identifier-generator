package com.github.chaosfirebolt.generator.token.validation;

import com.github.chaosfirebolt.generator.token.rule.GeneratorRule;

/**
 * Represents generator for error messages
 * <br/>
 * Created by ChaosFire on 12/5/2021
 */
@FunctionalInterface
public interface ErrorMessageCreator {

    /**
     * Creates error message from the supplied {@link GeneratorRule}
     * @param rule rule to be used for message generation
     * @return created error message
     */
    String create(GeneratorRule rule);
}