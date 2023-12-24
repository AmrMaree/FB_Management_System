package com.fb.components;
import java.util.ArrayList;

public class Post extends SocialContent{
    private String privacy;
    private ArrayList<Comment> comments;
    private ArrayList<Integer> taggedUsers;
    private final ArrayList<Integer> likers;
    private int NumberOfLikes = 0;
    public Post(int id, int userId, String content, String privacy) {
        super(id, userId, content);
        this.privacy = privacy;
        this.comments = new ArrayList<>();
        this.taggedUsers = new ArrayList<>();
        this.likers = new ArrayList<>();
    }
    public void addComment(Comment comment) {
        comments.add(comment);
    }
    @Override
    public void addLiker(int liker) {
        boolean found = false;
        for(int l : likers){
            if(l == liker){
                found = true;
            }
        }
        if(!found) {
            likers.add(liker);
            NumberOfLikes++;
        }
    }
    public void tagUser(int userId) {
        taggedUsers.add(userId);
    }
    public String getPrivacy() {
        return privacy;
    }
    public ArrayList<Comment> getComments() {
        return comments;
    }
    public ArrayList<Integer> getLikers() {
        return likers;
    }
    public ArrayList<Integer> getTaggedUsers() {
        return taggedUsers;
    }
    public int getNumberOfLikes() {
        return NumberOfLikes;
    }
}