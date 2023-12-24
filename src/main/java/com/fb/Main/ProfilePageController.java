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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;

public class ProfilePageController implements Initializable{
    @FXML
    private Label UserNameLabel, BirthdateLabel, GenderLabel,NotificationLabel;
    @FXML
    private VBox ProfilePostContainer,friendsContainer,mutualFriendsContainer,chatContainer;
    @FXML
    private TextField SearchTextField1;
    @FXML
    private Button friendRequestButton;
    ArrayList<Post> posts;
    private ArrayList<String> usersName = new ArrayList<>();
    String SearchedUser;
    private Stage stage;
    private Scene scene;
    private Parent root;
    private void getUserName(){
        for(User user:UserManager.users){
            usersName.add(user.getName());
        }
    }
    public void setProfileData(User user) {
        UserNameLabel.setText(user.getName());
        BirthdateLabel.setText(user.getBirthDate());
        GenderLabel.setText((user.getGender().equals("M")? "Male" :"Female"));
       if (UserManager.users.get(0).getFriends()!= null) {
               for (Friendship f : UserManager.users.get(0).getFriends()) {
                   if (f.getFriendId() == user.getId()) {
                       friendRequestButton.setVisible(false);
                   }
               }
       }
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
        try {
            if(user.getFriends() != null){
                for(Friendship f : user.getFriends()){
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("friend.fxml"));
                    Parent friendNode = fxmlLoader.load();
                    FriendController friendController = fxmlLoader.getController();
                    friendController.setFriendLabelData(f);
                    friendsContainer.getChildren().add(friendNode);
                }
            }
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            if(user.getFriends() != null){
                for(Friendship f : user.getFriends()){
                    if (UserManager.users.get(0).getFriends()!= null) {
                        for (Friendship f2 : UserManager.users.get(0).getFriends()) {
                            if (f.getFriendId() == f2.getFriendId()) {
                                FXMLLoader fxmlLoader = new FXMLLoader();
                                fxmlLoader.setLocation(getClass().getResource("friend.fxml"));
                                Parent friendNode = fxmlLoader.load();
                                FriendController friendController = fxmlLoader.getController();
                                friendController.setFriendLabelData(f);
                                mutualFriendsContainer.getChildren().add(friendNode);
                                break;
                            }
                        }
                    }
                }
            }
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            if(UserManager.users.get(0).getFriends() != null){
                for(Friendship f : UserManager.users.get(0).getFriends()){
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("Chat.fxml"));
                    Parent chatNode = fxmlLoader.load();
                    ChatController chatController = fxmlLoader.getController();
                    chatController.setChatData(f);
                    chatContainer.getChildren().add(chatNode);
                }
            }
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void friendRequest(ActionEvent event) {
        User user = UserManager.getUserByUserName(UserNameLabel.getText());
        Notification notification = new Notification(UserManager.users.get(0).getName() +" sent you a friend request", UserManager.users.get(0).getId(), user.getId());
        user.receiveNotification(user, notification);
        friendRequestButton.setVisible(false);
    }
    public void switchToHome (ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToProfilePage(ActionEvent event) {
        if (SearchedUser != null){
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("ProfilePage.fxml"));
                root = loader.load();
                if (loader.getController() instanceof ProfilePageController) {
                    ProfilePageController profilePageController = loader.getController();
                    User ProfileUser = UserManager.getUserByUserName(SearchedUser);
                    profilePageController.setProfileData(ProfileUser);
                }
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        getUserName();
        Set<String> _usersName = new HashSet<>(usersName);
        AutoCompletionBinding<String> autoComplete = TextFields.bindAutoCompletion(SearchTextField1, _usersName);
        autoComplete.setOnAutoCompleted(event -> {
            String selectedValue = event.getCompletion();
            SearchedUser = selectedValue;
        });
    }
}