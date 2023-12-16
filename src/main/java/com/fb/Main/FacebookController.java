package com.fb.Main;

import com.fb.components.Post;
import com.fb.components.User;
import com.fb.components.UserManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class FacebookController implements Initializable {
    @FXML
    private TextField PostTextField;

    @FXML
    private VBox postsContainer;
    ArrayList<Post> posts;
    private Stage stage;
    private Scene scene;
    private Parent root;
    public void SignOut(ActionEvent event){
        try {
            stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            stage.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void WritePost(ActionEvent event){
        User user = UserManager.getUserByEmail(UserManager.users.get(0).getEmail(),"UserInfo.json");
        if (user != null) {
            String postText = PostTextField.getText();
            if (postText != null && !postText.isEmpty()) {
                user.createPost(user.getId(),postText);
                for (int i = 0; i < UserManager.users.size(); i++) {
                    if (UserManager.users.get(i).getEmail().equals(user.getEmail())) {
                        UserManager.users.set(i, user);
                        break;
                    }
                }
                System.out.println("Post added to user " + user.getName());
            } else {
                System.out.println("Post text is null or empty");
            }
        } else {
            System.out.println("User not found");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        posts = (ArrayList<Post>) UserManager.users.get(0).getPosts();
        try {
        for (Post post:posts) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("Post.fxml"));
                VBox vBox =fxmlLoader.load();

            }
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
