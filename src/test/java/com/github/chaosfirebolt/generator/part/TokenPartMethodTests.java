package com.github.chaosfirebolt.generator.part;

import com.github.chaosfirebolt.generator.token.part.TokenPart;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by ChaosFire on 19-Dec-21
 */
public abstract class TokenPartMethodTests {

    private final int expectedLength;
    private final int expectedMinLength;
    private final List<Character> expectedCharacters;
    private final TokenPart tokenPart;

    protected TokenPartMethodTests(int expectedLength, int expectedMinLength, List<Character> expectedCharacters, TokenPart tokenPart) {
        this.expectedLength = expectedLength;
        this.expectedMinLength = expectedMinLength;
        this.expectedCharacters = expectedCharacters;
        this.tokenPart = tokenPart;
    }

    @Test
    public void getLength_ResultShouldBeCorrect() {
        int actual = this.tokenPart.getLength();
        assertEquals(this.expectedLength, actual);
    }

    @Test
    public void getMinLength_ResultShouldBeCorrect() {
        int actual = this.tokenPart.getMinLength();
        assertEquals(this.expectedMinLength, actual);
    }

    @Test
    public void getCharacters_ResultShouldBeCorrect() {
        List<Character> actual = this.tokenPart.getCharacters();
        assertEquals(this.expectedCharacters, actual);
    }
}