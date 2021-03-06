package com.github.mohistzh.config;
/**
 * 1) If an animal has 3 or more friends, the probability to lose friend 90%, the probability to get a new friend 10%.
 * 2) If an animal has 2 or ​fewer ​friends, the probability to lose friend 10%, the probability to get a new friend 90%
 * @Author Jonathan
 * @Date 2020/3/3
 **/
public class RestrictionConstants {
    // If an animal has 3 or more friends
    public static final int SOCIAL_TALENT_DEFINE = 3;


    public static final float HIGH_PERCENT_RATIO = 0.90f;
    public static final float LOW_PERCENT_RATIO = 0.10f;
    public static final int MAKE_FRIENDS=1;
    public static final int BREAKEUP_FRIENDS= 2;
}
