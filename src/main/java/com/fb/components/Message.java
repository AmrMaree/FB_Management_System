package com.fb.components;

public class Message {
    private static int nextId = 1;
    private int id;
    private User sender;
    private User recipient;
    private String content;

    public Message(User sender, User recipient, String content) {
        this.id = nextId++;
        this.sender = sender;
        this.recipient = recipient;
        this.content = content;
    }
    public String getMessageContent(){
        return content ;
    }
    // Other methods for getting sender, recipient, etc.
}
