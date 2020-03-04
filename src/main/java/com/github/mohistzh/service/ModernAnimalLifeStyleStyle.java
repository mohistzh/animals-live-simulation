package com.github.mohistzh.service;

import com.github.mohistzh.config.RestrictionConstants;
import com.github.mohistzh.repo.model.Animal;
import com.github.mohistzh.repo.model.SocialActivity;
import com.github.mohistzh.service.internal.AbstractAnimalLifeStyleStyle;
import com.github.mohistzh.util.RandomUtils;
import java.util.LinkedHashSet;
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
            int partnerId = helpMeChoosePartnerToBreakup(animal.getId());
            if (partnerId < 1 || !makeDecision(animal.getId(), RestrictionConstants.BREAKEUP_FRIENDS)) {
                System.out.println(animal.getName() + " doesn't have a chance to breakup friends this time.");
                continue;
            }

            // for record purpose
            SocialActivity socialActivity = socialActivityMap.getOrDefault(SocialActivity.of(animal.getId(), partnerId), new SocialActivity());
            socialActivity.increseRejectedCount();
            String partnerName = animalOfMap.get(partnerId).getName();
            System.out.println("PING: " + animal.getName() + " tries to break up with " + partnerName);
            if (makeDecision(partnerId, RestrictionConstants.BREAKEUP_FRIENDS)) {
                friendshipGraph.removeFriendship(animal.getId(), partnerId);
                socialActivity.setFriends(false);
                System.out.println("PONG: " + animal.getName() + " and " + partnerName + " are not friends anymore.");
            } else {
                System.out.println("PONG: "  + partnerName + " rejected break up with " + animal.getName());
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
            if (!makeDecision(animal.getId(), RestrictionConstants.MAKE_FRIENDS)) {
                System.out.println(animal.getName() + " doesn't have a chance to make new friend this time.");
                continue;
            }
            String myName = animal.getName();
            int partnerId = helpMeChoosePartnerToSocial(animal.getId());
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
    private int helpMeChoosePartnerToSocial(int myId) {
        List<Integer> friends = friendshipGraph.getFriends(myId).stream().collect(Collectors.toList());
        friends.add(myId);
        int partnerId = RandomUtils.randomGeneration(1, animalList.size(), friends);
        return partnerId;
    }

    /**
     * Only be able to breakup with friends.
     * @param myId
     * @return
     */
    private int helpMeChoosePartnerToBreakup(int myId) {
        int friendId = -1;
        int friendForever = bestFriendForeverMap.get(myId);
        List<Integer> friends = friendshipGraph.getFriends(myId).stream().filter(item -> friendForever!=item).collect(Collectors.toList());
        if (friends.size() > 0) {
            friendId = RandomUtils.randomGeneration(friends);
        }
        return friendId;
    }
    /**
     * deciding whether breakup and make friends by settings.
     * @param animalId
     * @param branch
     * @return
     */
    private boolean makeDecision(int animalId, int branch) {
        boolean decision = false;
        StringBuffer stringBuffer = new StringBuffer();
        LinkedHashSet<Integer> friends = friendshipGraph.getFriends(animalId);
        int countOfFriends = friends.size();
        stringBuffer.append("[DEBUG] ").append(animalOfMap.get(animalId).getName())
                .append(" goes to make a decision, it has ")
                .append(countOfFriends).append(" friends: ");
        friends.stream().forEach(item -> {
            stringBuffer.append(animalOfMap.get(item).getName()).append(" ");
        });
        System.out.println(stringBuffer.toString());
        if (branch == RestrictionConstants.MAKE_FRIENDS) {
            if (countOfFriends >= RestrictionConstants.SOCIAL_TALENT_DEFINE) {
                decision = RandomUtils.generateRandomBoolean(RestrictionConstants.LOW_PERCENT_RATIO);
            } else {
                decision = RandomUtils.generateRandomBoolean(RestrictionConstants.HIGH_PERCENT_RATIO);
            }
        } else if (branch == RestrictionConstants.BREAKEUP_FRIENDS){
            if (countOfFriends >= RestrictionConstants.SOCIAL_TALENT_DEFINE) {
                decision = RandomUtils.generateRandomBoolean(RestrictionConstants.HIGH_PERCENT_RATIO);
            } else {
                decision = RandomUtils.generateRandomBoolean(RestrictionConstants.LOW_PERCENT_RATIO);
            }
        }
        return decision;
    }
}
