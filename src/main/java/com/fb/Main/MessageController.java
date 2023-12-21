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
            ChatLabel.setStyle( "-fx-background-color: #7d55bc;"+"-fx-background-radius: 20;");
        } else {
            chatHbox.setStyle("-fx-alignment: CENTER-LEFT;");
        }
    }


}
