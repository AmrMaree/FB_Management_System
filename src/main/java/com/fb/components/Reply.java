package com.fb.components;
import java.util.ArrayList;

public class Reply extends SocialContent{
    private ArrayList<Integer> likers;
    public Reply(int id,int userId, String content) {
        super(id,userId,content);
        this.likers = new ArrayList<>();
    }
    @Override
    public void addLiker(int liker) {
        likers.add(liker);
    }

}