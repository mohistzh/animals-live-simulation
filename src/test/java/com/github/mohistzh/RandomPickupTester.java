package com.github.mohistzh;

import com.github.mohistzh.builder.AnimalMappingBuilder;
import com.github.mohistzh.builder.DataLoader;
import com.github.mohistzh.model.Animal;
import com.github.mohistzh.util.RandomUtils;
import org.junit.Before;
import org.junit.Test;

import java.util.*;
import java.util.stream.IntStream;

/**
 * @Author Jonathan
 * @Date 2020/3/3
 **/
public class RandomPickupTester {

    List<Animal> initialData = null;

    @Before
    public void init() {
        DataLoader dataLoader = new DataLoader();
        initialData = dataLoader.getAnimalList();
        AnimalMappingBuilder.builder(initialData);

    }
    @Test
    public void testAnimalBuilder() {
        Map<Integer, Integer> bestFriendForeverMap = AnimalMappingBuilder.getBestFriendForeverMap();
        Map<Integer, Animal> animalOfMap = AnimalMappingBuilder.getAnimalOfMap();
//        bestFriendForeverMap.forEach((k, v) -> System.out.println(k + " -> " + v));
//        Map<String, Float> weightedGainFriendMap = AnimalMappingBuilder.getWeightedGainFriendsMap();
//        weightedGainFriendMap.forEach((k, v) -> System.out.println(k + " -> " + v));


        Random rnd = new Random();
        for (int i = 0; i < initialData.size(); i++) {
            Animal animal = initialData.get(i);
            // should be ignore friend forever
            int friendForever = bestFriendForeverMap.getOrDefault(animal.getId(), -1);
            if (friendForever > 0) {
                System.out.println("FriendForever: " + friendForever);
                int random = RandomUtils.randomGeneration(1, initialData.size() + 1, Arrays.asList(animal.getId(), friendForever));
                if (random > Integer.MIN_VALUE) {
                    System.out.println(random);
                } else {
                    System.out.println("sth wrong!");
                }
            }


        }
    }

    @Test
    public void testRandomBoolean() {
        IntStream.range(1, 20).forEach(i -> System.out.println(RandomUtils.randomBooleanGreaterThan(0.10f)));
        IntStream.range(1, 20).forEach(i -> System.out.println(RandomUtils.randomBooleanLessThan((0.90f))));
    }

}
