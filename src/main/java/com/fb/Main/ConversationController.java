package com.fb.Main;

import com.fb.components.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ConversationController implements Initializable {

    @FXML
    private TextField ChatTextField;
    @FXML
    private VBox MessagesContainer;
    @FXML
    private Label UsernameLabel;
    ArrayList<Message> Messages;
    public void writeMessage(ActionEvent event){
        User user = UserManager.users.get(0);
        User recipient = UserManager.users.get(1);
        if (user != null && recipient != null) {
            String MessageText = ChatTextField.getText();
            ChatTextField.clear();
            if (MessageText != null && !MessageText.isEmpty()) {
                int MessageId;
                MessageId = user.getConversations().get(0).getMessages().isEmpty() ?
                        1 : user.getConversations().get(0).getMessages().get(user.getConversations().get(0).getMessages().size() - 1).getId() + 1;
                user.getConversations().get(0).createMessage(MessageId ,user.getId(),recipient.getId(),MessageText);
                recipient.getConversations().get(0).createMessage(MessageId ,user.getId(),recipient.getId(),MessageText);
                for (int i = 0; i < UserManager.users.size(); i++) {
                    if (UserManager.users.get(i).getEmail().equals(user.getEmail())) {
                        UserManager.users.set(i, user);
                        break;
                    }
                }
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("ChatText.fxml"));
                    Parent messageNode = fxmlLoader.load();
                    MessageController messageController = fxmlLoader.getController();
                    messageController.setMessageData(user.getConversations().get(0).getMessages().get(user.getConversations().get(0).getMessages().size() - 1),true);
                    MessagesContainer.getChildren().add(messageNode);
                }
                catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } else {
                System.out.println("Post text is null or empty");
            }
        } else {
            System.out.println("User not found");
        }
    }
    public void CloseWindow (ActionEvent event)
    {
        System.exit(0);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Messages = (ArrayList<Message>) UserManager.users.get(0).getConversations().get(0).getMessages();
        UsernameLabel.setText(UserManager.users.get(1).getName());
        try {
            if(Messages != null){
                for (Message message : Messages) {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("ChatText.fxml"));
                    Parent messageNode = fxmlLoader.load();
                    MessageController messageController = fxmlLoader.getController();
                    if (message.getSenderId() == UserManager.users.get(0).getId()) {
                        messageController.setMessageData(message, true);
                    } else {
                        messageController.setMessageData(message, false);
                    }
                    MessagesContainer.getChildren().add(messageNode);
                }
            }
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}