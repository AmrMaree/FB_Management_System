package com.fb.Main;

import com.fb.components.Post;
import com.fb.components.Privacy;
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
                int postId;
                if(!user.getPosts().isEmpty()){
                    postId = user.getPosts().get(user.getPosts().size()-1).getId() + 1;
                }
                else{
                    postId = 1;
                }
                user.createPost(postId, postText, Privacy.PUBLIC);
                for (int i = 0; i < UserManager.users.size(); i++) {
                    if (UserManager.users.get(i).getEmail().equals(user.getEmail())) {
                        UserManager.users.set(i, user);
                        break;
                    }
                }
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("Post.fxml"));
                    Parent postNode = fxmlLoader.load();
                    PostController postController = fxmlLoader.getController();
                    postController.setPostData(UserManager.users.get(0).getPosts().get(postId - 1));
                    postsContainer.getChildren().add(1, postNode);
                }
                catch (IOException e) {
                    throw new RuntimeException(e);
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
            if(posts != null){
                for (Post post:posts) {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("Post.fxml"));
                    Parent postNode = fxmlLoader.load();
                    PostController postController = fxmlLoader.getController();
                    postController.setPostData(post);
                    postsContainer.getChildren().add(postNode);
                }
            }
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
