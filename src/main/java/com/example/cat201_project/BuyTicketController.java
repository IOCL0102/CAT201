package com.example.cat201_project;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import static java.lang.Integer.parseInt;

public class BuyTicketController {

    @FXML private Button backButton;
    @FXML private Button bookedTicketButton;
    @FXML private Button logOutButton;
    @FXML private Button profileButton;
    @FXML private ImageView MoviePoster;

    @FXML private Text ErrorMsg;

    @FXML private Button checkedButton;
    @FXML private Text time1;
    @FXML private Text time2;
    @FXML private Text time3;
    @FXML private Text time4;
    @FXML private Text time5;

    @FXML private Text MovieTittle;
    @FXML private Text dateDisplay;
    @FXML private Button Time1Button;
    @FXML private Button Time2Button;
    @FXML private Button Time3Button;
    @FXML private Button Time4Button;
    @FXML private Button Time5Button;
    @FXML private ChoiceBox<String> ticketQuantitySelection;
    @FXML private Button ConfirmButton;

    @FXML private ChoiceBox<String> SeatSelection1;
    @FXML private ChoiceBox<String> SeatSelection2;
    @FXML private ChoiceBox<String> SeatSelection3;
    @FXML private ChoiceBox<String> SeatSelection4;
    @FXML private ChoiceBox<String> SeatSelection5;

    private JSONArray movieTimeSeatData = null;
    public int counter;

    public static String OrderedMovie;
    public static String OrderedTotal;
    public static String OrderedPoster;
    public static String OrderedTime;
    public static String OrderedDate;
    public static String [] OrderedSeats = {" "," "," "," "," "};
    public static String OrderedMovieCode;
    public static String OrderedTicket;


    public void initialize(int i){

        counter = i;
        switch(i){
            case 0:
                JSONObject movieTimeSeat0 = getJSONObject("movieTimeSeat0.json");
                movieTimeSeatData = (JSONArray) movieTimeSeat0.get("movieTimeSeat0");
                break;
            case 1:
                JSONObject movieTimeSeat1 = getJSONObject("movieTimeSeat1.json");
                movieTimeSeatData = (JSONArray) movieTimeSeat1.get("movieTimeSeat1");
                break;
            case 2:
                JSONObject movieTimeSeat2 = getJSONObject("movieTimeSeat2.json");
                movieTimeSeatData = (JSONArray) movieTimeSeat2.get("movieTimeSeat2");
                break;
            case 3:
                JSONObject movieTimeSeat3 = getJSONObject("movieTimeSeat3.json");
                movieTimeSeatData = (JSONArray) movieTimeSeat3.get("movieTimeSeat3");
                break;
            case 4:
                JSONObject movieTimeSeat4 = getJSONObject("movieTimeSeat4.json");
                movieTimeSeatData = (JSONArray) movieTimeSeat4.get("movieTimeSeat4");
                break;
            case 5:
                JSONObject movieTimeSeat5 = getJSONObject("movieTimeSeat5.json");
                movieTimeSeatData = (JSONArray) movieTimeSeat5.get("movieTimeSeat5");
                break;
            case 6:
                JSONObject movieTimeSeat6 = getJSONObject("movieTimeSeat6.json");
                movieTimeSeatData = (JSONArray) movieTimeSeat6.get("movieTimeSeat6");
                break;
            case 7:
                JSONObject movieTimeSeat7 = getJSONObject("movieTimeSeat7.json");
                movieTimeSeatData = (JSONArray) movieTimeSeat7.get("movieTimeSeat7");
                break;
            case 8:
                JSONObject movieTimeSeat8 = getJSONObject("movieTimeSeat8.json");
                movieTimeSeatData = (JSONArray) movieTimeSeat8.get("movieTimeSeat8");
                break;
            case 9:
                JSONObject movieTimeSeat9 = getJSONObject("movieTimeSeat9.json");
                movieTimeSeatData = (JSONArray) movieTimeSeat9.get("movieTimeSeat9");
                break;
            case 10:
                JSONObject movieTimeSeat10 = getJSONObject("movieTimeSeat10.json");
                movieTimeSeatData = (JSONArray) movieTimeSeat10.get("movieTimeSeat10");
                break;
            case 11:
                JSONObject movieTimeSeat11 = getJSONObject("movieTimeSeat11.json");
                movieTimeSeatData = (JSONArray) movieTimeSeat11.get("movieTimeSeat11");
                break;
            case 12:
                JSONObject movieTimeSeat12 = getJSONObject("movieTimeSeat12.json");
                movieTimeSeatData = (JSONArray) movieTimeSeat12.get("movieTimeSeat12");
                break;
            case 13:
                JSONObject movieTimeSeat13 = getJSONObject("movieTimeSeat13.json");
                movieTimeSeatData = (JSONArray) movieTimeSeat13.get("movieTimeSeat13");
                break;
        }
        ErrorMsg.setVisible(false);
        SeatSelection2.setVisible(false);
        SeatSelection3.setVisible(false);
        SeatSelection4.setVisible(false);
        SeatSelection5.setVisible(false);

        //Display movie tittle
        String movieTittleDisplay = (((JSONObject)movieTimeSeatData.get(0)).get("movieName")).toString();
        MovieTittle.setText(movieTittleDisplay);
        OrderedMovie = movieTittleDisplay;

        Image image = null;
        try
        {
            String moviePhotoSource = (((JSONObject)movieTimeSeatData.get(0)).get("moviePosterPath")).toString();
            image = new Image(new FileInputStream(moviePhotoSource));
            OrderedPoster = moviePhotoSource;
            MoviePoster.setImage(image);
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }

        String moviecode = (((JSONObject)movieTimeSeatData.get(0)).get("movieID")).toString();
        OrderedMovieCode = moviecode;

        //Display date
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        dateDisplay.setText(formatter.format(date));
        OrderedDate=formatter.format(date);

        //read file to set movie time slot
        for (int a=0; a<5;a++){
            String movieTime = (((JSONObject)movieTimeSeatData.get(a)).get("movieTimeSlot")).toString();
            switch(a){
                case 0:time1.setText(movieTime);
                    break;
                case 1:time2.setText(movieTime);
                    break;
                case 2:time3.setText(movieTime);
                    break;
                case 3:time4.setText(movieTime);
                    break;
                case 4:time5.setText(movieTime);
                    break;
            }
        }

        ticketQuantitySelection.getItems().addAll("1", "2", "3","4","5");
        ticketQuantitySelection.setValue("Ticket Quantity");

    }

