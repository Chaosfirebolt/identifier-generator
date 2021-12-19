package com.github.chaosfirebolt.generator.util;

import com.github.chaosfirebolt.generator.token.part.LowerAlphabeticTokenPart;
import com.github.chaosfirebolt.generator.token.part.NumericTokenPart;
import com.github.chaosfirebolt.generator.token.part.TokenPart;
import com.github.chaosfirebolt.generator.token.part.UpperAlphabeticTokenPart;
import com.github.chaosfirebolt.generator.token.util.CalculationUtility;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by ChaosFire on 19-Dec-21
 */
public class CalculationUtilityTests {

    private final List<TokenPart> parts = new ArrayList<>();

    @BeforeEach
    void setUp() {
        this.parts.add(new LowerAlphabeticTokenPart(5, 3));
        this.parts.add(new UpperAlphabeticTokenPart(15, 10));
        this.parts.add(new NumericTokenPart(35, 20));
    }

    @AfterEach
    void tearDown() {
        this.parts.clear();
    }

    @Test
    public void calculateTotalLength_ShouldReturnCorrect() {
        int expected = 55;
        int actual = CalculationUtility.totalLength(this.parts);
        assertEquals(expected, actual);
    }

    @Test
    public void calculateMinimumLength_ShouldReturnCorrect() {
        int expected = 33;
        int actual = CalculationUtility.minimumLength(this.parts);
        assertEquals(expected, actual);
    }
}