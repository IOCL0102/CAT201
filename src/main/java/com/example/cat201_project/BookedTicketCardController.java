package com.example.cat201_project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import javax.mail.MessagingException;
import java.io.IOException;

public class BookedTicketCardController {

    @FXML private Label date;
    @FXML private Label movieName;
    @FXML private Label seat;
    @FXML private Label time;
    @FXML private Button getQRCodeBttn;

    private String ticketQRPath;
    public void setData(BookedTicket tickets){
        date.setText(tickets.getDate());
        movieName.setText(tickets.getMovieName());
        seat.setText(tickets.getSeat());
        time.setText(tickets.getTime());
        ticketQRPath = tickets.getQRsource();
    }

    public void sendQRCode(ActionEvent e) throws IOException, ParseException, MessagingException {
        JSONObject user = JsonEditor.getCurrentUserInfo();
        String email = (String) user.get("email");
        System.out.println(email);
        MailSender.sendQRCode(email,ticketQRPath,movieName.getText());
    }
}
