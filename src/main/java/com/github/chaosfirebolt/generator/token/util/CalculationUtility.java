package com.github.chaosfirebolt.generator.token.util;

import com.github.chaosfirebolt.generator.token.part.TokenPart;

import java.util.List;

/**
 * Calculation utility methods.
 * <br/>
 * Created by ChaosFire on 12/6/2021
 */
public class CalculationUtility {

    /**
     * Calculates the total length of all parts.
     * @param parts parts whose total length is to be calculated
     * @return the total length
     */
    public static int totalLength(List<TokenPart> parts) {
        int sum = 0;
        for (TokenPart part : parts) {
            sum += part.getLength();
        }
        return sum;
    }

    /**
     * Calculates the minimum length of all parts.
     * @param parts parts whose total minimum length is to be calculated
     * @return the total minimum length
     */
    public static int minimumLength(List<TokenPart> parts) {
        int sum = 0;
        for (TokenPart part : parts) {
            sum += part.getMinLength();
        }
        return sum;
    }
}