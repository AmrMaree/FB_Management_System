package com.fb.components;
import java.util.ArrayList;

public class Reply {
    private static int nextId = 1;
    private int id;
    private User author;
    private String text;
    private ArrayList<User> likers;

    public Reply(User author, String text) {
        this.id = nextId++;
        this.author = author;
        this.text = text;
        this.likers = new ArrayList<>();
    }

    public void display() {
        System.out.println(author.getName() + ": " + text);
        System.out.println("Likes: " + likers.size());
    }

    public void addLiker(User liker) {
        likers.add(liker);
    }
}