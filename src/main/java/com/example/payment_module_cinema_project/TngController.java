package com.example.payment_module_cinema_project;

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
//import javafx.event.ActionEvent;
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


    public void initialize(URL url, ResourceBundle resourceBundle){
        PhoneNumErrMessage.setVisible(false);
        OTPErrMessage.setVisible(false);

        JSONArray orderData;

        JSONObject orderInfo = JsonEditor.getJSONObject("orderInfo.json");
        orderData = (JSONArray)orderInfo.get("orderInfo");

        String AT = (((JSONObject)orderData.get(orderData.size() - 1)).get("AdultTicket")).toString();
        String CT = (((JSONObject)orderData.get(orderData.size() - 1)).get("ChildrenTicket")).toString();
        String EXP = (((JSONObject)orderData.get(orderData.size() - 1)).get("Experience")).toString();
        String TOT = (((JSONObject)orderData.get(orderData.size() - 1)).get("Total")).toString();

        AdultTicket.setText(AT);
        ChildrenTicket.setText(CT);
        Experience.setText(EXP);
        Total.setText(TOT);

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

    public void handleBackBttn(){
        //back button function
    }

    public void handleBookedTicketBttn(){
        //insert function from booked ticket
    }

    public void handleProfileBttn(){
        //insert function from profile
    }

    public void handleLogoutBttn(){
        //insert logout function
    }

    public void handleCancelBttn(){
        //insert cancel function
    }

    public void changeToReceiptScene() throws IOException{//not sure need to include ActionEvent in the parameter
        String phoneNum = PhoneNumber.getText();
        int OTP = Integer.parseInt(OneTimePassword.getText());

        if(phoneNum.equals("")){
            PhoneNumErrMessage.setVisible(true);
            OTPErrMessage.setVisible(false);
        }
        else if (OTP < 0 || OTP > 999999){
            PhoneNumErrMessage.setVisible(false);
            OTPErrMessage.setVisible(true);
        }
        else{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("receipt.fxml"));
            Stage stage = (Stage) pay.getScene().getWindow();
            stage.setScene(new Scene(fxmlLoader.load(), 1280, 720));
            stage.show();
        }
    }

}
