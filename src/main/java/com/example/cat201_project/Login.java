package com.example.cat201_project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Login extends Application {

    public static int userArrayIndex = -1;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Login.class.getResource("login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1280, 720);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        JSONObject userInfo = getJSONObject("userInformation.json");
        JSONArray userData = (JSONArray) userInfo.get("userInfo");

        for(int i = 0; i < userData.size(); i++){
            String x = (((JSONObject)userData.get(i)).get("userID")).toString();

            if( x.equals("test2")) {
                System.out.println("you have logined");
                System.out.println("your index is "+ i);
                userArrayIndex = i+1;
            }
        }

        launch();
    }


    public static JSONObject getJSONObject(String fileName) {
        try {
            FileReader reader = new FileReader("././././JSON_File/" + fileName);
            JSONParser jsonParser = new JSONParser();
            Object obj = jsonParser.parse(reader);
            return (JSONObject) obj;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}