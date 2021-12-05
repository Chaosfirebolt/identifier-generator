package com.github.chaosfirebolt.generator.token.part;

import com.github.chaosfirebolt.generator.token.util.CharacterUtility;

import java.util.List;

/**
 * Token part for numeric characters.
 * <br/>
 * Created by ChaosFire on 12/5/2021
 */
public class NumericTokenPart extends BaseTokenPart {

    private static final List<Character> CHARACTERS = CharacterUtility.characterListFromIntRange(48, 58);

    /**
     * Constructor creating instance of token part for numeric chars, with desired length
     * @param length required length of the part
     */
    public NumericTokenPart(int length) {
        super(length, CHARACTERS);
    }
}