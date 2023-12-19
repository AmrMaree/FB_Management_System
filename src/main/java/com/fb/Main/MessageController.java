package com.fb.Main;

import com.fb.components.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

import java.io.IOException;

public class MessageController {
    @FXML
    private Label ChatLabel;
    @FXML
    private HBox chatHbox;

    public void setMessageData(Message message, boolean isCurrentUser) {
        ChatLabel.setText(message.getMessageContent());

        if (isCurrentUser) {
            chatHbox.setStyle("-fx-alignment: CENTER-RIGHT;");
        } else {
            chatHbox.setStyle("-fx-alignment: CENTER-LEFT;");
            ChatLabel.setStyle( "-fx-background-color: #5A5A5A;"+"-fx-background-radius: 20;");
        }
    }


}
