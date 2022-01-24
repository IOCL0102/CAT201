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

public class TngController {

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
    private Image MoviePoster;

    //back button function
    //Booked Ticket function
    //Profile function
    //logout function
    //show order details function
    //cancel function (direct back to ticket scene)

    public void initialise(){
        PhoneNumErrMessage.setVisible(false);
        OTPErrMessage.setVisible(false);
    }

    public void changeToReceiptScene(ActionEvent e) throws IOException{
        String phoneNum = PhoneNumber.getText();
        int OTP = Integer.parseInt(OneTimePassword.getText());

        if(phoneNum == ""){
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
