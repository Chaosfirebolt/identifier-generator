package com.github.chaosfirebolt.generator.token.part;

import com.github.chaosfirebolt.generator.token.util.CharacterUtility;

import java.util.ArrayList;
import java.util.List;

/**
 * Token part for special characters.
 * <br/>
 * Created by ChaosFire on 12/5/2021
 */
public class SpecialCharacterTokenPart extends BaseTokenPart {

    private static final List<Character> CHARACTERS;

    static {
        List<Character> firstRange = CharacterUtility.characterListFromIntRange(33, 48);
        List<Character> secondRange = CharacterUtility.characterListFromIntRange(58, 65);
        List<Character> thirdRange = CharacterUtility.characterListFromIntRange(91, 97);
        List<Character> fourthRange = CharacterUtility.characterListFromIntRange(123, 127);
        List<Character> specialCharacters = new ArrayList<>(firstRange.size() + secondRange.size() + thirdRange.size() + fourthRange.size());
        specialCharacters.addAll(firstRange);
        specialCharacters.addAll(secondRange);
        specialCharacters.addAll(thirdRange);
        specialCharacters.addAll(fourthRange);
        CHARACTERS = specialCharacters;
    }

    /**
     * Constructor creating instance of token part for special chars, with desired length.
     * <br/>
     * Minimum length is equal to the specified length.
     * @param length required length of the part
     */
    public SpecialCharacterTokenPart(int length) {
        this(length, length);
    }

    /**
     * Constructor creating instance of token part for special chars, with desired length and minimum length
     * @param length required length of the part
     * @param minLength required minimum length of the part
     */
    public SpecialCharacterTokenPart(int length, int minLength) {
        super(length, minLength, CHARACTERS);
    }
}