    public void checkedButtonClicked(ActionEvent event) {
        OrderedTicket = ticketQuantitySelection.getValue();
        checkedButton.setDisable(true);

        if(OrderedTicket.equals("2")){
            SeatSelection2.setVisible(true);
        }
        else if (OrderedTicket.equals("3")){
            SeatSelection2.setVisible(true);
            SeatSelection3.setVisible(true);
        }
        else if (OrderedTicket.equals("4")){
            SeatSelection2.setVisible(true);
            SeatSelection3.setVisible(true);
            SeatSelection4.setVisible(true);
        }
        else if (OrderedTicket.equals("5")){
            SeatSelection5.setVisible(true);
            SeatSelection2.setVisible(true);
            SeatSelection3.setVisible(true);
            SeatSelection4.setVisible(true);
        }
    }

    public void timeButton1OnClick(ActionEvent event) {
        OrderedTime = time1.getText();
        Time1Button.getStyleClass().removeAll("normalbutton");
        Time1Button.getStyleClass().add("clickedbutton");
        Time2Button.setDisable(true);
        Time3Button.setDisable(true);
        Time4Button.setDisable(true);
        Time5Button.setDisable(true);

            for (int j=1; j<=8;j++)
            {
                String num = Integer.toString(j);
                String movieArow = (((JSONObject) movieTimeSeatData.get(0)).get("movieSeatA0" + num)).toString();
                if(movieArow .equals("true")){
                    SeatSelection1.getItems().add("A0"+num);
                    SeatSelection2.getItems().add("A0"+num);
                    SeatSelection3.getItems().add("A0"+num);
                    SeatSelection4.getItems().add("A0"+num);
                    SeatSelection5.getItems().add("A0"+num);
                }
            }
            for (int j=1; j<=8;j++)
            {
                String num = Integer.toString(j);
                String movieBrow = (((JSONObject) movieTimeSeatData.get(0)).get("movieSeatB0" + num)).toString();
                if(movieBrow .equals("true")){
                    SeatSelection1.getItems().add("B0"+num);
                    SeatSelection2.getItems().add("B0"+num);
                    SeatSelection3.getItems().add("B0"+num);
                    SeatSelection4.getItems().add("B0"+num);
                    SeatSelection5.getItems().add("B0"+num);
                }
            }
            for (int j=1; j<=8;j++)
            {
                String num = Integer.toString(j);
                String movieCrow = (((JSONObject) movieTimeSeatData.get(0)).get("movieSeatC0" + num)).toString();
                if(movieCrow .equals("true")){
                    SeatSelection1.getItems().add("C0"+num);
                    SeatSelection2.getItems().add("C0"+num);
                    SeatSelection3.getItems().add("C0"+num);
                    SeatSelection4.getItems().add("C0"+num);
                    SeatSelection5.getItems().add("C0"+num);
                }
            }
    }

