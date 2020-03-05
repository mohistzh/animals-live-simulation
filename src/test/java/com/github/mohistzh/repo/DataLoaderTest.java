package com.github.mohistzh.repo;

import com.github.mohistzh.repo.builder.DataLoader;
import com.github.mohistzh.repo.model.Animal;
import org.junit.Test;

import java.util.List;

/**
 * @Author Jonathan
 * @Date 2020/3/5
 **/
public class DataLoaderTest {

    private DataLoader dataLoader;

    @Test
    public void testLoad() {
        dataLoader = new DataLoader();
        List<Animal> animals = dataLoader.getAnimalList();
        for (Animal animal : animals) {
            System.out.println(animal);
        }
    }


}
