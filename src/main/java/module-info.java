module com.example.fb_management_system {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;

    opens com.fb.components to com.google.gson;
    opens com.fb.Main to javafx.fxml;
    exports com.fb.Main;
}