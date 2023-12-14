package com.fb.components;
import java.util.ArrayList;

public class Comment {
    private static int nextId = 1;
    private int id;
    private User author;
    private String text;
    private ArrayList<Reply> replies;
    private ArrayList<User> likers;

    public Comment(User author, String text) {
        this.id = nextId++;
        this.author = author;
        this.text = text;
        this.replies = new ArrayList<>();
        this.likers = new ArrayList<>();
    }

    public void display() {
        System.out.println(author.getName() + ": " + text);
        System.out.println("Likes: " + likers.size());
        System.out.println("Replies:");
        for (Reply reply : replies) {
            reply.display();
        }
    }

    public void addReply(User replier, String text) {
        replies.add(new Reply(replier, text));
    }

    public void addLiker(User liker) {
        likers.add(liker);
    }
}