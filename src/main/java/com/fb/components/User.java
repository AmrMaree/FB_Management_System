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
    private ArrayList<Friendship> friends;
    private ArrayList<Conversation> conversations;
    private ArrayList <Notification> notifications;

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
    public void addFriend(Friendship friend){
        friends.add(friend);
    }

    public void receiveNotification(User user,Notification notification) {
        if (notifications == null) {
            notifications = new ArrayList<>();
        }
        notifications.add(0,notification);
        for (int i = 0; i < UserManager.users.size(); i++) {
            if (UserManager.users.get(i).getEmail().equals(user.getEmail())) {
                UserManager.users.set(i, user);
                break;
            }
        }
    }
    public void createPost(int postId , String content, String privacy) {
        boolean found= false;
        Post p1 = null;
        for(Post p : UserManager.users.get(0).getPosts())
        {
            if(p.getId()==postId)
            {
                found =true;
                p1=p;
                break;
            }
        }
        if(found)
        {
            for (int i = 0; i < UserManager.users.get(0).getPosts().size(); i++) {
                if (UserManager.users.get(0).getPosts().get(i).getContent().equals(content)) {
                    UserManager.users.get(0).getPosts().set(i,p1);
                    break;
                }
            }
        }else {
            Post post = new Post(postId, this.id, content,privacy);
            posts.add(0, post);
        }
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
    public List<Post> getPosts() { return posts; }
    public List<Conversation> getConversations() {
        return conversations;
    }
    public List<Friendship> getFriends() {
        return friends;
    }
    public void setConversations(ArrayList<Conversation> conversations) {
        this.conversations = conversations;
    }
    public ArrayList<Notification> getNotifications() {
        return notifications;
    }
}