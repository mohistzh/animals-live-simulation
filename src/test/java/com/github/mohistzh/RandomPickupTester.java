package com.github.mohistzh;

import com.github.mohistzh.model.Animal;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Jonathan
 * @Date 2020/3/3
 **/
public class RandomPickupTester {

    List<Animal> initialData = null;

    @Before
    public void init() {
        initialData = new ArrayList<>();
        Animal animal1 = new Animal("Rex", "Royal Canin", "Tom");
        Animal animal2 = new Animal("Max", "Purina ONE", "Jay");
        Animal animal3 = new Animal("Tom", "Royal Canin", "Rex");
        Animal animal4 = new Animal("Jay", "Purina ONE", "Max");
        Animal animal5 = new Animal("Zoe", "9Lives", "Ada");
        Animal animal6 = new Animal("Ada", "Purina Friskies", "Zoe");
        Animal animal7 = new Animal("Meg", "Purina Layena", "Lis");
        Animal animal8 = new Animal("Lis", "Manna Pro", "Meg");
        Animal animal9 = new Animal("Emi", "Purina Layena", "Lua");
        Animal animal10 = new Animal("Lua", "Manna Pro", "Emi");
        Animal animal11 = new Animal("Bob", "Manna Pro", null);
        Animal animal12 = new Animal("Mac", "Lafeber Original", "Alf");
        Animal animal13 = new Animal("Alf", "Kaytee Fiesta”", "Mac");
        initialData.add(animal1);
        initialData.add(animal2);
        initialData.add(animal3);
        initialData.add(animal4);
        initialData.add(animal5);
        initialData.add(animal6);
        initialData.add(animal7);
        initialData.add(animal8);
        initialData.add(animal9);
        initialData.add(animal10);
        initialData.add(animal11);
        initialData.add(animal12);
        initialData.add(animal13);
    }
    @Test
    public void testRandomPickUp() {
        initialData.stream().forEach(entry -> System.out.println(entry.getName()));

    }
}
