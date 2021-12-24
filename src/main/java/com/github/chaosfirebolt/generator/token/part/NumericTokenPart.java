package com.github.chaosfirebolt.generator.token.part;

import com.github.chaosfirebolt.generator.token.util.CharacterUtility;

import java.util.List;

/**
 * Token part for numeric characters.
 * <br>
 * Created by ChaosFire on 12/5/2021
 */
public class NumericTokenPart extends BaseTokenPart {

    private static final List<Character> CHARACTERS = CharacterUtility.characterListFromIntRange(48, 58);

    /**
     * Constructor creating instance of token part for numeric chars, with desired length.
     * <br>
     * Minimum length is equal to the specified length.
     * @param length required length of the part
     * @throws IllegalArgumentException if length is less than 1
     */
    public NumericTokenPart(int length) {
        this(length, length);
    }

    /**
     * Constructor creating instance of token part for numeric chars, with desired length and minimum length
     * @param length required length of the part
     * @param minLength required minimum length of the part
     * @throws IllegalArgumentException if length or minLength are less than 1 or length is less than minLength
     */
    public NumericTokenPart(int length, int minLength) {
        super(length, minLength, CHARACTERS);
    }
}