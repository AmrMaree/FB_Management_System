package com.fb.Main;

import com.fb.components.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

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
    private RadioButton MaleRadioButton, FemaleRadioButton;
    @FXML
    private TextField LoginEmailTextField;
    @FXML
    private PasswordField LoginPasswordField;
    @FXML
    private HBox loginCredentials;
    @FXML
    private HBox EmailValidation;
    @FXML
    private HBox PasswordValidation;
    @FXML
    private HBox ConfirmValidation;
    @FXML
    private HBox GenderValidation;
    @FXML
    private HBox NameValidation;
    @FXML
    private HBox DateValidation;


    private Stage stage;
    private Scene scene;
    private Parent root;
    private String gender;

    public void CloseWindow(ActionEvent event) {
        System.exit(0);
    }

    public void switchToSignUp(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("SignUp.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToLogin(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Main.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToHome(ActionEvent event) throws IOException {
        if (UserManager.checkLogin(LoginEmailTextField.getText(), LoginPasswordField.getText())) {
            root = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } else {
            loginCredentials.setVisible(true);
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
        User user = UserManager.createAccount(UserManager.getGreatestUserId()+1,NameTextField.getText(),EmailTextField.getText(),PasswordField.getText(),gender,myDatePicker.getValue().toString(),RepasswordField.getText(),EmailValidation,PasswordValidation,ConfirmValidation,GenderValidation,NameValidation,DateValidation);
        if(user != null){
            switchToLogin(event);
        }
    }
}