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
    private Stage stage;
    private Scene scene;
    private Parent root;
    private String gender;

    public void switchToSignUp (ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("SignUp.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
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
        User user = new User(NameTextField.getText(),EmailTextField.getText(),PasswordField.getText(),gender,myDatePicker.getValue());
        System.out.println(user.getGender()+" "+user.getBirthDate());
    }

}