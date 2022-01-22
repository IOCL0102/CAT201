package com.example.cat201_project;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.json.simple.parser.ParseException;

import javax.mail.MessagingException;
import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class ForgotPwController implements Initializable {

    @FXML private Button changePwBttn;
    @FXML private Button returnToLoginBttn;
    @FXML private Button userIDCheckBttn;
    @FXML private Button pinNumRequestBttn;
    @FXML private Label userIDErrLabel;
    @FXML private Label chgPwSuccessMsg;
    @FXML private Label pinNumErrLabel;
    @FXML private Label newPwErrLabel;
    @FXML private TextField pinNumTextField;
    @FXML private TextField userIDTextField;
    @FXML private PasswordField newPwTextField;
    private String pin = null;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setAllLabelVisibility(false);   // set all label to not visible
    }

    public void requestPinNum(ActionEvent e) throws MessagingException, IOException, ParseException {
        boolean isEmpty = isUserIDTextFieldEmpty();
        if(!isEmpty) {
            checkIDExist(new ActionEvent());
            if(userIDErrLabel.getText().equals("User exists")) {
                pinNumErrLabel.setVisible(false);
                String userID = userIDTextField.getText();
                String pin = getSingleCode();
                this.pin = pin;
                String email = JsonEditor.getEmailBasedOnUserID("userInformation.json",userIDTextField.getText());
                MailSender.sendSingleCodeWithMail(email, pin);
            }
            else{
                pinNumErrLabel.setText("UserID Incorrect");
                pinNumErrLabel.setVisible(true);
            }
        }
        else{
            pinNumErrLabel.setText("Please insert UserID ");
            pinNumErrLabel.setVisible(true);
        }
    }

    public void changeToLoginScene(ActionEvent e) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login-view.fxml"));
        Stage stage = (Stage) returnToLoginBttn.getScene().getWindow();
        stage.setScene(new Scene(fxmlLoader.load(), 1280, 720));
        stage.show();
    }

    public void changePassword(ActionEvent e) throws IOException, ParseException {
        setAllLabelVisibility(false);
        String pinNum = pinNumTextField.getText();
        String newPw = newPwTextField.getText();
        String userID = userIDTextField.getText();
        System.out.println(this.pin);
        // if text field is empty
        if(pinNum.isEmpty()){
            pinNumErrLabel.setText("Pin number cannot be empty");
            pinNumErrLabel.setVisible(true);
        }

        if(isUserIDTextFieldEmpty()){
            userIDErrLabel.setText("UserID cannot be empty");
            userIDErrLabel.setVisible(true);
        }

        if(newPw.isEmpty()){
            newPwErrLabel.setVisible(true);
        }

        // if pin number does not match
        if(this.pin != null)
            if(!(this.pin.equals(pinNum))){
                pinNumErrLabel.setText("Pin number does not match");
                pinNumErrLabel.setVisible(true);
            }

        checkIDExist(new ActionEvent());

        if((userIDErrLabel.getText().equals(("User exists"))
                || !(userIDErrLabel.isVisible()))
                && !pinNumErrLabel.isVisible()
                && !newPwErrLabel.isVisible())
        {
            userIDErrLabel.setVisible(false);
            setAllTextFieldEmpty();
            JsonEditor.updatePassword("userInformation.json", userID,newPw);
            chgPwSuccessMsg.setVisible(true);

            FadeTransition fadeMessage = new FadeTransition(Duration.millis(7000), (Node) chgPwSuccessMsg);
            fadeMessage.setFromValue(1);
            fadeMessage.setToValue(0);
            fadeMessage.play();
        }

    }

    public static String getSingleCode(){
        Random random = new Random();
        int singleCode = random.nextInt(999999);
        return(String.format("%06d", singleCode));
    }

    public void checkIDExist(ActionEvent e) throws IOException, ParseException {
        userIDErrLabel.setStyle("-fx-text-fill : #ff3c3c;");
        if(!isUserIDTextFieldEmpty()){
            String tempUserID = userIDTextField.getText();
            boolean isExist = JsonEditor.isExistInFile("userInformation.json","userID",tempUserID);

            if(isExist){
                userIDErrLabel.setText("User exists");
                userIDErrLabel.setStyle("-fx-text-fill : #5fff5f;");
            }

            else{
                userIDErrLabel.setText("UserID does not exists, please try again");
            }

            userIDErrLabel.setVisible(true);
        }
    }
    private boolean isUserIDTextFieldEmpty(){
        if (userIDTextField.getText().isEmpty()){
            userIDErrLabel.setText("UserID cannot be empty");
            userIDErrLabel.setVisible(true);
            return true;
        }
        return false;
    }

    private void setAllLabelVisibility(boolean isVisible){
        userIDErrLabel.setVisible(isVisible);
        chgPwSuccessMsg.setVisible(isVisible);
        pinNumErrLabel.setVisible(isVisible);
        newPwErrLabel.setVisible(isVisible);
    }

    private void setAllTextFieldEmpty(){
        newPwTextField.clear();
        pinNumTextField.clear();
        userIDTextField.clear();
    }

}


