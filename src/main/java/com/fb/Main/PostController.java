package com.fb.Main;

import com.fb.components.Post;
import com.fb.components.User;
import com.fb.components.UserManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

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
    @FXML
    private HBox comment;
    @FXML
    private HBox commentContainer;
    @FXML
    private Button commentExitButton;
    private long startTime = 0;
    public void  onCommentPressed(MouseEvent event){
        startTime = System.currentTimeMillis();
    }

    public void onCommentReleased(MouseEvent event) {
        if(System.currentTimeMillis() - startTime > 5) {
            commentContainer.setVisible(true);
        }
    }
    public void commentExit(ActionEvent event) {
        commentContainer.setVisible(false);
    }
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