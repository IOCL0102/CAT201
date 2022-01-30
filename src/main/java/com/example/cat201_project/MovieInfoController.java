package com.example.cat201_project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class MovieInfoController {
    @FXML private Button bookedTicketButton;
    @FXML private Button profileButton;
    @FXML private Button logOutButton;

    @FXML private ImageView ChosenMovieImage;
    @FXML private Text ChosenMovieDesc;
    @FXML private Text ChosenMovieTittle;
    @FXML private Text ChosenMovieCast;
    @FXML private Text ChosenMovieDirector;
    @FXML private Button backButton;
    @FXML private Button bookNowButton;

    public int movieIndex;
    private JSONArray movieData = null;

    public void initialize(int i)
    {
        movieIndex =i;
        JSONObject movieInfo = getJSONObject("movieInfo.json");
        movieData = (JSONArray) movieInfo.get("movieInfo");

        for(int j = 0; j < movieData.size(); j++)
        {
            if(i==j)
            {
                //if the movie selected is the last two, hide the book now button
                if( i == movieData.size() -1 ||i == movieData.size() -2  ){
                    bookNowButton.setVisible(false);
                }

                String movieTittle = (((JSONObject) movieData.get(i)).get("movieName")).toString();
                String movieDes = (((JSONObject) movieData.get(i)).get("movieDescription")).toString();
                String movieCast = (((JSONObject) movieData.get(i)).get("movieCast")).toString();
                String movieDir = (((JSONObject) movieData.get(i)).get("movieDirector")).toString();

                Image image = null;
                try
                {
                    String moviePhotoSource = (((JSONObject) movieData.get(i)).get("moviePoster")).toString();
                    image = new Image(new FileInputStream(moviePhotoSource));
                    ChosenMovieImage.setImage(image);

                } catch (FileNotFoundException e)
                {
                    e.printStackTrace();
                }
                ChosenMovieTittle.setText(movieTittle);
                ChosenMovieDesc.setText(movieDes);
                ChosenMovieCast.setText("Movie Cast: "+ movieCast);
                ChosenMovieDirector.setText("Movie Director: "+ movieDir);
            }
        }
    }


    public void BacktoMovieHomeScene(ActionEvent event) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("home-movie.fxml"));
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.setScene(new Scene(fxmlLoader.load(), 1280, 720));
        stage.show();
    }

    public void bookNowButtonclicked(ActionEvent event) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("buy-ticket.fxml"));
        Stage stage = (Stage) bookNowButton.getScene().getWindow();
        stage.setScene(new Scene(fxmlLoader.load(), 1280, 720));
        BuyTicketController controller = fxmlLoader.getController();
        controller.initialize(movieIndex);
        stage.show();
    }

    //change scene to see booked ticket
    public void bookedTicketButtonClicked(ActionEvent event) throws IOException
    {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("bookedTicket.fxml"));
        Stage stage = (Stage) bookedTicketButton.getScene().getWindow();
        stage.setScene(new Scene(fxmlLoader.load(), 1280, 720));
        stage.show();

    }

    //change scene back to login page
    public void logOutButtonClicked(ActionEvent event) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login-view.fxml"));
        Stage stage = (Stage) logOutButton.getScene().getWindow();
        stage.setScene(new Scene(fxmlLoader.load(), 1280, 720));
        stage.show();
    }

    //change scene to profile page
    public void profileButtonClicked(ActionEvent event) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("myProfile.fxml"));
        Stage stage = (Stage) profileButton.getScene().getWindow();
        stage.setScene(new Scene(fxmlLoader.load(), 1280, 720));
        stage.show();
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

}