    public void timeButton2OnClick(ActionEvent event) {
        OrderedTime = time2.getText();
        Time2Button.getStyleClass().removeAll("normalbutton");
        Time2Button.getStyleClass().add("clickedbutton");
        Time1Button.setDisable(true);
        Time3Button.setDisable(true);
        Time4Button.setDisable(true);
        Time5Button.setDisable(true);
        for (int j=1; j<=8;j++)
        {
            String num = Integer.toString(j);
            String movieArow = (((JSONObject) movieTimeSeatData.get(1)).get("movieSeatA0" + num)).toString();
            if(movieArow .equals("true")){
                SeatSelection1.getItems().add("A0"+num);
                SeatSelection2.getItems().add("A0"+num);
                SeatSelection3.getItems().add("A0"+num);
                SeatSelection4.getItems().add("A0"+num);
                SeatSelection5.getItems().add("A0"+num);
            }
        }
        for (int j=1; j<=8;j++)
        {
            String num = Integer.toString(j);
            String movieBrow = (((JSONObject) movieTimeSeatData.get(1)).get("movieSeatB0" + num)).toString();
            if(movieBrow .equals("true")){
                SeatSelection1.getItems().add("B0"+num);
                SeatSelection2.getItems().add("B0"+num);
                SeatSelection3.getItems().add("B0"+num);
                SeatSelection4.getItems().add("B0"+num);
                SeatSelection5.getItems().add("B0"+num);
            }
        }
        for (int j=1; j<=8;j++)
        {
            String num = Integer.toString(j);
            String movieCrow = (((JSONObject) movieTimeSeatData.get(1)).get("movieSeatC0" + num)).toString();
            if(movieCrow .equals("true")){
                SeatSelection1.getItems().add("C0"+num);
                SeatSelection2.getItems().add("C0"+num);
                SeatSelection3.getItems().add("C0"+num);
                SeatSelection4.getItems().add("C0"+num);
                SeatSelection5.getItems().add("C0"+num);
            }
        }
    }

    public void timeButton3OnClick(ActionEvent event) {
        OrderedTime = time3.getText();
        Time3Button.getStyleClass().removeAll("normalbutton");
        Time3Button.getStyleClass().add("clickedbutton");
        Time2Button.setDisable(true);
        Time1Button.setDisable(true);
        Time4Button.setDisable(true);
        Time5Button.setDisable(true);
        for (int j=1; j<=8;j++)
        {
            String num = Integer.toString(j);
            String movieArow = (((JSONObject) movieTimeSeatData.get(2)).get("movieSeatA0" + num)).toString();
            if(movieArow .equals("true")){
                SeatSelection1.getItems().add("A0"+num);
                SeatSelection2.getItems().add("A0"+num);
                SeatSelection3.getItems().add("A0"+num);
                SeatSelection4.getItems().add("A0"+num);
                SeatSelection5.getItems().add("A0"+num);
            }
        }
        for (int j=1; j<=8;j++)
        {
            String num = Integer.toString(j);
            String movieBrow = (((JSONObject) movieTimeSeatData.get(2)).get("movieSeatB0" + num)).toString();
            if(movieBrow .equals("true")){
                SeatSelection1.getItems().add("B0"+num);
                SeatSelection2.getItems().add("B0"+num);
                SeatSelection3.getItems().add("B0"+num);
                SeatSelection4.getItems().add("B0"+num);
                SeatSelection5.getItems().add("B0"+num);
            }
        }
        for (int j=1; j<=8;j++)
        {
            String num = Integer.toString(j);
            String movieCrow = (((JSONObject) movieTimeSeatData.get(2)).get("movieSeatC0" + num)).toString();
            if(movieCrow .equals("true")){
                SeatSelection1.getItems().add("C0"+num);
                SeatSelection2.getItems().add("C0"+num);
                SeatSelection3.getItems().add("C0"+num);
                SeatSelection4.getItems().add("C0"+num);
                SeatSelection5.getItems().add("C0"+num);
            }
        }

    }

