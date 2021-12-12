package com.github.chaosfirebolt.generator.token.part;

import java.util.Collections;
import java.util.List;

/**
 * Basic implementation of {@link TokenPart}
 * <br/>
 * Created by ChaosFire on 12/5/2021
 */
public class BaseTokenPart implements TokenPart {

    /**
     * Desired length of this part.
     */
    private final int length;

    /**
     * Desired minimum length of this part.
     */
    private final int minLength;

    /**
     * Possible characters fot this part.
     */
    private final List<Character> characters;

    /**
     * Constructor creating token part from supplied length and characters.
     * <br/>
     * Collection of characters is changed to unmodifiable list.
     * @param length required length of the part
     * @param characters possible characters for the part
     */
    public BaseTokenPart(int length, List<Character> characters) {
        this(length, length, characters);
    }

    /**
     * Constructor creating token part from supplied length, minimum length and characters.
     * <br/>
     * Collection of characters is changed to unmodifiable list.
     * @param length required length of the part
     * @param minLength required minimum length of the part
     * @param characters possible characters for the part
     */
    public BaseTokenPart(int length, int minLength, List<Character> characters) {
        this.length = length;
        this.minLength = minLength;
        this.characters = Collections.unmodifiableList(characters);
    }

    @Override
    public int getLength() {
        return this.length;
    }

    @Override
    public int getMinLength() {
        return this.minLength;
    }

    @Override
    public List<Character> getCharacters() {
        return this.characters;
    }
}