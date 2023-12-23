package com.fb.components;

import java.util.ArrayList;

public class Conversation {
    private final int id;
    private ArrayList<Integer> participants;
    private ArrayList<Message> messages;

    public Conversation(int id, int user1Id, int user2Id) {
        this.id = id;
        this.participants = new ArrayList<Integer>();
        this.messages = new ArrayList<>();
        participants.add(user1Id);
        participants.add(user2Id);
    }

    public void createMessage(int messageId ,int senderId, int participentId, String content){
        Message message = new Message(messageId, senderId, participentId, content);
        messages.add(message);
    }
    public int getId() {
        return id;
    }
    public void addMessage(Message message) {
        messages.add(message);
    }

    public ArrayList<Integer> getParticipants() {
        return participants;
    }

    public ArrayList<Message> getMessages() {
        return messages;
    }
}
