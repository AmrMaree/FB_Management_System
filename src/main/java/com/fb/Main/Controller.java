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
        if(userManager.checkLogin(LoginEmailTextField.getText(),LoginPasswordField.getText(),"UserInfo.json")){
            root = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
            stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }
    public void WritePost(ActionEvent event){
        User user = userManager.getUser("UserInfo.json");
        //System.out.println(user.getEmail());
        if(user != null) {
            user.createPost(PostTextField.getText());
            userManager.serialize(user, "UserInfo.json");
            System.out.println("Post added to user "+user.getName());
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
        User user = userManager.createAccount(NameTextField.getText(),EmailTextField.getText(),PasswordField.getText(),gender,myDatePicker.getValue().toString(),RepasswordField.getText());
        if(user != null){
            userManager.serialize(user,"UserInfo.json");
            switchToLogin(event);
        }

    }

}