package com.fb.Main;

import com.fb.components.Post;
import com.fb.components.User;
import com.fb.components.UserManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class PostController {
    @FXML
    private Label UsernameLabel;
    @FXML
    private Label PostCaption;
    @FXML
    private Label likes;

    public void addLike(ActionEvent event){
        for(Post post:UserManager.users.get(0).getPosts()){
            if(post.getContent().equals(PostCaption.getText())){
                post.addLiker(UserManager.users.get(0).getId());
                //likes.setText(String.valueOf(post.getNumberOfLikes()));
            }
        }
    }
    public void setPostData (Post post){
        UsernameLabel.setText(UserManager.users.get(0).getName());
        PostCaption.setText(post.getContent());
    }
}