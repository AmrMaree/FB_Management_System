package com.fb.Main;

import com.fb.components.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;

import java.io.IOException;

public class MessageController {
    @FXML
    private Label ChatLabel;

    public void setMessageData (Message message){
        ChatLabel.setText(message.getMessageContent());
    }
}
