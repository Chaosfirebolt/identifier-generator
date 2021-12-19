package com.github.chaosfirebolt.generator.util;

import com.github.chaosfirebolt.generator.token.util.CharacterUtility;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by ChaosFire on 19-Dec-21
 */
public class CharacterUtilityTests {

    @Test
    public void getCharacterListFromRange_ShouldReturnCorrect() {
        List<Character> expected = Arrays.asList('A', 'B', 'C');
        List<Character> actual = CharacterUtility.characterListFromIntRange(65, 68);
        assertEquals(expected, actual);
    }
}