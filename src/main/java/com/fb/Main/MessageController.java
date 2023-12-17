package com.fb.Main;

import com.fb.components.Message;
import com.fb.components.Post;
import com.fb.components.UserManager;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class MessageController {
    @FXML
    private Label ChatLabel;

    public void setMessageData (Message message){
        ChatLabel.setText(message.getMessageContent());
    }
}
