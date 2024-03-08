package com.fb.Main;

import com.fb.components.Friendship;
import com.fb.components.User;
import com.fb.components.UserManager;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableListValue;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.stage.Stage;

import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class TaggedFriendsController implements Initializable {

    @FXML
    private ListView<String> listView;
    @FXML
    private Label TaggedFriends;
    public String Tagged;
    public void SignOut(ActionEvent event){
        try {
            Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            stage.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ArrayList<String> friendsToTag = new ArrayList<>();
        User user;
        for (Friendship f : UserManager.users.get(0).getFriends()) {
            user = UserManager.getUserByUserId(f.getFriendId());
            friendsToTag.add(user.getName());
        }
        listView.getItems().addAll(friendsToTag);
        listView.getSelectionModel().getSelectedItems().addListener((ListChangeListener.Change<? extends String> change) -> {
            ObservableList<? extends String> selectedItems = listView.getSelectionModel().getSelectedItems();
            StringBuilder selectedFriends = new StringBuilder();
            for (String selectedItem : selectedItems) {
                selectedFriends.append(selectedItem).append(", ");
            }

            String result = selectedFriends.length() > 0 ?
                    selectedFriends.substring(0, selectedFriends.length() - 2) :
                    selectedFriends.toString();

            TaggedFriends.setText(result);
            Tagged = result;
            });
    }
}
