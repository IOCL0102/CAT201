package com.example.payment_module_cinema_project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.*;

public class SelectPaymentMethod extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(SelectPaymentMethod.class.getResource("selectpayment.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1280, 720);
        scene.getStylesheets().add(getClass().getResource("login.css").toExternalForm());
        stage.setTitle("ROYAL CINEMA!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) throws IOException{

        launch();
    }
}
