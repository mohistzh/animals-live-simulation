package com.github.mohistzh.util;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

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
        int range = max - min + 1;
        int random = ThreadLocalRandom.current().nextInt(range)+1;
        while (excludeNumbers.contains(random)) {
            random = ThreadLocalRandom.current().nextInt(range) + 1;
        }
        return random;
    }

    /**
     * Generate a random number only included in the given array
     * @param includedNumbers
     * @return
     */
    public static int randomGeneration(List<Integer> includedNumbers) {
        int random, index;
        if (includedNumbers.size() > 1) {
            index = ThreadLocalRandom.current().nextInt(0, includedNumbers.size());
            random = includedNumbers.get(index);
        } else {
            random = includedNumbers.get(0);
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
        return ThreadLocalRandom.current().nextFloat() <= percent;
    }
}
