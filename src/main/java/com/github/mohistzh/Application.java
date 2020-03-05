package com.github.mohistzh;

import com.github.mohistzh.repo.builder.DataLoader;
import com.github.mohistzh.repo.model.Animal;
import com.github.mohistzh.service.ModernAnimalLifeStyle;
import java.util.List;

/**
 * @Author Jonathan
 * @Date 2020/3/4
 **/
public class Application {

    public static void main(String[] args) {
        int days = argParser(args);
        System.out.println("----------------------------"+days+ " days of animals live simulation----------------------------");
        System.out.println();
        // Initialize data model
        DataLoader dataLoader = new DataLoader();
        List<Animal> rawData = dataLoader.getAnimalList();
        ModernAnimalLifeStyle modernAnimalLifeStyle = new ModernAnimalLifeStyle(rawData);
        for (int i = 1; i <= days; i++) {
            System.out.println("-------------Day-"+ i +" beginning-------------");
            System.out.println("----------Hungry! Let's breakup friends before having lunch-----------");
            modernAnimalLifeStyle.breakupFriends();
            System.out.println("----------It's time to have lunch-----------");
            modernAnimalLifeStyle.eating();
            System.out.println("----------Wonderful! Let's make some friends-----------");
            modernAnimalLifeStyle.makeFriends();
            System.out.println("-------------Day-"+ i +" completed-------------");
            System.out.println();
        }
        modernAnimalLifeStyle.printAnimalsStatus();
    }
    private static int argParser(String[] args) {
        int days = 10;
        if (args.length > 1) {
            System.out.println("Invalid input, the program ONLY supports one argument, use default value 10 continue to work.");
        } else {
            try {
                days = Integer.parseInt(args[0]);
                if (days > 1000 || days <= 0) {
                    System.out.println("Invalid input, the program ONLY supports one number from 1 ~ 1000, use default value 10 continue to work.");
                    days = 10;
                }
            } catch (Exception e){
                System.out.println("Invalid input, the program ONLY supports one number from 1 ~ 1000, use default value 10 continue to work.");
            }
        }
        return days;
    }
}
