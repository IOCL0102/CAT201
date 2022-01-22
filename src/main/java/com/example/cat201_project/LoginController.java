package com.example.cat201_project;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static com.example.cat201_project.JsonEditor.getJSONObject;

public class LoginController implements Initializable{

    @FXML private TextField userIDTextField;
    @FXML private TextField userPwTextField;

    @FXML private Text errorInvalidAccMessage;
    @FXML private Button signUpBttn;
    @FXML private Label emptyTextFieldErrMsg;


    private JSONArray userData = null;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) throws NullPointerException {
        JSONObject userInfo = JsonEditor.getJSONObject("userInformation.json");
        userData = (JSONArray) userInfo.get("userInfo");
        emptyTextFieldErrMsg.setVisible(false);
        errorInvalidAccMessage.setVisible(false);
        JsonEditor.setUserArrayIndex(-1);
        System.out.println(JsonEditor.getUserArrayIndex());
    }

    public void validateLogin(ActionEvent e){
        String userID = userIDTextField.getText();
        String userPw = userPwTextField.getText();
        boolean isValidAcc = false;

        // get all the data from userInformation.json
        for(int i = 0; i < userData.size(); i++){
            String tempUserID = (((JSONObject)userData.get(i)).get("userID")).toString();
            String tempUserPw = (((JSONObject)userData.get(i)).get("password")).toString();

            if( tempUserID.equals(userID) && tempUserPw.equals(userPw)) {
                System.out.println("you have logined");
                JsonEditor.setUserArrayIndex(i);
                System.out.println("your index is "+ JsonEditor.getUserArrayIndex());
                isValidAcc = true;
                // LATER TO BE ADDED
                // CHANGE SCENE TO MAIN PAGE AFTER LOGIN
            }
        }

        // if no account is found on userInformation.json , display error message
        if(userID.isEmpty() || userPw.isEmpty()){

            if(errorInvalidAccMessage.isVisible())
                errorInvalidAccMessage.setVisible(false);

            emptyTextFieldErrMsg.setText("UserID or Password cannot be emtpy");
            emptyTextFieldErrMsg.setVisible(true);
            FadeTransition fadeMessage = new FadeTransition(Duration.millis(4000), emptyTextFieldErrMsg);
            fadeMessage.setFromValue(1);
            fadeMessage.setToValue(0);
            fadeMessage.play();
        }
        else if(isValidAcc == false){
            if(emptyTextFieldErrMsg.isVisible())
                emptyTextFieldErrMsg.setVisible(false);

            errorInvalidAccMessage.setVisible(true);
            FadeTransition fadeMessage = new FadeTransition(Duration.millis(4000), errorInvalidAccMessage);
            fadeMessage.setFromValue(1);
            fadeMessage.setToValue(0);
            fadeMessage.play();
        }
    }

    public void changeToSignUpScene(ActionEvent e) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("registerAccount.fxml"));
        Stage stage = (Stage) signUpBttn.getScene().getWindow();
        stage.setScene(new Scene(fxmlLoader.load(), 1280, 720));
        stage.show();
    }

    public void changeToForgotPwScene(ActionEvent e) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("forgotPw.fxml"));
        Stage stage = (Stage) signUpBttn.getScene().getWindow();
        stage.setScene(new Scene(fxmlLoader.load(), 1280, 720));
        stage.show();
    }
}