package com.example.cat201_project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RegisterAccountController implements Initializable{

    @FXML private Label emailErrLabel;
    @FXML private Label pwNotSameErrLabel;
    @FXML private Label userIDErrLabel;
    @FXML private Label userNameErrLabel;
    @FXML private Label signUpSuccessMsg;
    
    @FXML private TextField userIDTextField;
    @FXML private TextField userNameTextField;
    @FXML private TextField emailTextField;
    @FXML private PasswordField pwTextField;
    @FXML private PasswordField reEnterPwTextField;

    @FXML private Button returnToLoginBttn;
    @FXML private Button signUpBttn;

    private JSONArray userData = null;
    private String userEmail,userID,userName,userPw,userReEnterPw;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // set not visible to all the error message
        setErrLabelVisibility(false);
        System.out.println(JsonEditor.getUserArrayIndex());
        // get all the data from userInformation.json
        JSONObject userInfo = JsonEditor.getJSONObject("userInformation.json");
        userData = (JSONArray) userInfo.get("userInfo");
    }

    public void signUp(ActionEvent e){
        validateSignUp();

        // If all error message is NOT visible, then execute
        // All error message not visible indicates all data is correct
        if(!isAllLabelIsVisible()){
            // add data into json file
            JSONObject newUserAcc = new JSONObject();
            newUserAcc.put("userID",userID);
            newUserAcc.put("userName",userName);
            newUserAcc.put("password",userPw);
            newUserAcc.put("email",userEmail);

            userData.add(newUserAcc);
            System.out.println(userData.toString());
            System.out.println("SUCCESSFULLY SIGN UP !!");
        }
    }

    public void validateSignUp (){
        setErrLabelVisibility(false);
        userEmail = emailTextField.getText();
        userID = userIDTextField.getText();
        userName = userNameTextField.getText();
        userPw = pwTextField.getText();
        userReEnterPw = reEnterPwTextField.getText();
        boolean isValidInfoProvided = false;

        // print error message if text field is empty
        if(userEmail.isEmpty()){
            emailErrLabel.setText("Email cannot be empty");
            emailErrLabel.setVisible(true);
        }
        if(userPw.isEmpty() || userReEnterPw.isEmpty()){
            pwNotSameErrLabel.setText("Password cannot be empty");
            pwNotSameErrLabel.setVisible(true);
        }
        if(userID.isEmpty()){
            userIDErrLabel.setText("UserID cannot be empty");
            userIDErrLabel.setVisible(true);
        }
        if(userName.isEmpty()){
            userNameErrLabel.setText("UserName cannot be empty");
            userNameErrLabel.setVisible(true);
        }


        String tempUserEmail, tempUserID, tempUserName;
        // compare the text field value with data in userInformation.json
        // if is existed in userInformation.json, display error message

        for(int i = 0; i < userData.size(); i++){
            tempUserEmail = (((JSONObject)userData.get(i)).get("email")).toString();
            tempUserID = (((JSONObject)userData.get(i)).get("userID")).toString();
            tempUserName = (((JSONObject)userData.get(i)).get("userName")).toString();

            if(tempUserEmail.equals(userEmail)){
                emailErrLabel.setText("This email is already existed, please use another email");
                emailErrLabel.setVisible(true);
            }
            if(tempUserID.equals(userID)){
                userIDErrLabel.setText("This userID is already existed, please use another userID");
                userIDErrLabel.setVisible(true);
            }
            if(tempUserName.equals(userName)){
                userNameErrLabel.setText("This userName is already existed, please use another name");
                userNameErrLabel.setVisible(true);
            }
        }
    }

    public void changeToLoginScene(ActionEvent e) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login-view.fxml"));
        Stage stage = (Stage) returnToLoginBttn.getScene().getWindow();
        stage.setScene(new Scene(fxmlLoader.load(), 1280, 720));
        stage.show();
    }

    private void setErrLabelVisibility(boolean isVisible){
        emailErrLabel.setVisible(isVisible);
        pwNotSameErrLabel.setVisible(isVisible);
        userIDErrLabel.setVisible(isVisible);
        userNameErrLabel.setVisible(isVisible);
        signUpSuccessMsg.setVisible(isVisible);
    }

    private boolean isAllLabelIsVisible(){
        return (emailErrLabel.isVisible() && pwNotSameErrLabel.isVisible() && userIDErrLabel.isVisible() && userNameErrLabel.isVisible());
    }
}
