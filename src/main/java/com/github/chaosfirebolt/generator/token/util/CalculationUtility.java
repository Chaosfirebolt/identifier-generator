package com.github.chaosfirebolt.generator.token.util;

import com.github.chaosfirebolt.generator.token.part.TokenPart;

import java.util.List;

/**
 * Created by ChaosFire on 12/6/2021
 */
public class CalculationUtility {

    public static int totalLength(List<TokenPart> parts) {
        int sum = 0;
        for (TokenPart part : parts) {
            sum += part.getLength();
        }
        return sum;
    }
}