package com.github.mohistzh.repo;

import com.github.mohistzh.repo.builder.AnimalMappingBuilder;
import com.github.mohistzh.repo.builder.DataLoader;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @Author Jonathan
 * @Date 2020/3/5
 **/
public class DataBuilderTester {


    @Before
    public void init() {
        DataLoader dataLoader = new DataLoader();
        AnimalMappingBuilder.builder(dataLoader.getAnimalList());
    }

    @Test
    public void testSocialActivityMap() {
        AnimalMappingBuilder.getSocialActivityMap().forEach((k, v) -> {
            System.out.println(k.getKey() + " & " + k.getValue());
            System.out.println(v.toString());
            Assert.assertEquals(v.isForeverFriend(), true);
        });
    }

}
