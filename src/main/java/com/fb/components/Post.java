package com.fb.components;
import java.util.ArrayList;

public class Post {
    private int id;
    private int userId;
    private String content;
    private Privacy privacy;
    private ArrayList<Comment> comments;
    private ArrayList<User> taggedUsers;
    private ArrayList<User> likers;

    public Post(int id, int userId, String content, Privacy privacy) {
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
    public void addLiker(User liker) {
        likers.add(liker);
    }
    public void tagUser(User user) {
        taggedUsers.add(user);
    }
    public int getId() {
        return id;
    }


}