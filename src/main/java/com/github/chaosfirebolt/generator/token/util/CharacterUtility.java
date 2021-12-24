package com.github.chaosfirebolt.generator.token.util;

import java.util.ArrayList;
import java.util.List;

/**
 * Utility methods for characters
 * <br>
 * Created by ChaosFire on 12/5/2021
 */
public class CharacterUtility {

    /**
     * Creates a list of characters from supplied range of integers cast to char
     * @param start range start, inclusive
     * @param end range end, exclusive
     * @return list of chars
     */
    public static List<Character> characterListFromIntRange(int start, int end) {
        List<Character> result = new ArrayList<>(end - start);
        for (int i = start; i < end; i++) {
            result.add((char) i);
        }
        return result;
    }
}