    public void timeButton4OnClick(ActionEvent event) {
        OrderedTime = time4.getText();
        Time4Button.getStyleClass().removeAll("normalbutton");
        Time4Button.getStyleClass().add("clickedbutton");
        Time2Button.setDisable(true);
        Time3Button.setDisable(true);
        Time1Button.setDisable(true);
        Time5Button.setDisable(true);
        for (int j=1; j<=8;j++)
        {
            String num = Integer.toString(j);
            String movieArow = (((JSONObject) movieTimeSeatData.get(3)).get("movieSeatA0" + num)).toString();
            if(movieArow .equals("true")){
                SeatSelection1.getItems().add("A0"+num);
                SeatSelection2.getItems().add("A0"+num);
                SeatSelection3.getItems().add("A0"+num);
                SeatSelection4.getItems().add("A0"+num);
                SeatSelection5.getItems().add("A0"+num);
            }
        }
        for (int j=1; j<=8;j++)
        {
            String num = Integer.toString(j);
            String movieBrow = (((JSONObject) movieTimeSeatData.get(3)).get("movieSeatB0" + num)).toString();
            if(movieBrow .equals("true")){
                SeatSelection1.getItems().add("B0"+num);
                SeatSelection2.getItems().add("B0"+num);
                SeatSelection3.getItems().add("B0"+num);
                SeatSelection4.getItems().add("B0"+num);
                SeatSelection5.getItems().add("B0"+num);
            }
        }
        for (int j=1; j<=8;j++)
        {
            String num = Integer.toString(j);
            String movieCrow = (((JSONObject) movieTimeSeatData.get(3)).get("movieSeatC0" + num)).toString();
            if(movieCrow .equals("true")){
                SeatSelection1.getItems().add("C0"+num);
                SeatSelection2.getItems().add("C0"+num);
                SeatSelection3.getItems().add("C0"+num);
                SeatSelection4.getItems().add("C0"+num);
                SeatSelection5.getItems().add("C0"+num);
            }
        }
    }

    public void timeButton5OnClick(ActionEvent event) {
        OrderedTime = time5.getText();
        Time5Button.getStyleClass().removeAll("normalbutton");
        Time5Button.getStyleClass().add("clickedbutton");
        Time2Button.setDisable(true);
        Time3Button.setDisable(true);
        Time4Button.setDisable(true);
        Time1Button.setDisable(true);

        for (int j=1; j<=8;j++)
        {
            String num = Integer.toString(j);
            String movieArow = (((JSONObject) movieTimeSeatData.get(4)).get("movieSeatA0" + num)).toString();
            if(movieArow .equals("true")){
                SeatSelection1.getItems().add("A0"+num);
                SeatSelection2.getItems().add("A0"+num);
                SeatSelection3.getItems().add("A0"+num);
                SeatSelection4.getItems().add("A0"+num);
                SeatSelection5.getItems().add("A0"+num);
            }
        }
        for (int j=1; j<=8;j++)
        {
            String num = Integer.toString(j);
            String movieBrow = (((JSONObject) movieTimeSeatData.get(4)).get("movieSeatB0" + num)).toString();
            if(movieBrow .equals("true")){
                SeatSelection1.getItems().add("B0"+num);
                SeatSelection2.getItems().add("B0"+num);
                SeatSelection3.getItems().add("B0"+num);
                SeatSelection4.getItems().add("B0"+num);
                SeatSelection5.getItems().add("B0"+num);
            }
        }
        for (int j=1; j<=8;j++)
        {
            String num = Integer.toString(j);
            String movieCrow = (((JSONObject) movieTimeSeatData.get(4)).get("movieSeatC0" + num)).toString();
            if(movieCrow .equals("true")){
                SeatSelection1.getItems().add("C0"+num);
                SeatSelection2.getItems().add("C0"+num);
                SeatSelection3.getItems().add("C0"+num);
                SeatSelection4.getItems().add("C0"+num);
                SeatSelection5.getItems().add("C0"+num);
            }
        }
    }

