package com.github.mohistzh.repo;

import com.github.mohistzh.repo.builder.AnimalMappingBuilder;
import com.github.mohistzh.repo.builder.DataLoader;
import com.github.mohistzh.repo.model.Animal;
import com.github.mohistzh.repo.model.FriendshipGraph;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * @Author Jonathan
 * @Date 2020/3/4
 **/
public class FriendshipGraphTester {

    private List<Animal> animalList;
    private FriendshipGraph friendshipGraph;

    @Before
    public void init() {
        DataLoader dataLoader = new DataLoader();
        animalList = dataLoader.getAnimalList();
        AnimalMappingBuilder.builder(animalList);
        friendshipGraph = AnimalMappingBuilder.getFriendshipGraph();
    }

    @Test
    public void testAddFriendship() {
        System.out.println("------testAddFriendship------");
        friendshipGraph.addFriendship(11, 12);
        friendshipGraph.addFriendship(13, 1);
        System.out.println(friendshipGraph.toString());
    }

    @Test
    public void testRemoveFriendship() {
        System.out.println("------testRemoveFriendship------");
        friendshipGraph.removeFriendship(11,12);
        System.out.println(friendshipGraph.toString());
    }
}
