package com.fb.components;

import java.util.ArrayList;

public class Conversation {
    private static int nextId = 1;
    private int id;
    private ArrayList<User> participants;
    private ArrayList<Message> messages;

    public Conversation(User user1, User user2) {
        this.id = nextId++;
        this.participants = new ArrayList<>();
        this.messages = new ArrayList<>();
        participants.add(user1);
        participants.add(user2);
    }

    public void addMessage(Message message) {
        messages.add(message);
    }

    public ArrayList<User> getParticipants() {
        return participants;
    }

    // Other methods for displaying conversation, adding participants, etc.
}
