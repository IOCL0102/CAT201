module com.example.cat201_project {
    requires javafx.controls;
    requires javafx.fxml;
    requires json.simple;
    requires core;
    requires qrgen;
    requires org.kordamp.ikonli.javafx;
    requires commons.io;
    requires javax.mail.api;
    requires activation;


    opens com.example.cat201_project to javafx.fxml;
    exports com.example.cat201_project;
}