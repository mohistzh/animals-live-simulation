package com.github.mohistzh.service;

import com.github.mohistzh.builder.AnimalMappingBuilder;
import com.github.mohistzh.model.Animal;
import com.github.mohistzh.service.internal.AnimalDailyLifeActionImpl;
import com.github.mohistzh.service.internal.AnimalDailyLifeStageImpl;
import com.github.mohistzh.service.internal.IAnimalDailyLife;

import java.util.List;

/**
 * @Author Jonathan
 * @Date 2020/3/4
 **/
public class AnimalLiveSimulationService {

    private AnimalMappingBuilder animalMappingBuilder;
    private IAnimalDailyLife animalDailyLifeStage;
    private IAnimalDailyLife animalDailyLifeAction;

    public AnimalLiveSimulationService(List<Animal> animalList) {
        animalMappingBuilder.builder(animalList);
        animalDailyLifeStage = new AnimalDailyLifeStageImpl();
        animalDailyLifeAction = new AnimalDailyLifeActionImpl();
    }

    public void beforeLunchBreakUpFriends() {
        animalDailyLifeStage.beforeLunch();
        animalDailyLifeAction.beforeLunch();
    }
}
