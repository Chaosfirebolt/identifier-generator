package com.github.chaosfirebolt.generator.part;

import com.github.chaosfirebolt.generator.token.part.SpecialCharacterTokenPart;

import java.util.Arrays;
import java.util.List;

/**
 * Created by ChaosFire on 19-Dec-21
 */
public class SpecialCharacterTokenPartMethodTests extends TokenPartMethodTests {

    private static final int LENGTH = 5;
    private static final int MIN_LENGTH = 4;
    private static final List<Character> CHARACTERS = Arrays.asList('!', '"', '#', '$', '%', '&', '\'', '(', ')', '*', '+', ',', '-', '.',
        '/', ':', ';', '<', '=', '>', '?', '@', '[', '\\', ']', '^', '_', '`', '{', '|', '}', '~');
    private static final SpecialCharacterTokenPart PART = new SpecialCharacterTokenPart(LENGTH, MIN_LENGTH);

    public SpecialCharacterTokenPartMethodTests() {
        super(LENGTH, MIN_LENGTH, CHARACTERS, PART);
    }
}