package com.fb.components;

public class Message {
    private int id;
    private int sender;
    private int recipient;
    private String content;

    public Message(int id, int senderId, int recipientId, String content) {
        this.id = id;
        this.sender = sender;
        this.recipient = recipient;
        this.content = content;
    }
    public int getId() {
        return id;
    }
    public String getMessageContent(){
        return content;
    }
}
