package com.github.chaosfirebolt.generator.token.util;

import java.security.SecureRandom;
import java.util.Random;

/**
 * Shuffle utility methods
 * <br/>
 * Created by ChaosFire on 12-Dec-21
 */
public class ShuffleUtility {

    /**
     * Shuffles the array using Fisher-Yates algorithm.
     * @param random random to generate random indexes
     * @param array array to be shuffled
     */
    public static void shuffleFisherYates(Random random, char[] array) {
        for (int i = array.length - 1; i > 0; i--) {
            int randomIndex = random.nextInt(i + 1);
            char temp = array[i];
            array[i] = array[randomIndex];
            array[randomIndex] = temp;
        }
    }

    /**
     * Shuffles the array using Fisher-Yates algorithm.
     * @param array array to be shuffled
     */
    public static void shuffleFisherYates(char[] array) {
        shuffleFisherYates(new SecureRandom(), array);
    }
}