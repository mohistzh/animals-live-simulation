package com.github.mohistzh;

import com.github.mohistzh.builder.DataLoader;
import com.github.mohistzh.service.AnimalDailyLife;

/**
 * @Author Jonathan
 * @Date 2020/3/4
 **/
public class Application {

    public static void main(String[] args) {
        DataLoader dataLoader = new DataLoader();
        AnimalDailyLife simulationService = new AnimalDailyLife(dataLoader.getAnimalList());
        simulationService.beforeLunchBreakUpFriends();
    }
}
