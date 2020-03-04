package com.github.mohistzh.repo.model;

import javafx.util.Pair;

/**
 * Records animals social activities
 * @Author Jonathan
 * @Date 2020/3/4
 **/
public class SocialActivity {


    // take an oath never broken
    private boolean foreverFriend;

    // never say hello once
    private boolean stranger;

    // is friend now?
    private boolean friends;

    // count of k rejected v
    private int rejectedCount;

    // count of k asked v
    private int askedCount;

    // become friends times
    private int becomeFriendsCount;

    public SocialActivity(){}

    /**
     * initialize attributes by data default setting
     * @param foreverFriend
     */
    public SocialActivity(boolean foreverFriend) {
        if (foreverFriend) {
            this.stranger = false;
            this.friends = true;
        }
        this.setForeverFriend(foreverFriend);
    }
    public static Pair<Integer, Integer> of(int idA, int idB) {
        return new Pair<Integer, Integer>(idA, idB);
    }

    public int increseRejectedCount() {
        return this.rejectedCount++;
    }

    public int increaseAskedCount() {
        return this.askedCount++;
    }

    public int increaseBecomeFriendsCount() {
        return this.becomeFriendsCount++;
    }

    public boolean isForeverFriend() {
        return foreverFriend;
    }

    public void setForeverFriend(boolean foreverFriend) {
        if (foreverFriend) {
            stranger = false;
            friends = true;
        }
        this.foreverFriend = foreverFriend;
    }

    public boolean isStranger() {
        return stranger;
    }

    public void setStranger(boolean stranger) {
        this.stranger = stranger;
    }

    public boolean isFriends() {
        return friends;
    }

    public void setFriends(boolean friends) {
        if (friends) {
            stranger = false;
        }
        this.friends = friends;
    }

    public int getRejectedCount() {
        return rejectedCount;
    }

    public void setRejectedCount(int rejectedCount) {
        this.rejectedCount = rejectedCount;
    }

    public int getAskedCount() {
        return askedCount;
    }

    public void setAskedCount(int askedCount) {
        this.askedCount = askedCount;
    }

    public int getBecomeFriendsCount() {
        return becomeFriendsCount;
    }

    public void setBecomeFriendsCount(int becomeFriendsCount) {
        this.becomeFriendsCount = becomeFriendsCount;
    }

    @Override
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Forever friend: ").append(foreverFriend);
        stringBuffer.append("\n");
        stringBuffer.append("Is stranger? ").append(stranger);
        stringBuffer.append("\n");
        stringBuffer.append("Is a normal friend: ").append(friends);
        stringBuffer.append("\n");
        stringBuffer.append("Rejected times: ").append(rejectedCount);
        stringBuffer.append("\n");
        stringBuffer.append("Asked times: ").append(askedCount);
        stringBuffer.append("\n");
        stringBuffer.append("Become friend times: ").append(becomeFriendsCount);
        stringBuffer.append("\n");
        return stringBuffer.toString();
    }
}
