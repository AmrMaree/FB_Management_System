package com.fb.components;
import java.util.ArrayList;

public class Post {
    private final int id;
    private int userId;
    private String content;
    private String privacy;
    private ArrayList<Comment> comments;
    private ArrayList<User> taggedUsers;
    private ArrayList<Integer> likers;
    private int NumberOfLikes = 0;

    public Post(int id, int userId, String content, String privacy) {
        this.id = id;
        this.userId = userId;
        this.content = content;
        this.privacy = privacy;
        this.comments = new ArrayList<>();
        this.taggedUsers = new ArrayList<>();
        this.likers = new ArrayList<>();
    }

    public void addComment(User commenter, String text) {
        comments.add(new Comment(commenter, text));
    }

    public void addLiker(int liker) {
        boolean found = false;
        for(int l : likers){
            if(l == liker){
               found = true;
            }
        }
        if(!found){
            likers.add(liker);
        }
        NumberOfLikes++;
    }

    public void tagUser(User user) {
        taggedUsers.add(user);
    }
    public int getId() {
        return id;
    }

    public String getContent() {
        return content;
    }
    public String getPrivacy() {
        return privacy;
    }
    public int getNumberOfLikes() {
        return NumberOfLikes;
    }
    public int getUserId() {
        return userId;
    }

}