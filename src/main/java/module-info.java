module com.example.cat201_project {
    requires javafx.controls;
    requires javafx.fxml;
    requires json.simple;
    requires core;
    requires javase;

    requires org.kordamp.ikonli.javafx;
    requires json.simple;
    requires commons.io;
    requires javax.mail.api;
    requires activation;


    opens com.example.cat201_project to javafx.fxml;
    exports com.example.cat201_project;
}