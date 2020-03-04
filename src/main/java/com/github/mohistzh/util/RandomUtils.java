package com.github.mohistzh.util;

import java.util.Arrays;
import java.util.Random;

/**
 * An utility class for random data generation
 * @Author Jonathan
 * @Date 2020/3/4
 **/
public class RandomUtils {


    /**
     * Generate a random number with exclusion numbers
     * @param rnd
     * @param start
     * @param end
     * @param exclusion
     * @return
     */
    public static int randomNumber(Random rnd, int start, int end, final int ... exclusion) {
        int result = 0;
        for (int i = 0; i < 3; i++) {
            final int random = rnd.nextInt(end)%(end-start+1) + start;
            boolean isExists = Arrays.stream(exclusion).anyMatch(ex -> ex == random);
            if (!isExists) {
                result = random;
            }
        }
        return result;
    }

    /**
     * Generate random boolean value greater than percent of X ratio
     * @param ratio
     * @return
     */
    public static boolean randomBooleanGreaterThan(float ratio) {
        return Math.random() < ratio;
    }

    /**
     * Generate random boolean value less than percent of x ratio
     * @param ratio
     * @return
     */
    public static boolean randomBooleanLessThan(float ratio) {
        return Math.random() > ratio;
    }
}
