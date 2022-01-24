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
    private Image MoviePoster;


    public void initialise(Image poster, Text adult, Text child, Text exp, Text tot){
        ErrorMessage.setVisible(false);
        paymentOption = new ChoiceBox<>();
        paymentOption.getItems().addAll("Select Payment Option", "Credit/Debit Card", "TouchnGo");
        paymentOption.setValue("Select Payment Option");
        AdultTicket = adult;
        ChildrenTicket = child;
        Experience = exp;
        Total = tot;
        MoviePoster = poster; //check whether shown in scene builder
    }

    public void handleBackBttn() throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("cardpayment.fxml"));//change to ticket scene later
        Stage stage = (Stage) next.getScene().getWindow();
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


    public void changeToPaymentScene(ActionEvent e, ChoiceBox<String> paymentOption) throws IOException{
        if(paymentOption.getValue() == "Credit/Debit Card"){
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("cardpayment.fxml"));
            Stage stage = (Stage) next.getScene().getWindow();
            stage.setScene(new Scene(fxmlLoader.load(), 1280, 720));
            CardController controller = fxmlLoader.getController();
            //controller.initialise(jfdklf,dsfaf, dfsaf);
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

