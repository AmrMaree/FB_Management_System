package com.fb.Main;

import com.fb.components.User;
import com.fb.components.UserManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
            Scene scene = new Scene(root);
           //String css = this.getClass().getResource("C:\\Users\\PC\\IdeaProjects\\FB_Management_System1\\src\\main\\resources\\fb.css").toExternalForm();
            //scene.getStylesheets().add(css);
            Image icon = new Image(getClass().getResourceAsStream("/Images/buzzIcon.png"));
            stage.setResizable(false);
            stage.getIcons().add(icon);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        launch();
        User user = UserManager.getUserByEmail(UserManager.users.get(0).getEmail(),"UserInfo.json");
        System.out.println(user.getPosts());
        UserManager.serialize(user, "UserInfo.json");
    }
}