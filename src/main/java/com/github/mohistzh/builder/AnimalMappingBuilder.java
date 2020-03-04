package com.github.mohistzh.builder;

import com.github.mohistzh.config.RestrictionConstants;
import com.github.mohistzh.model.Animal;
import com.github.mohistzh.model.FriendshipGraph;
import com.github.mohistzh.service.SocialActivity;
import javafx.util.Pair;

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
    // unfriend relationship excluded
    private static FriendshipGraph friendshipGraph;

    private static Map<Pair<Integer, Integer>, SocialActivity> socialActivityMap;

    public static void builder(List<Animal> animalList) {
        animalOfMap = new HashMap<>();
        bestFriendForeverMap = new HashMap<>();
        socialActivityMap = new HashMap<>();

        friendshipGraph = new FriendshipGraph(animalList.size() + 1);
        for (Animal animal : animalList) {
            animalOfMap.put(animal.getId(), animal);
            bestFriendForeverMap.put(animal.getId(), animal.getBestFriendForever());
            friendshipGraph.addFriendship(animal.getId(), animal.getBestFriendForever());
            if (animal.getBestFriendForever() > 0) {
                socialActivityMap.put(new Pair<Integer, Integer>(animal.getId(), animal.getBestFriendForever()), new SocialActivity(true));
            }
        }
    }
    public static Map<Integer, Animal> getAnimalOfMap() {
        return animalOfMap;
    }
    public static Map<Integer, Integer> getBestFriendForeverMap() {
        return bestFriendForeverMap;
    }
    public static FriendshipGraph getFriendshipGraph() { return friendshipGraph; }

    public static Map<Pair<Integer, Integer>, SocialActivity> getSocialActivityMap() {
        return socialActivityMap;
    }
}
