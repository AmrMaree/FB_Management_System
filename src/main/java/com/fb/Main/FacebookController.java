package com.fb.Main;

import com.fb.components.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.controlsfx.control.textfield.TextFields;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;

public class FacebookController implements Initializable {
    @FXML
    private TextField PostTextField;
    @FXML
    private VBox postsContainer;
    @FXML
    private RadioButton PublicRadioButton, FriendsRadioButton;
    @FXML
    private TextField SearchTextField;
    ArrayList<Post> posts;
    private Stage stage;
    private Scene scene;
    private Parent root;
    private String privacy;
    private ArrayList<String> usersName = new ArrayList<>();
    private void getUserName(){
        for(User user:UserManager.users){
            usersName.add(user.getName());
        }
    }
    public void SignOut(ActionEvent event){
        try {
            stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            stage.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void CloseWindow (ActionEvent event)
    {
     System.exit(0);
    }
    public void OpenChat(ActionEvent event) {
        ArrayList<Conversation> conversations = (ArrayList<Conversation>) UserManager.users.get(0).getConversations();
        ArrayList<Conversation> conversations2 = (ArrayList<Conversation>) UserManager.users.get(1).getConversations();
        if (conversations == null) {
            conversations = new ArrayList<>();
            UserManager.users.get(0).setConversations(conversations);
        }
        if (conversations2 == null) {
            conversations2 = new ArrayList<>();
            UserManager.users.get(1).setConversations(conversations2);
        }
        int conversationId;
        if(!UserManager.users.get(0).getConversations().isEmpty()){
            conversationId = UserManager.users.get(0).getConversations().get(UserManager.users.get(0).getConversations().size()-1).getId() + 1;
        }
        else{
            conversationId = 1;
        }
        int conversationId2;
        if(!UserManager.users.get(1).getConversations().isEmpty()){
            conversationId2 = UserManager.users.get(1).getConversations().get(UserManager.users.get(1).getConversations().size()-1).getId() + 1;
        }
        else{
            conversationId2 = 1;
        }
        UserManager.users.get(0).createConversation(conversationId, UserManager.users.get(0).getId(), UserManager.users.get(1).getId());
        UserManager.users.get(1).createConversation(conversationId2, UserManager.users.get(0).getId(), UserManager.users.get(1).getId());
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Conversation.fxml"));
            Parent root = loader.load();
            Stage newStage = new Stage();
            newStage.setScene(new Scene(root));
            Image icon = new Image(getClass().getResourceAsStream("/Images/buzzIcon.png"));
            newStage.setResizable(false);
            newStage.getIcons().add(icon);
            newStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void getPrivacy(ActionEvent event){
        if(PublicRadioButton.isSelected()){
            privacy = "public";
        }else if (FriendsRadioButton.isSelected()){
            privacy = "friends";
        }
    }
    public void WritePost(ActionEvent event){
        User user = UserManager.users.get(0);
        if (user != null) {
            String postText = PostTextField.getText();
            if (postText != null && !postText.isEmpty()) {
                int postId;
                if(!user.getPosts().isEmpty()){
                    postId = user.getPosts().get(0).getId() + 1;
                }
                else{
                    postId = 1;
                }
                user.createPost(postId, postText, privacy);
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
                    postController.setPostData(UserManager.users.get(0).getPosts().get(0));
                    postsContainer.getChildren().add(1,postNode);
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
        getUserName();
        Set<String> _usersName = new HashSet<>(usersName);
        TextFields.bindAutoCompletion(SearchTextField,_usersName);
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
