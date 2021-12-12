package com.github.chaosfirebolt.generator.token.rule;

import com.github.chaosfirebolt.generator.token.part.TokenPart;

import java.util.List;

/**
 * Represents a rule for token generation.
 * <br/>
 * Created by ChaosFire on 12/5/2021
 */
public interface GeneratorRule {

    /**
     * Get token parts required by this rule
     * @return the token parts
     */
    List<TokenPart> getParts();

    /**
     * Get token length required by this rule
     * @return total length of the token
     */
    int getLength();

    /**
     * Get the minimum length required by this rule
     * @return the minimum length of the token
     */
    default int getMinLength() {
        return this.getLength();
    }
}