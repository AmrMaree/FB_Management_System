package com.fb.components;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    final private int id;
    private String email;
    private String name;
    private String password;
    private String gender;
    private String birthdate;
    private ArrayList<Post> posts;
    private ArrayList<Integer> friends;
    private ArrayList<Conversation> conversations;
    private ArrayList <String> notifications;

    public User(int id ,String name, String email, String password, String gender, String birthdate) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.birthdate = birthdate;
        this.posts = new ArrayList<>();
        this.friends = new ArrayList<>();
        this.conversations = new ArrayList<>();
        this.notifications = new ArrayList<>();
    }

    public void receiveNotification(User user) {
        if (notifications == null) {
            notifications = new ArrayList<>();
        }
        notifications.add("You got a friend request from " + user.getName());
        for (int i = 0; i < UserManager.users.size(); i++) {
            if (UserManager.users.get(i).getEmail().equals(user.getEmail())) {
                UserManager.users.set(i, user);
                break;
            }
        }
    }

    public void acceptFriendRequest(User RequestSender, User RequestAccepter){
        friends.add(RequestSender.getId());
        RequestSender.friends.add(id);
    }
    public void createPost(int postId , String content, String privacy) {
        Post post = new Post(postId, this.id, content,privacy);
        posts.add(0, post);
    }
    public void createConversation(int conversationId ,int senderId, int participentId){
        Conversation conversation = new Conversation(conversationId, senderId, participentId);
        conversations.add(conversation);
    }
    public int getId() {
        return id;
    }
    public String getGender() {
        return gender;
    }
    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getBirthDate() {
        return birthdate;
    }
    public List<Post> getPosts() {
       return posts;

    }
    public List<Conversation> getConversations() {
        return conversations;
    }
    public List<Integer> getFriends() {
        return friends;
    }
    public void setConversations(ArrayList<Conversation> conversations) {
        this.conversations = conversations;
    }
}