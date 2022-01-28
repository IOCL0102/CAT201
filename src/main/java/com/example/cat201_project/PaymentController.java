package com.example.cat201_project;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.text.Text;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;


public class PaymentController implements Initializable {

    @FXML
    private Button back;
    @FXML
    private Button next;
    @FXML
    private Button BookedTicket;
    @FXML
    private Button Profile;
    @FXML
    private Button logout;

    @FXML
    private ChoiceBox<String> paymentOption;

    @FXML
    private Text AdultTicket;
    @FXML
    private Text ChildrenTicket;
    @FXML
    private Text Experience;
    @FXML
    private Text Total;
    @FXML
    private Text ErrorMessage;

    @FXML
    private ImageView MoviePoster;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) throws NullPointerException{
        ErrorMessage.setVisible(false); //Disable error message

        //Initialse ChoiceBox choices
        paymentOption.getItems().addAll("Select Payment Option", "Credit/Debit Card", "TouchnGo");
        paymentOption.setValue("Select Payment Option");

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

        //Initialise image poster using path from JSON file
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
        //insert back to ticket scene function
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

    public void handleLogoutBttn() throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login-view.fxml"));
        Stage stage = (Stage) logout.getScene().getWindow();
        stage.setScene(new Scene(fxmlLoader.load(), 1280, 720));
        stage.show();
    }

    //Change to next scene after user click "Next"
    public void changeToPaymentScene() throws IOException{
        if(paymentOption.getValue().equals("Credit/Debit Card")){
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("cardpayment.fxml"));
            Stage stage = (Stage) next.getScene().getWindow();
            stage.setScene(new Scene(fxmlLoader.load(), 1280, 720));
            stage.show();
        }
        else if(paymentOption.getValue().equals("TouchnGo")){
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("tngpayment.fxml"));
            Stage stage = (Stage) next.getScene().getWindow();
            stage.setScene(new Scene(fxmlLoader.load(), 1280, 720));
            stage.show();
        }
        else{
            ErrorMessage.setVisible(true);
        }
    }
}

