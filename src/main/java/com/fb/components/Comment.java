package com.fb.components;
import java.util.ArrayList;
public class Comment extends SocialContent{
    private static int nextId = 1;
    final private int postId;
    private ArrayList<Reply> replies;
    private ArrayList<Integer> likers;
    private int NumberOfLikes = 0;
    public Comment(int userId, int postId, String content) {
        super(userId , postId, content);
        this.postId = postId;
        this.replies = new ArrayList<>();
        this.likers = new ArrayList<>();
    }
    @Override
    public void addLiker(int likerId) {
        likers.add(likerId);
    }
    public void addReply(int id ,int replierId, String text) {
        replies.add(new Reply(id,replierId, text));
    }
}