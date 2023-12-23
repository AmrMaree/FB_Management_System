package com.fb.components;

public class Notification {
    private String content;
    private int senderId, receiverId;
    public Notification(String content, int senderId , int receiverId) {
        this.content = content;
        this.senderId = senderId;
        this.receiverId = receiverId;
    }
    public String getContent() {
        return content;
    }
    public int getSenderId() {
        return senderId;
    }
    public int getReceiverId() {
        return receiverId;
    }
}