    private static JSONObject getJSONObject(String fileName)
    {
        try
        {
            FileReader reader = new FileReader("src/main/resources/com/example/cat201_project/JSON_file/" + fileName);
            JSONParser jsonParser = new JSONParser();
            Object obj = jsonParser.parse(reader);
            return (JSONObject) obj;
        } catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public void BacktoMovieInfoScene(ActionEvent event) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("movie-info.fxml"));
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.setScene(new Scene(fxmlLoader.load(), 1280, 720));
        MovieInfoController controller = fxmlLoader.getController();
        controller.initialize(counter);
        stage.show();
    }

    //change scence
    public void ConfirmButtonclicked(ActionEvent event)throws IOException
    {
        if(SeatSelection1.getValue().equals(SeatSelection2.getValue()) && OrderedTicket.equals("2"))
        {
            ErrorMsg.setVisible(true);
            FadeTransition fadeMessage = new FadeTransition(Duration.millis(6000), ErrorMsg);
            fadeMessage.setFromValue(1);
            fadeMessage.setToValue(0);
            fadeMessage.play();
        }
        else if (OrderedTicket.equals("3") && (SeatSelection1.getValue().equals(SeatSelection2.getValue())
                || SeatSelection1.getValue().equals(SeatSelection3.getValue())
                || SeatSelection2.getValue().equals(SeatSelection3.getValue())))
        {
            ErrorMsg.setVisible(true);
            FadeTransition fadeMessage = new FadeTransition(Duration.millis(6000), ErrorMsg);
            fadeMessage.setFromValue(1);
            fadeMessage.setToValue(0);
            fadeMessage.play();
        }

        else if (OrderedTicket.equals("4")
                && (SeatSelection1.getValue().equals(SeatSelection2.getValue())
                || SeatSelection1.getValue().equals(SeatSelection3.getValue())
                || SeatSelection1.getValue().equals(SeatSelection4.getValue())
                || SeatSelection2.getValue().equals(SeatSelection3.getValue())
                || SeatSelection2.getValue().equals(SeatSelection4.getValue())
                || SeatSelection3.getValue().equals(SeatSelection4.getValue())))
        {
            ErrorMsg.setVisible(true);
            FadeTransition fadeMessage = new FadeTransition(Duration.millis(6000), ErrorMsg);
            fadeMessage.setFromValue(1);
            fadeMessage.setToValue(0);
            fadeMessage.play();
        }

        else if (OrderedTicket.equals("5")
                && (SeatSelection1.getValue().equals(SeatSelection2.getValue())
                || SeatSelection1.getValue().equals(SeatSelection3.getValue())
                || SeatSelection1.getValue().equals(SeatSelection4.getValue())
                || SeatSelection1.getValue().equals(SeatSelection5.getValue())
                || SeatSelection2.getValue().equals(SeatSelection3.getValue())
                || SeatSelection2.getValue().equals(SeatSelection4.getValue())
                || SeatSelection2.getValue().equals(SeatSelection5.getValue())
                || SeatSelection3.getValue().equals(SeatSelection4.getValue())
                || SeatSelection3.getValue().equals(SeatSelection5.getValue())
                || SeatSelection4.getValue().equals(SeatSelection5.getValue())))
        {
            ErrorMsg.setVisible(true);
            FadeTransition fadeMessage = new FadeTransition(Duration.millis(6000), ErrorMsg);
            fadeMessage.setFromValue(1);
            fadeMessage.setToValue(0);
            fadeMessage.play();
        }

        else{

            double price = parseInt(OrderedTicket) * 25;
            OrderedTotal= Double.toString(price);
            OrderedSeats [0] = SeatSelection1.getValue();
            OrderedSeats [1] = SeatSelection2.getValue();
            OrderedSeats [2] = SeatSelection3.getValue();
            OrderedSeats [3] = SeatSelection4.getValue();
            OrderedSeats [4] = SeatSelection5.getValue();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Confirm_Ticket.fxml"));
        Stage stage = (Stage) ConfirmButton.getScene().getWindow();
        stage.setScene(new Scene(fxmlLoader.load(), 1280, 720));
        stage.show();

        }

    }

    public void bookedTicketButtonClicked(ActionEvent event)throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("bookedTicket.fxml"));
        Stage stage = (Stage) bookedTicketButton.getScene().getWindow();
        stage.setScene(new Scene(fxmlLoader.load(), 1280, 720));
        stage.show();
    }

    public void logOutButtonClicked(ActionEvent event)throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login-view.fxml"));
        Stage stage = (Stage) logOutButton.getScene().getWindow();
        stage.setScene(new Scene(fxmlLoader.load(), 1280, 720));
        stage.show();
    }

    public void profileButtonClicked(ActionEvent event)throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("myProfile.fxml"));
        Stage stage = (Stage) profileButton.getScene().getWindow();
        stage.setScene(new Scene(fxmlLoader.load(), 1280, 720));
        stage.show();
    }

}