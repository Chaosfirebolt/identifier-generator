package com.github.chaosfirebolt.generator.token.part;

import java.util.List;

/**
 * Represents a part of this token.
 * <br/>
 * Created by ChaosFire on 12/5/2021
 */
public interface TokenPart {

    /**
     * Gets the length of this token part
     * @return the length of this part
     */
    int getLength();

    /**
     * Get the minimum length for this token part
     * @return the minimum length of this part
     */
    default int getMinLength() {
        return this.getLength();
    }

    /**
     * Get possible characters for this token part.
     * @return possible characters for this part
     */
    List<Character> getCharacters();
}