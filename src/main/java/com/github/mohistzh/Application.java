package com.github.mohistzh;

import com.github.mohistzh.repo.builder.DataLoader;
import com.github.mohistzh.repo.model.Animal;
import com.github.mohistzh.service.ModernAnimalLifeStyleStyle;
import java.util.List;

/**
 * @Author Jonathan
 * @Date 2020/3/4
 **/
public class Application {

    public static void main(String[] args) {
        // Initialize data model first
        DataLoader dataLoader = new DataLoader();
        List<Animal> rawData = dataLoader.getAnimalList();
        ModernAnimalLifeStyleStyle modernAnimalLifeStyle = new ModernAnimalLifeStyleStyle(rawData);

        int days = 10;
        for (int i = 1; i <= days; i++) {
            System.out.println("-------------Day-"+ i +" beginning-------------");
            System.out.println("----------Hungry! Let's breakup friends before having lunch-----------");
            modernAnimalLifeStyle.breakupFriends();
            System.out.println("----------It's time to have lunch-----------");
            modernAnimalLifeStyle.eating();
            System.out.println("----------Wonderful! Let's make some friends-----------");
            modernAnimalLifeStyle.makeFriends();
            System.out.println("-------------Day-"+ i +" completed-------------");
        }
        modernAnimalLifeStyle.printAnimalsStatus();
    }
}
