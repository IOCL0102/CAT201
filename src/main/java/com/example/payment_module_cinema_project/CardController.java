package com.example.payment_module_cinema_project;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.scene.image.Image;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import java.io.IOException;

public class CardController {

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
    private Button Search;

    @FXML
    private TextField SearchField;
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
    private Image MoviePoster;

    //back button function
    //Booked Ticket function
    //Profile function
    //logout function
    //search function
    //show order details function
    //cancel function (direct back to ticket scene)

    public void initialise(){
        pay.setDisable(true);
    }

    public void enablePayButton(ActionEvent e) throws IOException{
        long cardNum = Long.parseLong(CardNumber.getText());
        int month = Integer.parseInt(ExpiryMonth.getText());
        int year = Integer.parseInt(ExpiryYear.getText());
        int cvv = Integer.parseInt(CVV.getText());

        if(cardNum < 0){
            //error message
        }
        else if (month < 1 || month > 12){
            //error message
        }
        else if(year < 2022){
            //error message
        }
        else if(cvv < 0 || cvv > 999){
            //error message
        }
        else{
            pay.setDisable(false);
        }
    }

    public void changeToReceiptScene(ActionEvent e) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("receipt.fxml"));
        Stage stage = (Stage) pay.getScene().getWindow();
        stage.setScene(new Scene(fxmlLoader.load(), 1280, 720));
        stage.show();
    }



}
