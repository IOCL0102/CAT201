module com.example.cat201_project {
    requires javafx.controls;
    requires javafx.fxml;
    requires json.simple;
    requires core;
    requires qrgen;

    opens com.example.cat201_project to javafx.fxml;
    exports com.example.cat201_project;
}