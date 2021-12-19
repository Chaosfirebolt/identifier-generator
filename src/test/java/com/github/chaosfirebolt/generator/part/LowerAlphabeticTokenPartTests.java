package com.github.chaosfirebolt.generator.part;

import com.github.chaosfirebolt.generator.token.part.LowerAlphabeticTokenPart;

import java.util.Arrays;
import java.util.List;

/**
 * Created by ChaosFire on 19-Dec-21
 */
public class LowerAlphabeticTokenPartTests extends TokenPartMethodTests {

    private static final int LENGTH = 5;
    private static final int MIN_LENGTH = 4;
    private static final List<Character> CHARACTERS = Arrays.asList('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n',
            'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z');
    private static final LowerAlphabeticTokenPart PART = new LowerAlphabeticTokenPart(LENGTH, MIN_LENGTH);

    public LowerAlphabeticTokenPartTests() {
        super(LENGTH, MIN_LENGTH, CHARACTERS, PART);
    }
}