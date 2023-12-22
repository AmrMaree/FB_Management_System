package com.fb.components;
import java.util.ArrayList;

public class Comment {
    private static int nextId = 1;
    final private int id;
    final private int userId;
    private String text;
    private ArrayList<Reply> replies;
    private ArrayList<User> likers;

    public Comment(int userId, String text) {
        this.id = nextId++;
        this.userId = userId;
        this.text = text;
        this.replies = new ArrayList<>();
        this.likers = new ArrayList<>();
    }

    public void addReply(User replier, String text) {
        replies.add(new Reply(replier, text));
    }

    public void addLiker(User liker) {
        likers.add(liker);
    }
}