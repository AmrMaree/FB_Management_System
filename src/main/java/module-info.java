module com.example.fb_management_system {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;
    requires org.controlsfx.controls;

    opens com.fb.components to com.google.gson;
    opens com.fb.Main to javafx.fxml, org.controlsfx.controls;
    exports com.fb.Main;
}
