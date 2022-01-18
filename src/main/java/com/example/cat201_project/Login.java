package com.example.cat201_project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class Login extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Login.class.getResource("login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1280, 720);
        scene.getStylesheets().add(getClass().getResource("login.css").toExternalForm());
        stage.setTitle("ROYAL CINEMA!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) { launch();}
}

