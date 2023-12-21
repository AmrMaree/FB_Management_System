package com.fb.components;
import java.util.ArrayList;

public class Reply {
    private static int nextId = 1;
    final private int id;
    private User author;
    private String text;
    private ArrayList<User> likers;
    public Reply(User author, String text) {
        this.id = nextId++;
        this.author = author;
        this.text = text;
        this.likers = new ArrayList<>();
    }
    public void addLiker(User liker) {
        likers.add(liker);
    }
}