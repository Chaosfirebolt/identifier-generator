package com.github.chaosfirebolt.generator.part;

import com.github.chaosfirebolt.generator.token.part.NumericTokenPart;

import java.util.Arrays;
import java.util.List;

/**
 * Created by ChaosFire on 19-Dec-21
 */
public class NumericTokenPartMethodTests extends TokenPartMethodTests {

    private static final int LENGTH = 7;
    private static final int MIN_LENGTH = 3;
    private static final List<Character> CHARACTERS = Arrays.asList('0', '1', '2', '3', '4', '5', '6', '7', '8', '9');
    private static final NumericTokenPart PART = new NumericTokenPart(LENGTH, MIN_LENGTH);

    public NumericTokenPartMethodTests() {
        super(LENGTH, MIN_LENGTH, CHARACTERS, PART);
    }
}