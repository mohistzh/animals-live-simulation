package com.github.mohistzh;

import com.github.mohistzh.builder.DataLoader;
import com.github.mohistzh.service.AnimalDailyLife;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.stream.IntStream;

/**
 * @Author Jonathan
 * @Date 2020/3/4
 **/
public class AnimalDailyLifeTester {
    private AnimalDailyLife animalDailyLife;

    @Before
    public void init() {
        DataLoader dataLoader = new DataLoader();
        animalDailyLife = new AnimalDailyLife(dataLoader.getAnimalList());
    }

    @Test
    public void testAfterLunchMakeFriends() {
        animalDailyLife.afterLunchMakeFriends();

    }
//    @Test
//    public void testBeforeLunchBreakUpFriends() {
//        animalDailyLife.beforeLunchBreakUpFriends();
//    }
//    @Test
//    public void testLunchTime() {
//        animalDailyLife.lunchTime();
//    }

    @After
    public void after() {
        animalDailyLife.printTable();
    }
}
