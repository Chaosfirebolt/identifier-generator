package com.github.chaosfirebolt.generator.token.part;

import com.github.chaosfirebolt.generator.token.util.CharacterUtility;

import java.util.List;

/**
 * Token part for upper alphabetic characters.
 * <br/>
 * Created by ChaosFire on 12/5/2021
 */
public class UpperAlphabeticTokenPart extends BaseTokenPart {

    private static final List<Character> CHARACTERS = CharacterUtility.characterListFromIntRange(65, 91);

    /**
     * Constructor creating instance of token part for upper alphabetic chars, with desired length
     * @param length required length of the part
     */
    public UpperAlphabeticTokenPart(int length) {
        super(length, CHARACTERS);
    }
}