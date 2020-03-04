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

    private Map<String, String> extraAttributes;


    public Animal(){}
    public Animal(int id, String name, String favoriteFood, int bestFriendForever) {
        this.id = id;
        this.name = name;
        this.favoriteFood = favoriteFood;
        this.bestFriendForever = bestFriendForever;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFavoriteFood() {
        return favoriteFood;
    }

    public void setFavoriteFood(String favoriteFood) {
        this.favoriteFood = favoriteFood;
    }

    public int getBestFriendForever() {
        return bestFriendForever;
    }

    public void setBestFriendForever(int bestFriendForever) {
        this.bestFriendForever = bestFriendForever;
    }

    public Map<String, String> getExtraAttributes() {
        return extraAttributes;
    }

    public void setExtraAttributes(Map<String, String> extraAttributes) {
        this.extraAttributes = extraAttributes;
    }
}
