package com.example.cat201_project;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Confirm_TicketController implements Initializable {

    @FXML
    private Button Profile;
    @FXML
    private Button logout;
    @FXML
    private Button pay;
    @FXML
    private Button back;
    @FXML
    private Button BookedTicket;

    @FXML
    private Text Movie;
    @FXML
    private Text Date;
    @FXML
    private Text Time;

    @FXML
    private Text Ticket;
    @FXML
    private Text Seats;
    @FXML
    private Text Total;

    @FXML
    private ImageView MoviePoster;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) throws NullPointerException {
        String movie = BuyTicketController.OrderedMovie;
        String date = BuyTicketController.OrderedDate;
        String time = BuyTicketController.OrderedTime;
        String seats = "";
        String T =  BuyTicketController.OrderedTicket;
        String Tot = BuyTicketController.OrderedTotal;
        int numTicket = Integer.parseInt(BuyTicketController.OrderedTicket);
        for(int i = 0; i < numTicket; i++){
            seats = seats + BuyTicketController.OrderedSeats[i] + " ";
        }

        //Initialise movie details
        Movie.setText(movie);
        Date.setText(date);
        Time.setText(time);
        Seats.setText(seats);
        Total.setText(Tot);
        Ticket.setText(T);

        //Initialise movie poster
        Image image;
        try
        {
            String moviePosterSource = BuyTicketController.OrderedPoster;
            image = new Image(new FileInputStream(moviePosterSource));
            MoviePoster.setImage(image);

        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }

    public void handleProfileBttn() throws IOException {
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

    public void handlePayBttn() throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("selectpayment.fxml"));
        Stage stage = (Stage) pay.getScene().getWindow();
        stage.setScene(new Scene(fxmlLoader.load(), 1280, 720));
        stage.show();
    }

    public void handleBackBttn() throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("buy-ticket.fxml"));
        Stage stage = (Stage) back.getScene().getWindow();
        stage.setScene(new Scene(fxmlLoader.load(), 1280, 720));
        stage.show();
    }

    public void handleBookedTicketBttn() throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Booked_Ticket.fxml"));
        Stage stage = (Stage) BookedTicket.getScene().getWindow();
        stage.setScene(new Scene(fxmlLoader.load(), 1280, 720));
        stage.show();
    }
}
