package com.github.chaosfirebolt.generator.util;

import com.github.chaosfirebolt.generator.token.util.ShuffleUtility;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 * Created by ChaosFire on 19-Dec-21
 */
public class ShuffleUtilityTests {

    @Test
    public void afterShuffle_ArrayShouldBeDifferent() {
        char[] array = new char[]{'a', 'b', 'c', 'd', 'e', '5', '7', '1', '#', '$', '^'};
        char[] copy = new char[array.length];
        System.arraycopy(array, 0, copy, 0, array.length);

        ShuffleUtility.shuffleFisherYates(array);
        assertNotEquals(copy, array);
    }
}