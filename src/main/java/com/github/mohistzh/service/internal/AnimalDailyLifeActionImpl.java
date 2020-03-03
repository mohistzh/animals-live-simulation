package com.github.mohistzh.service.internal;

/**
 * @Author Jonathan
 * @Date 2020/3/4
 **/
public class AnimalDailyLifeActionImpl implements IAnimalDailyLife {

    @Override
    public void beforeLunch() {
        System.out.println("breakup with friends");
    }

    @Override
    public void duringLunch() {
        System.out.println("eating food");

    }

    @Override
    public void afterLunch() {
        System.out.println("make friends");

    }
}
