package com.github.mohistzh.repo.model;

import java.util.Map;

/**
 * Data model of animal, in our story, only names,
 * favorite food, and best friend forever need to be visible.
 * @Author Jonathan
 * @Date 2020/3/3
 **/
public class Animal {
    private int id;
    private String name;
    private String favoriteFood;
    private int bestFriendForever;

    public Animal(int id, String name, String favoriteFood, int bestFriendForever) {
        this.id = id;
        this.name = name;
        this.favoriteFood = favoriteFood;
        this.bestFriendForever = bestFriendForever;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getFavoriteFood() {
        return favoriteFood;
    }

    public int getBestFriendForever() {
        return bestFriendForever;
    }

    @Override
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Animal id: ").append(id).append("\n")
                .append("Animal name: ").append(name).append("\n")
                .append("Favorite food: ").append(favoriteFood).append("\n")
                .append("Best friend id: ").append(bestFriendForever).append("\n");
        return stringBuffer.toString();
    }
}
