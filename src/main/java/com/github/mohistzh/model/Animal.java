package com.github.mohistzh.model;

import java.util.Map;

/**
 * Data model of animal, in our story, only names,
 * favorite food, and best friend forever need to be visible.
 * @Author Jonathan
 * @Date 2020/3/3
 **/
public class Animal {
    private String name;
    private String favoriteFood;
    private String bestFriendForever;

    private Map<String, String> extraAttributes;

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

    public String getBestFriendForever() {
        return bestFriendForever;
    }

    public void setBestFriendForever(String bestFriendForever) {
        this.bestFriendForever = bestFriendForever;
    }

    public Map<String, String> getExtraAttributes() {
        return extraAttributes;
    }

    public void setExtraAttributes(Map<String, String> extraAttributes) {
        this.extraAttributes = extraAttributes;
    }
}
