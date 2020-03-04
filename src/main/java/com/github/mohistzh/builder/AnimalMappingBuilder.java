package com.github.mohistzh.builder;

import com.github.mohistzh.config.RestrictionConstants;
import com.github.mohistzh.model.Animal;
import com.github.mohistzh.model.FriendshipGraph;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author Jonathan
 * @Date 2020/3/3
 **/
public class AnimalMappingBuilder {
    // animal id to animal object
    private static Map<Integer, Animal> animalOfMap;
    // best friend forever mapping
    private static Map<Integer, Integer> bestFriendForeverMap;
    // 0 is gain, 1 is lose
    private static Map<Integer, float[]> weightedGainAndLoseFriendMap;
    // unfriend relationship excluded
    private static FriendshipGraph friendshipGraph;

    public static void builder(List<Animal> animalList) {
        animalOfMap = new HashMap<>();
        bestFriendForeverMap = new HashMap<>();
        weightedGainAndLoseFriendMap = new HashMap<>();
        friendshipGraph = new FriendshipGraph(animalList.size() + 1);
        for (Animal animal : animalList) {
            animalOfMap.put(animal.getId(), animal);
            bestFriendForeverMap.put(animal.getId(), animal.getBestFriendForever());
            // by default, animal's social data is weak
            weightedGainAndLoseFriendMap.putIfAbsent(animal.getId(),
                    new float[]{RestrictionConstants.SOCIAL_WEAKER_GAIN_FRIEND_RATIO,
                            RestrictionConstants.SOCIAL_WEAKER_LOST_FRIEND_RATIO});
            friendshipGraph.addFriendship(animal.getId(), animal.getBestFriendForever());
        }
    }
    public static Map<Integer, Animal> getAnimalOfMap() {
        return animalOfMap;
    }
    public static Map<Integer, Integer> getBestFriendForeverMap() {
        return bestFriendForeverMap;
    }
    public static Map<Integer, float[]> getWeightedGainAndLoseFriendMap() { return weightedGainAndLoseFriendMap; }
    public static FriendshipGraph getFriendshipGraph() { return friendshipGraph; }
}
