package com.fb.Main;

import com.fb.components.Friendship;
import com.fb.components.User;
import com.fb.components.UserManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class FriendHomeController {
    @FXML
    private Label friendLabel;
    @FXML
    private Button restrictedButton;
    public void restrictFriend(ActionEvent event){
        for(Friendship f : UserManager.users.get(0).getFriends())
        {
            User user = UserManager.getUserByUserName(friendLabel.getText());
            if(user.getId()== f.getFriendId() && UserManager.users.get(0).getId()==f.getUserId())
            {
                f.setType("restricted");
                restrictedButton.setVisible(false);
            }
        }
    }
    public void setFriendLabelData(Friendship friend){
        User user = UserManager.getUserByUserId(friend.getFriendId());
        friendLabel.setText(user.getName());
    }


}
