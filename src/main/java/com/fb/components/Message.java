package com.fb.components;
public class Message {
    private int id;
    private int senderId;
    private int recipientId;
    private String content;
    public Message(int id, int senderId, int recipientId, String content) {
        this.id = id;
        this.senderId = senderId;
        this.recipientId = recipientId;
        this.content = content;
    }
   public int getId() {
        return id;
    }
    public String getMessageContent(){
        return content;
    }
    public int getSenderId() {
        return senderId;
    }
    public int getRecipientId() {
        return recipientId;
    }
}
