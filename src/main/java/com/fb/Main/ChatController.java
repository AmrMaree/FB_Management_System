package com.fb.Main;

import com.fb.components.Conversation;
import com.fb.components.Friendship;
import com.fb.components.User;
import com.fb.components.UserManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class ChatController {
    @FXML
    private Label chatLabel;
    public void setChatData(Friendship friend){
        User user = UserManager.getUserByUserId(friend.getFriendId());
        chatLabel.setText(user.getName());
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
}
