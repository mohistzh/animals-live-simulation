package com.github.mohistzh;

import com.github.mohistzh.builder.AnimalMappingBuilder;
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
        initialData = new ArrayList<>();
        Animal animal1 = new Animal(1, "Rex", "Royal Canin", 3);
        Animal animal2 = new Animal(2, "Max", "Purina ONE", 4);
        Animal animal3 = new Animal(3, "Tom", "Royal Canin", 1);
        Animal animal4 = new Animal(4, "Jay", "Purina ONE", 2);
        Animal animal5 = new Animal(5, "Zoe", "9Lives", 6);
        Animal animal6 = new Animal(6, "Ada", "Purina Friskies", 5);
        Animal animal7 = new Animal(7, "Meg", "Purina Layena", 8);
        Animal animal8 = new Animal(8, "Lis", "Manna Pro", 7);
        Animal animal9 = new Animal(9, "Emi", "Purina Layena", 10);
        Animal animal10 = new Animal(10, "Lua", "Manna Pro", 9);
        Animal animal11 = new Animal(11, "Bob", "Manna Pro", -1);
        Animal animal12 = new Animal(12, "Mac", "Lafeber Original", 13);
        Animal animal13 = new Animal(13, "Alf", "Kaytee Fiesta”", 12);
        initialData.add(animal1);
        initialData.add(animal2);
        initialData.add(animal3);
        initialData.add(animal4);
        initialData.add(animal5);
        initialData.add(animal6);
        initialData.add(animal7);
        initialData.add(animal8);
        initialData.add(animal9);
        initialData.add(animal10);
        initialData.add(animal11);
        initialData.add(animal12);
        initialData.add(animal13);
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
                int random = RandomUtils.randomNumber(rnd, 1, initialData.size() + 1, new int[]{animal.getId(), friendForever});
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
