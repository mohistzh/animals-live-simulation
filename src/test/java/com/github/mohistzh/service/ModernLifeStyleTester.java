package com.github.mohistzh.service;

import com.github.mohistzh.repo.builder.DataLoader;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.stream.IntStream;

/**
 * @Author Jonathan
 * @Date 2020/3/5
 **/
public class ModernLifeStyleTester {
    ModernAnimalLifeStyle modernAnimalLifeStyle;

    @Before
    public void init() {
        DataLoader dataLoader = new DataLoader();
        modernAnimalLifeStyle = new ModernAnimalLifeStyle(dataLoader.getAnimalList());
    }

    @Test
    public void testBreakup() {
        IntStream.range(0, 5).forEach(i -> {
            modernAnimalLifeStyle.breakupFriends();
        });
    }

    @Test
    public void testMakeFriends() {
        IntStream.range(0, 5).forEach(i -> {
            modernAnimalLifeStyle.makeFriends();
        });
    }

    @After
    public void tearDown() {
        modernAnimalLifeStyle.printAnimalsStatus();
        modernAnimalLifeStyle.checkAnimalsActivities();
    }

}
