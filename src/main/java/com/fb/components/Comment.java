package com.fb.components;
import java.util.ArrayList;
public class Comment {
    private static int nextId = 1;
    final private int id;
    final private int userId;
    final private int postId;
    private String text;
    private ArrayList<Reply> replies;
    private ArrayList<Integer> likers;
    private int NumberOfLikes = 0;
    public Comment(int userId, int postId, String text) {
        this.postId = postId;
        this.id = nextId++;
        this.userId = userId;
        this.text = text;
        this.replies = new ArrayList<>();
        this.likers = new ArrayList<>();
    }
    public void addReply(int replierId, String text) {
        replies.add(new Reply(replierId, text));
    }
    public void addLiker(int likerId) {
        likers.add(likerId);
    }
    public String getText() {return text;}
}