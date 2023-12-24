package com.fb.components;

abstract public class SocialContent {
    private final int id;
    private final int userId;
    private String content;

    public SocialContent(int id, int userId, String content) {
        this.id = id;
        this.userId = userId;
        this.content = content;
    }
    abstract public void addLiker(int liker);
    public int getId() {
        return id;
    }
    public int getUserId()
    {
        return userId;
    }
    public String getContent()
    {
        return content;
    }
}
