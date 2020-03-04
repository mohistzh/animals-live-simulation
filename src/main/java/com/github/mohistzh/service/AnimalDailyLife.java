package com.github.mohistzh.service;

import com.github.mohistzh.builder.AnimalMappingBuilder;
import com.github.mohistzh.config.RestrictionConstants;
import com.github.mohistzh.model.Animal;
import com.github.mohistzh.model.FriendshipGraph;
import com.github.mohistzh.service.internal.AnimalDailyLifeActionImpl;
import com.github.mohistzh.service.internal.IAnimalDailyLifeAction;
import com.github.mohistzh.util.RandomUtils;
import javafx.util.Pair;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @Author Jonathan
 * @Date 2020/3/4
 **/
public class AnimalDailyLife {

    //private IAnimalDailyLifeAction animalDailyLifeAction;
    private FriendshipGraph friendshipGraph;
    // best friend forever mapping
    private static Map<Integer, Integer> bestFriendForeverMap;

    // animal id to animal object
    private static Map<Integer, Animal> animalOfMap;

    private static Map<Pair<Integer, Integer>, SocialActivity> socialActivityMap;
    private List<Animal> animalList;


    public AnimalDailyLife(List<Animal> animalList) {
        this.animalList = animalList;
        AnimalMappingBuilder.builder(animalList);
        friendshipGraph = AnimalMappingBuilder.getFriendshipGraph();
        bestFriendForeverMap = AnimalMappingBuilder.getBestFriendForeverMap();
        animalOfMap = AnimalMappingBuilder.getAnimalOfMap();
        socialActivityMap = new HashMap<>();
        //animalDailyLifeAction = new AnimalDailyLifeActionImpl();

    }

    public void beforeLunchBreakUpFriends() {
        System.out.println("-------Before lunch-------");
        Random rnd = new Random();
        for (int i = 0; i < animalList.size(); i++) {
            Animal animal = animalList.get(i);
            // should be ignore friend forever
            int friendForever = bestFriendForeverMap.get(animal.getId());
            int breakupId = RandomUtils.randomNumber(rnd, 1, animalList.size(), new int[]{animal.getId(),
                    friendForever});
            SocialActivity socialActivity = socialActivityMap.getOrDefault(SocialActivity.of(animal.getId(), breakupId), new SocialActivity());
            socialActivity.increseRejectedCount();
            System.out.println(animal.getName() + " tries to break up with " + animalOfMap.get(breakupId).getName());
            if (friendshipGraph.getFriends(animal.getId()).contains(breakupId)) {
                if (makeDecision(breakupId, RestrictionConstants.BREAKEUP_FRIENDS)) {
                    friendshipGraph.removeFriendship(animal.getId(), breakupId);
                    socialActivity.setFriends(false);
                    System.out.println("--- " + animal.getName() + " and " + animalOfMap.get(breakupId).getName() + " are not friends anymore.");
                } else {
                    socialActivity.setFriends(true);
                    System.out.println("--- " + animalOfMap.get(breakupId).getName() + " rejected break up with " + animal.getName());
                }

            } else {
                socialActivity.setStranger(true);
                System.out.println("--- " + animal.getName() + " and " + animalOfMap.get(breakupId).getName() + " did't have a friendship, they should make a friend first.");
            }
            socialActivityMap.putIfAbsent(SocialActivity.of(animal.getId(), breakupId), socialActivity);

        }
    }

    public void lunchTime() {
        Map<String, List<Animal>> animalPerFood = this.animalList.stream()
                .collect(Collectors.groupingBy(Animal::getFavoriteFood));
        for (String food : animalPerFood.keySet()) {
            List<Animal> animals = animalPerFood.get(food);
            if (animals.size() > 1) {
                StringBuffer stringBuffer = new StringBuffer();
                for (int i = 0; i < animals.size(); i++) {
                    if (i == animals.size() - 1) {
                        stringBuffer.deleteCharAt(stringBuffer.lastIndexOf(", "));
                        stringBuffer.append("and ").append(animals.get(i).getName());
                    } else {
                        stringBuffer.append(animals.get(i).getName()).append(", ");
                    }
                }
                System.out.println(stringBuffer.toString() + " are eating their favourite food " + food);

            } else {
                System.out.println(animals.get(0).getName() + " is eating "+ food + " alone.");
            }
        }
    }
    private boolean makeDecision(int animalId, int branch) {
        boolean decision = false;
        switch (branch) {
            case RestrictionConstants.BREAKEUP_FRIENDS:
                if (friendshipGraph.getFriends(animalId).size() >= RestrictionConstants.SOCIAL_TALENT_DEFINE) {
                    decision = RandomUtils.randomBooleanGreaterThan(RestrictionConstants.SOCIAL_TALENT_LOST_FRIEND_RATIO);
                } else {
                    decision = RandomUtils.randomBooleanLessThan(RestrictionConstants.SOCIAL_WEAKER_LOST_FRIEND_RATIO);
                }
             case RestrictionConstants.MAKE_FRIENDS:
                 if (friendshipGraph.getFriends(animalId).size() >= RestrictionConstants.SOCIAL_TALENT_DEFINE) {
                     decision = RandomUtils.randomBooleanLessThan(RestrictionConstants.SOCIAL_TALENT_GAIN_FRIEND_RATIO);
                 } else {
                     decision = RandomUtils.randomBooleanGreaterThan(RestrictionConstants.SOCIAL_WEAKER_GAIN_FRIEND_RATIO);
                 }
            default:

        }
        return decision;
    }

    public void afterLunchMakeFriends() {
        System.out.println("-------After lunch-------");
        Random rnd = new Random();
        for (int i = 0; i < animalList.size(); i++) {
            Animal animal = animalList.get(i);
            // should be ignore friend forever
            int friendForever = bestFriendForeverMap.get(animal.getId());
            int friendId = RandomUtils.randomNumber(rnd, 1, animalList.size(), new int[]{animal.getId(),
                    friendForever});
            SocialActivity socialActivity = socialActivityMap.getOrDefault(SocialActivity.of(animal.getId(), friendId), new SocialActivity());
            socialActivity.increaseAskedCount();
            System.out.println(animal.getName() + " tries to make a friend with " + animalOfMap.get(friendId).getName());
            if (!friendshipGraph.getFriends(animal.getId()).contains(friendId)) {
                if (makeDecision(friendId, RestrictionConstants.MAKE_FRIENDS)) {
                    friendshipGraph.addFriendship(animal.getId(), friendId);
                    socialActivity.setFriends(true);
                    socialActivity.increaseBecomeFriendsCount();
                    System.out.println("--- " + animal.getName() + " and " + animalOfMap.get(friendId).getName() + " become friends.");
                } else {
                    socialActivity.setFriends(false);
                    System.out.println("--- " + animalOfMap.get(friendId).getName() + " rejected make friends with " + animal.getName());
                }

            } else {
                System.out.println("--- " + animal.getName() + " and " + animalOfMap.get(friendId).getName() + " were friends.");
            }
            socialActivityMap.putIfAbsent(SocialActivity.of(animal.getId(), friendId), socialActivity);

        }

    }

    public String relationshipToString() {

        return null;

    }
    public void printActivities() {
        socialActivityMap.forEach((k, v) -> {
            System.out.println(k.getKey() + " & " + k.getValue());
            System.out.println(v.toString());
        });
    }
}
