package com.github.mohistzh.repo.builder;

import com.github.mohistzh.repo.model.Animal;

import java.util.ArrayList;
import java.util.List;

/**
 * Data Loader, to use persist data store instead of it in a serious runtime
 * @Author Jonathan
 * @Date 2020/3/4
 **/
public class DataLoader {
    private List<Animal> animalList = new ArrayList<>();
    public DataLoader() {
        Animal animal1 = new Animal(1, "Rex", "Royal Canin", 3);
        Animal animal2 = new Animal(2, "Max", "Purina ONE", 4);
        Animal animal3 = new Animal(3, "Tom", "Royal Canin", 1);
        Animal animal4 = new Animal(4, "Jay", "Purina ONE", 2);
        Animal animal5 = new Animal(5, "Zoe", "9Lives", 6);
        Animal animal6 = new Animal(6, "Ada", "Purina Friskies", 5);
        Animal animal7 = new Animal(7, "Meg", "Purina Layena", 8);
        Animal animal8 = new Animal(8, "Lis", "Manna Pro", 7);
        Animal animal9 = new Animal(9, "Emi", "Purina Layena", 10);
        Animal animal10 = new Animal(10, "Lua", "Manna Pro", 9);
        Animal animal11 = new Animal(11, "Bob", "Manna Pro", -1);
        Animal animal12 = new Animal(12, "Mac", "Lafeber Original", 13);
        Animal animal13 = new Animal(13, "Alf", "Kaytee Fiesta", 12);
        animalList.add(animal1);
        animalList.add(animal2);
        animalList.add(animal3);
        animalList.add(animal4);
        animalList.add(animal5);
        animalList.add(animal6);
        animalList.add(animal7);
        animalList.add(animal8);
        animalList.add(animal9);
        animalList.add(animal10);
        animalList.add(animal11);
        animalList.add(animal12);
        animalList.add(animal13);
    }
    public List<Animal> getAnimalList() {
        return animalList;
    }
}
