package com.example.cat201_project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HomeMovie extends Application
{
    @Override
    public void start(Stage stage) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(com.example.cat201_project.HomeMovie.class.getResource("home-movie.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1280, 720);
        scene.getStylesheets().add(getClass().getResource("HomeMovie.css").toExternalForm());
        stage.setTitle("ROYAL CINEMA!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args)
    {
        launch();


    }
}