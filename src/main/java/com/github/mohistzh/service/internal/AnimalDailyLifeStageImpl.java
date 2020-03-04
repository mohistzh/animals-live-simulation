package com.github.mohistzh.service.internal;

/**
 * @Author Jonathan
 * @Date 2020/3/4
 **/
public class AnimalDailyLifeStageImpl implements IAnimalDailyLifeStage {
    @Override
    public void beforeLunch() {
        System.out.println("Before lunch");
    }

    @Override
    public void duringLunch() {

        System.out.println("During lunch");
    }

    @Override
    public void afterLunch() {
        System.out.println("After lunch");
    }
}
