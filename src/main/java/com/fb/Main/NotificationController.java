package com.fb.Main;

import com.fb.components.Friendship;
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
    public void acceptFriend (ActionEvent event){
        int spaceIndex = NotificationLabel.getText().indexOf(' ');
        String userFriendName = NotificationLabel.getText().substring(0, spaceIndex).trim();
        User userFriend = UserManager.getUserByUserName(userFriendName);
        Friendship friendship = new Friendship(UserManager.users.get(0).getId(),userFriend.getId(),"regular");
        Friendship friendship2 = new Friendship(userFriend.getId(),UserManager.users.get(0).getId(),"regular");
        UserManager.users.get(0).addFriend(friendship);
        userFriend.addFriend(friendship2);
    }
}
