package com.github.mohistzh.service;

import com.github.mohistzh.repo.builder.DataLoader;
import org.junit.Before;
import org.junit.Test;

import java.util.stream.IntStream;

/**
 * @Author Jonathan
 * @Date 2020/3/5
 **/
public class ModernLifeStyleTester {
    ModernAnimalLifeStyleStyle modernAnimalLifeStyleStyle;

    @Before
    public void init() {
        DataLoader dataLoader = new DataLoader();
        modernAnimalLifeStyleStyle = new ModernAnimalLifeStyleStyle(dataLoader.getAnimalList());
    }

    @Test
    public void testBreakup() {
        IntStream.range(0, 100).forEach(i -> {
            modernAnimalLifeStyleStyle.breakupFriends();
        });
    }
}
