package com.example.payment_module_cinema_project;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.scene.image.Image;
import javafx.event.ActionEvent;
import java.io.IOException;


public class ReceiptController {

    @FXML
    private Button BookedTicket;
    @FXML
    private Button Profile;
    @FXML
    private Button logout;


    @FXML
    private Text AdultTicket;
    @FXML
    private Text ChildrenTicket;
    @FXML
    private Text Experience;
    @FXML
    private Text Total;
    @FXML
    private Text Movie;
    @FXML
    private Text Date;
    @FXML
    private Text Time;
    @FXML
    private Text Seats;

    @FXML
    private Image MoviePoster;
    @FXML
    private Image ReceiptQR;

    //Booked Ticket function
    //Profile function
    //logout function
    //show order details function
    //show QR function
}
