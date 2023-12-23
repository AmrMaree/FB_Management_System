package com.fb.Main;

import com.fb.components.Notification;
import com.fb.components.User;
import com.fb.components.UserManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class NotificationController {
    @FXML
    private Label NotificationLabel;
    public void setNotificationData(Notification notification){
        NotificationLabel.setText(notification.getContent());
    }
    public void acceptFriend(ActionEvent event){

    }
}
