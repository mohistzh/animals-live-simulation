package com.github.mohistzh.service;

import com.github.mohistzh.config.RestrictionConstants;
import com.github.mohistzh.repo.model.Animal;
import com.github.mohistzh.repo.model.SocialActivity;
import com.github.mohistzh.service.internal.AbstractAnimalLifeStyleStyle;
import com.github.mohistzh.util.RandomUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * A type of animal life stype
 * @Author Jonathan
 * @Date 2020/3/4
 **/
public class ModernAnimalLifeStyleStyle extends AbstractAnimalLifeStyleStyle {
    public ModernAnimalLifeStyleStyle(List<Animal> animalList) {
        super(animalList);
    }

    @Override
    public void breakupFriends() {
        for (int i = 0; i < animalList.size(); i++) {
            Animal animal = animalList.get(i);
            int partnerId = helpMeChoosePartner(animal.getId());
            // for record purpose
            SocialActivity socialActivity = socialActivityMap.getOrDefault(SocialActivity.of(animal.getId(), partnerId), new SocialActivity());
            socialActivity.increseRejectedCount();
            String partnerName = animalOfMap.get(partnerId).getName();
            System.out.println("PING: " + animal.getName() + " tries to break up with " + partnerName);
            // if we are friends now
            if (friendshipGraph.getFriends(animal.getId()).contains(partnerId)) {

                if (makeDecision(partnerId, RestrictionConstants.BREAKEUP_FRIENDS)) {
                    friendshipGraph.removeFriendship(animal.getId(), partnerId);
                    socialActivity.setFriends(false); //TODO
                    System.out.println("PONG: " + animal.getName() + " and " + partnerName + " are not friends anymore.");
                } else {
                    //socialActivity.setFriends(true);
                    System.out.println("PONG: "  + partnerName + " rejected break up with " + animal.getName());
                }

            } else {
                socialActivity.setStranger(true);
                System.out.println("PONG: "  + animal.getName() + " and " + partnerName + " don't know each other, they should make friends first.");
            }
            socialActivityMap.putIfAbsent(SocialActivity.of(animal.getId(), partnerId), socialActivity);
        }
    }


    /**
     * Eating food with animals whose have the same interests
     */
    @Override
    public void eating() {
        Map<String, List<Animal>> animalPerFood = animalList.stream()
                .collect(Collectors.groupingBy(Animal::getFavoriteFood));
        for (String food : animalPerFood.keySet()) {
            List<Animal> animals = animalPerFood.get(food);
            // for statements appearing
            if (animals.size() > 1) {
                StringBuffer stringBuffer = new StringBuffer();
                for (int i = 0; i < animals.size(); i++) {
                    if (i == animals.size() - 1) {
                        stringBuffer.deleteCharAt(stringBuffer.lastIndexOf(", "));
                        stringBuffer.append("and ").append(animals.get(i).getName());
                    } else {
                        stringBuffer.append(animals.get(i).getName()).append(", ");
                    }
                }
                System.out.println(stringBuffer.toString() + " are eating their favourite food " + food);

            } else {
                System.out.println(animals.get(0).getName() + " is eating "+ food + " alone.");
            }
        }
    }

    @Override
    public void makeFriends() {
        for (int i = 0; i < animalList.size(); i++) {
            Animal animal = animalList.get(i);
            String myName = animal.getName();
            int partnerId = helpMeChoosePartner(animal.getId());
            String partnerName = animalOfMap.get(partnerId).getName();
            SocialActivity socialActivity = socialActivityMap.getOrDefault(SocialActivity.of(animal.getId(), partnerId), new SocialActivity());
            socialActivity.increaseAskedCount();
            System.out.println("PING: "+myName + " tries to make a friend with " + partnerName);
            if (!friendshipGraph.getFriends(animal.getId()).contains(partnerId)) {
                if (makeDecision(partnerId, RestrictionConstants.MAKE_FRIENDS)) {
                    friendshipGraph.addFriendship(animal.getId(), partnerId);
                    socialActivity.setFriends(true);
                    socialActivity.increaseBecomeFriendsCount();
                    System.out.println("PONG: " + myName + " and " + partnerName + " become friends.");
                } else {
                    socialActivity.setFriends(false);
                    System.out.println("PONG: " + partnerName + " rejected make friends with " + myName);
                }

            } else {
                System.out.println("PONG: " + myName + " and " + partnerName + " were friends.");
            }
            socialActivityMap.putIfAbsent(SocialActivity.of(animal.getId(), partnerId), socialActivity);
        }
    }

    @Override
    public void printAnimalsStatus() {
        System.out.println("----------------------------Modern animals social life check list----------------------------");
        System.out.println("Note: ○ means stranger, × means breaks up with friend, √ means friend right now" +"\n");
        String[][] table = new String[this.animalList.size() + 1][this.animalList.size() + 1];
        String[] symbols = {"○", "×", "√"};
        table[0][0]="";
        for (int row = 0; row < table.length; row++) {
            for (int col = 0; col < table[row].length; col++) {
                if (row == 0 && col > 0) {
                    table[row][col] = animalOfMap.get(col).getName();
                } else if (row > 0 && col == 0) {
                    table[row][col] = animalOfMap.get(row).getName();
                }
            }

        }
        //stranger, unfriends, friends(included forever friends)

        socialActivityMap.forEach((k, v) -> {
            String symbol = "";
            if (v.isForeverFriend() || v.isFriends()) {
                symbol = symbols[2];
            } else if (v.isStranger()) {
                symbol = symbols[0];
            } else {
                symbol = symbols[1];
            }
            table[k.getKey()][k.getValue()] = symbol;
        });
        for (String[] row : table) {
            for (String entry : row) {
                if (entry == null) {
                    entry = symbols[0]; // stranger
                }
                System.out.format("%5s%4s", entry, "");
                System.out.print("|");
            }
            System.out.println();
        }
    }

    @Override
    public void checkAnimalsActivities() {

    }

    /**
     * to pick up a partner
     * @param myId
     * @return
     */
    private int helpMeChoosePartner(int myId) {
        int friendForever = bestFriendForeverMap.get(myId); // my best forever friend and myself shouldn't in the choose list
        int partnerId = RandomUtils.randomGeneration(1, animalList.size(), Arrays.asList(myId, friendForever));
        return partnerId;
    }
    /**
     * deciding whether breakup and make friends by settings.
     * @param animalId
     * @param branch
     * @return
     */
    private boolean makeDecision(int animalId, int branch) {
        boolean decision = false;
        switch (branch) {
            case RestrictionConstants.BREAKEUP_FRIENDS:
                if (friendshipGraph.getFriends(animalId).size() >= RestrictionConstants.SOCIAL_TALENT_DEFINE) {
                    decision = RandomUtils.randomBooleanGreaterThan(RestrictionConstants.SOCIAL_TALENT_LOST_FRIEND_RATIO);
                } else {
                    decision = RandomUtils.randomBooleanLessThan(RestrictionConstants.SOCIAL_WEAKER_LOST_FRIEND_RATIO);
                }
            case RestrictionConstants.MAKE_FRIENDS:
                if (friendshipGraph.getFriends(animalId).size() >= RestrictionConstants.SOCIAL_TALENT_DEFINE) {
                    decision = RandomUtils.randomBooleanLessThan(RestrictionConstants.SOCIAL_TALENT_GAIN_FRIEND_RATIO);
                } else {
                    decision = RandomUtils.randomBooleanGreaterThan(RestrictionConstants.SOCIAL_WEAKER_GAIN_FRIEND_RATIO);
                }
            default:

        }
        return decision;
    }
}
