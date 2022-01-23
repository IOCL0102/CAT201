package com.example.payment_module_cinema_project;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.text.Text;
import javafx.scene.image.Image;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import java.io.IOException;

public class PaymentController {

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
    private Button Search;

    @FXML
    private ChoiceBox<String> paymentOption;

    @FXML
    private TextField SearchField;

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
    private Image MoviePoster;

    //back button function
    //Booked Ticket function
    //Profile function
    //logout function
    //search function
    //show order details function

    public void initialise(){
        ErrorMessage.setVisible(false);
        paymentOption = new ChoiceBox<>();
        paymentOption.getItems().addAll("Select Payment Option", "Credit/Debit Card", "TouchnGo");
        paymentOption.setValue("Select Payment Option");
    }

    public void changeToPaymentScene(ActionEvent e, ChoiceBox<String> paymentOption) throws IOException{
        if(paymentOption.getValue() == "Credit/Debit Card"){
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("cardpayment.fxml"));
            Stage stage = (Stage) next.getScene().getWindow();
            stage.setScene(new Scene(fxmlLoader.load(), 1280, 720));
            stage.show();
        }
        else if(paymentOption.getValue() == "TouchnGo"){
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

