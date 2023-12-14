package com.fb.components;
import java.util.ArrayList;

enum Privacy {
    PUBLIC,
    FRIENDS_ONLY
}
public class Post {
    private static int nextId = 1;
    private int id;
    private User author;
    private String content;
    private Privacy privacy;
    private ArrayList<Comment> comments;
    private ArrayList<User> taggedUsers;
    private ArrayList<User> likers;

    public Post(User author, String content) {
        this.id = nextId++;
        this.author = author;
        this.content = content;
        this.privacy = privacy;
        this.comments = new ArrayList<>();
        this.taggedUsers = new ArrayList<>();
        this.likers = new ArrayList<>();
    }

    public void display() {
        System.out.println("Post by " + author.getName() + ": " + content);
        System.out.println("Privacy: " + privacy);
        System.out.println("Tagged Users: " + taggedUsers);
        System.out.println("Likes: " + likers.size());
        System.out.println("Comments:");
        for (Comment comment : comments) {
            comment.display();
        }
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

}