package com.fb.Main;

import com.fb.components.Post;
import com.fb.components.User;
import com.fb.components.UserManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ProfilePageController{
    @FXML
    private Label UserNameLabel, BirthdateLabel, GenderLabel;
    @FXML
    private VBox ProfilePostContainer;
    ArrayList<Post> posts;
    public void setProfileData(User user) {
        UserNameLabel.setText(user.getName());
        BirthdateLabel.setText(user.getBirthDate());
        GenderLabel.setText((user.getGender().equals("M")? "Male" :"Female"));
        posts = (ArrayList<Post>) user.getPosts();
        try {
            if(posts != null){
                for (Post post:posts) {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("Post.fxml"));
                    Parent postNode = fxmlLoader.load();
                    PostController postController = fxmlLoader.getController();
                    postController.setPostData(post);
                    ProfilePostContainer.getChildren().add(postNode);
                }
            }
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void friendRequest(ActionEvent event) {
        User user = UserManager.getUserByUserName(UserNameLabel.getText());
        user.receiveNotification(user);
    }
}