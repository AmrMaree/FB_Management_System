package com.fb.components;

public class Friendship {
    private int userId;
    private int friendId;
    private String type;
    public Friendship(int userId ,int friendId, String type) {
        this.userId = userId;
        this.friendId = friendId;
        this.type = type;
    }
    public int getFriendId() {
        return friendId;
    }
    public int getUserId() {
        return userId;
    }
    public String getType() {
        return type;
    }
}