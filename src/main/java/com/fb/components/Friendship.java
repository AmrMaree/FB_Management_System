package com.fb.components;

enum FriendType {
    REGULAR,
    RESTRICTED
}
class Friendship {
    private User friend;
    private FriendType type;

    public Friendship(User friend, FriendType type) {
        this.friend = friend;
        this.type = type;
    }

    public User getFriend() {
        return friend;
    }

    public FriendType getType() {
        return type;
    }
}