package com.github.mohistzh.repo.builder;

import com.github.mohistzh.repo.model.Animal;

import java.io.BufferedReader;
import java.io.FileReader;
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
        try {
            load();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-1); // unnecessary to continue
        }
    }

    /**
     * Load and parse data from csv file
     * @throws Exception
     */
    private void load() throws Exception {
        Animal animal = null;
        String filePath = "static/animals_sample.csv";
        String line = null, csvSplitSymbol = ",";
        boolean flag = false;
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
            while ((line = bufferedReader.readLine()) != null) {
                if (!flag) {
                    flag = true;
                    continue;
                }
                String[] rawData = line.split(csvSplitSymbol);
                animal = new Animal(Integer.parseInt(rawData[0].trim()), rawData[1].trim(),
                        rawData[2].trim(), Integer.parseInt(rawData[3]));
                animalList.add(animal);
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public List<Animal> getAnimalList() {
        return animalList;
    }
}
