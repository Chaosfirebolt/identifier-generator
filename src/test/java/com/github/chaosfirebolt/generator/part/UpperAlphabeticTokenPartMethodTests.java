package com.github.chaosfirebolt.generator.part;

import com.github.chaosfirebolt.generator.token.part.UpperAlphabeticTokenPart;

import java.util.Arrays;
import java.util.List;

/**
 * Created by ChaosFire on 19-Dec-21
 */
public class UpperAlphabeticTokenPartMethodTests extends TokenPartMethodTests {

    private static final int LENGTH = 5;
    private static final int MIN_LENGTH = 4;
    private static final List<Character> CHARACTERS = Arrays.asList('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N',
            'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z');
    private static final UpperAlphabeticTokenPart PART = new UpperAlphabeticTokenPart(LENGTH, MIN_LENGTH);

    public UpperAlphabeticTokenPartMethodTests() {
        super(LENGTH, MIN_LENGTH, CHARACTERS, PART);
    }
}