package com.fb.Main;

import com.fb.components.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {
    @FXML
    private TextField NameTextField;
    @FXML
    private TextField EmailTextField;
    @FXML
    private PasswordField PasswordField;
    @FXML
    private PasswordField RepasswordField;
    @FXML
    private Button SignUpButton;
    @FXML
    private DatePicker myDatePicker;
    @FXML
    private RadioButton MaleRadioButton,FemaleRadioButton;
    @FXML
    private TextField LoginEmailTextField;
    @FXML
    private PasswordField LoginPasswordField;
    @FXML
    private TextField PostTextField;
    @FXML
    private Button CloseButton;
    private Stage stage;
    private Scene scene;
    private Parent root;
    private String gender;
    UserManager userManager = new UserManager();
    public void switchToSignUp (ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("SignUp.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToLogin (ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Main.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToHome (ActionEvent event) throws IOException {
        if(UserManager.checkLogin(LoginEmailTextField.getText(),LoginPasswordField.getText(),"UserInfo.json")){
            root = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
            stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }
    public void SignOut(ActionEvent event){
        try {
            stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            stage.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void WritePost(ActionEvent event){
        User user = UserManager.getUserByEmail(UserManager.users.get(0).getEmail(),"UserInfo.json");
        if (user != null) {
            String postText = PostTextField.getText();
            if (postText != null && !postText.isEmpty()) {
                user.createPost(user.getId(),postText);
                for (int i = 0; i < UserManager.users.size(); i++) {
                    if (UserManager.users.get(i).getEmail().equals(user.getEmail())) {
                        UserManager.users.set(i, user);
                        break;
                    }
                }
                System.out.println("Post added to user " + user.getName());
            } else {
                System.out.println("Post text is null or empty");
            }
        } else {
            System.out.println("User not found");
        }
    }
    public void getGender(ActionEvent event){
        if(MaleRadioButton.isSelected()){
            gender = "M";
        }
        else{
            gender = "F";
        }
    }
    public void SignUp(ActionEvent event) throws IOException {
        User user = userManager.createAccount(UserManager.getGreatestUserId()+1,NameTextField.getText(),EmailTextField.getText(),PasswordField.getText(),gender,myDatePicker.getValue().toString(),RepasswordField.getText());
        if(user != null){
            switchToLogin(event);
        }
    }
}