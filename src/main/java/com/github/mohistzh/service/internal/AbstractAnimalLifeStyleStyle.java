package com.github.mohistzh.service.internal;

import com.github.mohistzh.repo.builder.AnimalMappingBuilder;
import com.github.mohistzh.repo.model.Animal;
import com.github.mohistzh.repo.model.FriendshipGraph;
import com.github.mohistzh.repo.model.SocialActivity;
import javafx.util.Pair;

import java.util.List;
import java.util.Map;

/**
 * Providing some abstract data structure and requirements for sub-class to implement and extend their functionalities.
 * @Author Jonathan
 * @Date 2020/3/4
 **/
public abstract class AbstractAnimalLifeStyleStyle implements IAnimalLifeStyleAction {

    //private IAnimalLifeStyleAction animalDailyLifeAction;
    protected  FriendshipGraph friendshipGraph;
    // best friend forever mapping
    protected  Map<Integer, Integer> bestFriendForeverMap;

    // animal id to animal object
    protected  Map<Integer, Animal> animalOfMap;

    // Animals social acvitities
    protected  Map<Pair<Integer, Integer>, SocialActivity> socialActivityMap;

    //Raw data of animals
    protected  List<Animal> animalList;

    public AbstractAnimalLifeStyleStyle(List<Animal> animalList) {
        this.animalList = animalList;
        AnimalMappingBuilder.builder(animalList);
        friendshipGraph = AnimalMappingBuilder.getFriendshipGraph();
        bestFriendForeverMap = AnimalMappingBuilder.getBestFriendForeverMap();
        animalOfMap = AnimalMappingBuilder.getAnimalOfMap();
        socialActivityMap = AnimalMappingBuilder.getSocialActivityMap();

    }

    /**
     * Print animals' social status for human
     */
    public abstract void printAnimalsStatus();

    /**
     * Logging animals' activities
     */
    public abstract void checkAnimalsActivities();

}
