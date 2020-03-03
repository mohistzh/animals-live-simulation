package com.github.mohistzh.builder;

import com.github.mohistzh.config.RestrictionConstants;
import com.github.mohistzh.model.Animal;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author Jonathan
 * @Date 2020/3/3
 **/
public class AnimalMappingBuilder {
    private static Map<Integer, Animal> animalOfMap;
    private static Map<Integer, Integer> bestFriendForeverMap;
    private static Map<String, Float> weightedLoseFriendsMap;
    private static Map<String, Float> weightedGainFriendsMap;

    public static void builder(List<Animal> animalList) {
        animalOfMap = new HashMap<>();
        bestFriendForeverMap = new HashMap<>();
        weightedGainFriendsMap = new HashMap<>();
        weightedLoseFriendsMap = new HashMap<>();

        for (Animal animal : animalList) {
            animalOfMap.put(animal.getId(), animal);
            bestFriendForeverMap.put(animal.getId(), animal.getBestFriendForever());
            weightedLoseFriendsMap.put(animal.getName(), RestrictionConstants.SOCIAL_WEAKER_LOST_FRIEND_RATIO);
            weightedGainFriendsMap.put(animal.getName(), RestrictionConstants.SOCIAL_WEAKER_GAIN_FRIEND_RATIO);
        }
    }
    public static Map<Integer, Animal> getAnimalOfMap() {
        return animalOfMap;
    }
    public static Map<Integer, Integer> getBestFriendForeverMap() {
        return bestFriendForeverMap;
    }
    public static Map<String, Float> getWeightedLoseFriendsMap() {
        return weightedLoseFriendsMap;
    }
    public static Map<String, Float> getWeightedGainFriendsMap() {
        return weightedGainFriendsMap;
    }
}
