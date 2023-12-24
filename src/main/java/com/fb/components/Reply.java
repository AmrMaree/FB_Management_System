package com.fb.components;
import java.util.ArrayList;

public class Reply {
    private static int nextId = 1;
    final private int id;
    private int userId;
    private String text;
    private ArrayList<Integer> likers;
    public Reply(int userId, String text) {
        this.id = nextId++;
        this.userId = userId;
        this.text = text;
        this.likers = new ArrayList<>();
    }
    public void addLiker(Integer liker) {
        likers.add(liker);
    }
}