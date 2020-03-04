package com.github.mohistzh;

import com.github.mohistzh.repo.builder.DataLoader;
import com.github.mohistzh.repo.model.Animal;
import com.github.mohistzh.service.AnimalDailyLife;
import java.util.List;

/**
 * @Author Jonathan
 * @Date 2020/3/4
 **/
public class Application {

    public static void main(String[] args) {
        DataLoader dataLoader = new DataLoader();
        List<Animal> rawData = dataLoader.getAnimalList();
        AnimalDailyLife simulationService = new AnimalDailyLife(rawData);

        int days = 10;
        for (int i = 1; i <= days; i++) {
            System.out.println("-------------Day-"+ i +" beginning-------------");
            simulationService.beforeLunchBreakUpFriends();
            simulationService.lunchTime();
            simulationService.afterLunchMakeFriends();
            System.out.println("-------------Day-"+ i +" completed-------------");
        }
        simulationService.printTable();
    }
}
