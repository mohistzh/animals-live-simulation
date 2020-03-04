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
     * Generate a random boolean value by given percent.
     * Percent more higher, then easy to return true probably. vice vera.
     * @param percent
     * @return
     */
    public static boolean generateRandomBoolean(float percent) {
        Random rnd = new Random();
        return rnd.nextFloat() <= percent;
    }
}
