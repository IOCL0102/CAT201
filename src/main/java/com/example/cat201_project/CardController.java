package com.example.cat201_project;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.scene.image.Image;
import javafx.scene.control.TextField;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class CardController implements  Initializable{

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
    private TextField CardNumber;
    @FXML
    private TextField ExpiryMonth;
    @FXML
    private TextField ExpiryYear;
    @FXML
    private TextField CVV;

    @FXML
    private Text AdultTicket;
    @FXML
    private Text ChildrenTicket;
    @FXML
    private Text Experience;
    @FXML
    private Text Total;
    @FXML
    private Text CardNumErrMessage;
    @FXML
    private Text CardMonthErrMessage;
    @FXML
    private Text CardYearErrMessage;
    @FXML
    private Text CVVErrMessage;

    @FXML
    private ImageView MoviePoster;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) throws NullPointerException{
        //Disable all error messages
        CardNumErrMessage.setVisible(false);
        CardMonthErrMessage.setVisible(false);
        CardYearErrMessage.setVisible(false);
        CVVErrMessage.setVisible(false);

        JSONArray orderData;

        JSONObject orderInfo = JsonEditor.getJSONObject("orderInfo.json");
        orderData = (JSONArray)orderInfo.get("orderInfo");

        String AT = (((JSONObject)orderData.get(orderData.size() - 1)).get("AdultTicket")).toString();
        String CT = (((JSONObject)orderData.get(orderData.size() - 1)).get("ChildrenTicket")).toString();
        String EXP = (((JSONObject)orderData.get(orderData.size() - 1)).get("Experience")).toString();
        String TOT = (((JSONObject)orderData.get(orderData.size() - 1)).get("Total")).toString();

        //Initialise movie details from JSON file
        AdultTicket.setText(AT);
        ChildrenTicket.setText(CT);
        Experience.setText(EXP);
        Total.setText(TOT);

        //Initialise movie poster using path from JSON file
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

    //Change back to Select Payment scene when user click Back button
    public void handleBackBttn() throws IOException, RuntimeException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("selectpayment.fxml"));
        Stage stage = (Stage) back.getScene().getWindow();
        stage.setScene(new Scene(fxmlLoader.load(), 1280, 720));
        stage.show();
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

    public void changeToReceiptScene() throws IOException{
        String CN = CardNumber.getText();
        String EM = ExpiryMonth.getText();
        String EY = ExpiryYear.getText();
        String CV = CVV.getText();

        long cardNum = -1;
        int month = -1;
        int year = -1;
        int cvv = -1;

        //Parse the texts from text fields into numbers if text fields are not empty
        if(!CN.isEmpty()){
            cardNum = Long.parseLong(CN);
        }
        if(!EM.isEmpty()){
            month = Integer.parseInt(EM);
        }
        if(!EY.isEmpty()){
            year = Integer.parseInt(EY);
        }
        if(!CV.isEmpty()){
            cvv = Integer.parseInt(CV);
        }

        //Show error messages whenever user enters an invalid number or leave text field empty
        if(cardNum < 0){
            CardNumErrMessage.setVisible(true);
            CardMonthErrMessage.setVisible(false);
            CardYearErrMessage.setVisible(false);
            CVVErrMessage.setVisible(false);
        }
        else if (month < 1 || month > 12){
            CardMonthErrMessage.setVisible(true);
            CardNumErrMessage.setVisible(false);
            CardYearErrMessage.setVisible(false);
            CVVErrMessage.setVisible(false);
        }
        else if(year < 2022){
            CardYearErrMessage.setVisible(true);
            CardNumErrMessage.setVisible(false);
            CardMonthErrMessage.setVisible(false);
            CVVErrMessage.setVisible(false);
        }
        else if(cvv < 0 || cvv > 999){
            CVVErrMessage.setVisible(true);
            CardNumErrMessage.setVisible(false);
            CardMonthErrMessage.setVisible(false);
            CardYearErrMessage.setVisible(false);
        }
        else{ //Change to Receipt scene when user clicks Pay button
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("receipt.fxml"));
            Stage stage = (Stage) pay.getScene().getWindow();
            stage.setScene(new Scene(fxmlLoader.load(), 1280, 720));
            stage.show();
        }
    }
}
