package com.github.mohistzh.service;

import com.github.mohistzh.builder.AnimalMappingBuilder;
import com.github.mohistzh.model.Animal;
import com.github.mohistzh.model.FriendshipGraph;
import com.github.mohistzh.service.internal.AnimalDailyLifeActionImpl;
import com.github.mohistzh.service.internal.AnimalDailyLifeStageImpl;
import com.github.mohistzh.service.internal.IAnimalDailyLifeAction;
import com.github.mohistzh.service.internal.IAnimalDailyLifeStage;

import java.util.List;

/**
 * @Author Jonathan
 * @Date 2020/3/4
 **/
public class AnimalDailyLife {

    private IAnimalDailyLifeStage animalDailyLifeStage;
    private IAnimalDailyLifeAction animalDailyLifeAction;
    private FriendshipGraph friendshipGraph;


    public AnimalDailyLife(List<Animal> animalList) {
        AnimalMappingBuilder.builder(animalList);
        friendshipGraph = AnimalMappingBuilder.getFriendshipGraph();
        animalDailyLifeStage = new AnimalDailyLifeStageImpl();
        animalDailyLifeAction = new AnimalDailyLifeActionImpl();

    }

    public void beforeLunchBreakUpFriends() {
        animalDailyLifeStage.beforeLunch();
        animalDailyLifeAction.breakupFriends();
        System.out.println(friendshipGraph.toString());
    }
}
