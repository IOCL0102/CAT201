package com.example.cat201_project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class MyProfileController implements Initializable {

    @FXML private Text backToMainPageText;
    @FXML private Button logOutBttn;
    @FXML private Button returnToMainPageBttn;
    @FXML private Button viewBookedTicketBttn;
    @FXML private Button viewProfileBttn;
    @FXML private Label userEmailLabel;
    @FXML private Label userIDLabel;
    @FXML private Label userNameLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            JSONObject userData = JsonEditor.getCurrentUserInfo();

            String userName = (String) userData.get("userName");;
            String userEmail = (String) userData.get("email");
            String userID = (String) userData.get("userID");

            userNameLabel.setText(userName);
            userEmailLabel.setText(userEmail);
            userIDLabel.setText(userID);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }


    public void returnToLogOutPage(ActionEvent e) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login-view.fxml"));
        Stage stage = (Stage) logOutBttn.getScene().getWindow();
        stage.setScene(new Scene(fxmlLoader.load(), 1280, 720));
        stage.show();
    }

    public void returnToProfilePage(ActionEvent e) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("myProfile.fxml"));
        Stage stage = (Stage) viewProfileBttn.getScene().getWindow();
        stage.setScene(new Scene(fxmlLoader.load(), 1280, 720));
        stage.show();
    }

    public void returnToViewTicketPage(ActionEvent e) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("bookedTicket.fxml"));
        Stage stage = (Stage) viewBookedTicketBttn.getScene().getWindow();
        stage.setScene(new Scene(fxmlLoader.load(), 1280, 720));
        stage.show();
    }

    public void returnToHomeMovieScene(ActionEvent e) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("home-movie.fxml"));
        Stage stage = (Stage) returnToMainPageBttn.getScene().getWindow();
        stage.setScene(new Scene(fxmlLoader.load(), 1280, 720));
        stage.show();
    }



}
