package com.github.mohistzh.util;

import java.util.List;
import java.util.Random;

/**
 * An utility class for random data generation
 * @Author Jonathan
 * @Date 2020/3/4
 **/
public class RandomUtils {


    /**
     * Generate a random number excluded by given list
     * @param min
     * @param max
     * @param excludeNumbers
     * @return
     */
    public static int randomGeneration(int min, int max, List<Integer> excludeNumbers) {
        Random rand = new Random();
        int range = max - min + 1;
        int random = rand.nextInt(range)+1;
        while (excludeNumbers.contains(random)) {
            random = rand.nextInt(range) + 1;
        }
        return random;
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
