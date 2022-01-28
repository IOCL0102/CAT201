package com.example.cat201_project;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.scene.image.Image;
import javafx.scene.control.TextField;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TngController implements Initializable {

    @FXML
    private Button back;
    @FXML
    private Button pay;
    @FXML
    private Button cancel;
    @FXML
    private Button BookedTicket;
    @FXML
    private Button Profile;
    @FXML
    private Button logout;

    @FXML
    private TextField PhoneNumber;
    @FXML
    private TextField OneTimePassword;

    @FXML
    private Text AdultTicket;
    @FXML
    private Text ChildrenTicket;
    @FXML
    private Text Experience;
    @FXML
    private Text Total;
    @FXML
    private Text PhoneNumErrMessage;
    @FXML
    private Text OTPErrMessage;

    @FXML
    private ImageView MoviePoster;


    public void initialize(URL url, ResourceBundle resourceBundle) throws NullPointerException{
        //Disable all error messages
        PhoneNumErrMessage.setVisible(false);
        OTPErrMessage.setVisible(false);

        JSONArray orderData;

        JSONObject orderInfo = JsonEditor.getJSONObject("orderInfo.json");
        orderData = (JSONArray)orderInfo.get("orderInfo");

        String AT = (((JSONObject)orderData.get(orderData.size() - 1)).get("AdultTicket")).toString();
        String CT = (((JSONObject)orderData.get(orderData.size() - 1)).get("ChildrenTicket")).toString();
        String EXP = (((JSONObject)orderData.get(orderData.size() - 1)).get("Experience")).toString();
        String TOT = (((JSONObject)orderData.get(orderData.size() - 1)).get("Total")).toString();

        //Initialise the movie details from JSON file
        AdultTicket.setText(AT);
        ChildrenTicket.setText(CT);
        Experience.setText(EXP);
        Total.setText(TOT);

        //Initialise the movie poster using path from JSON file
        Image image;
        try
        {
            String moviePosterSource = (((JSONObject) orderData.get(orderData.size() - 1)).get("Poster")).toString();
            image = new Image(new FileInputStream(moviePosterSource));
            MoviePoster.setImage(image);

        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }

    //Direct user back to Select Payment Scene when they click the Back button
    public void handleBackBttn() throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("selectpayment.fxml"));
        Stage stage = (Stage) back.getScene().getWindow();
        stage.setScene(new Scene(fxmlLoader.load(), 1280, 720));
        stage.show();
    }

    public void handleBookedTicketBttn(){
        //insert function from booked ticket
    }

    public void handleProfileBttn() throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("myProfile.fxml"));
        Stage stage = (Stage) Profile.getScene().getWindow();
        stage.setScene(new Scene(fxmlLoader.load(), 1280, 720));
        stage.show();
    }

    public void handleLogoutBttn()throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login-view.fxml"));
        Stage stage = (Stage) logout.getScene().getWindow();
        stage.setScene(new Scene(fxmlLoader.load(), 1280, 720));
        stage.show();
    }

    public void handleCancelBttn(){
        //insert cancel function
    }

    //Direct user to Receipt scene when they click Pay button
    public void changeToReceiptScene() throws IOException, NumberFormatException{
        String phoneNum = PhoneNumber.getText();
        String otpstr = OneTimePassword.getText();

        int OTP = -1;
        if(!otpstr.isEmpty()){
            OTP = Integer.parseInt(otpstr);
        }

        //Display error message if users enter invalid numbers or if users leave text field empty
        if (phoneNum.isEmpty()) {
            PhoneNumErrMessage.setVisible(true);
            OTPErrMessage.setVisible(false);
        } else if (OTP < 0 || OTP > 999999) {
            PhoneNumErrMessage.setVisible(false);
            OTPErrMessage.setVisible(true);
        }else {//Change to Receipt scene if all text field are filled with valid numbers
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("receipt.fxml"));
            Stage stage = (Stage) pay.getScene().getWindow();
            stage.setScene(new Scene(fxmlLoader.load(), 1280, 720));
            stage.show();
        }
    }
}
