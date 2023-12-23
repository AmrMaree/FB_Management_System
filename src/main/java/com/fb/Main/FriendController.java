package com.fb.Main;

import com.fb.components.Friendship;
import com.fb.components.User;
import com.fb.components.UserManager;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class FriendController {
    @FXML
    private Label friendLabel;
    public void setFriendLabelData(Friendship friend){
        User user = UserManager.getUserByUserId(friend.getFriendId());
        friendLabel.setText(user.getName());
    }
}
