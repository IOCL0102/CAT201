package com.example.cat201_project;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class HomeMovieController implements Initializable
{
    @FXML private Button searchButton;
    @FXML private TextField searchTextField;
    @FXML private Text noMovieErrorMsg;

    @FXML private ImageView popularMovieImage;
    @FXML private Button bookNowButton;
    @FXML private Button movieInfoButton;

    @FXML private Button comingSoonButton;
    @FXML private Button nowShowingButton;
    @FXML private AnchorPane scrollPaneForMovie;

    @FXML private ImageView movieImage1;
    @FXML private ImageView movieImage2;
    @FXML private ImageView movieImage3;
    @FXML private ImageView movieImage4;
    @FXML private ImageView movieImage5;
    @FXML private ImageView movieImage6;
    @FXML private ImageView movieImage7;
    @FXML private ImageView movieImage8;
    @FXML private ImageView movieImage9;
    @FXML private ImageView movieImage10;

    @FXML private Button movieButton1;
    @FXML private Button movieButton2;
    @FXML private Button movieButton3;
    @FXML private Button movieButton4;
    @FXML private Button movieButton5;
    @FXML private Button movieButton6;
    @FXML private Button movieButton7;
    @FXML private Button movieButton8;
    @FXML private Button movieButton9;
    @FXML private Button movieButton10;

    @FXML private AnchorPane ComingSoonPane;
    @FXML private ImageView comingSoonMovieImg1;
    @FXML private ImageView comingSoonMovieImg2;
    @FXML private Text comingSoonMovieText1;
    @FXML private Text comingSoonMovieText2;
    @FXML private Text comingSoonMovieDate1;
    @FXML private Text comingSoonMovieDate2;

    private JSONArray movieData = null;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        JSONObject movieInfo = getJSONObject("movieInfo.json");
        movieData = (JSONArray) movieInfo.get("movieInfo");

        noMovieErrorMsg.setVisible(false);
        ComingSoonPane.setVisible(false);

        for(int i = 1; i < movieData.size(); i++){
            String movieID = (((JSONObject)movieData.get(i)).get("movieName")).toString();
            //System.out.println(movieID);
        }

        //Load image from resources and display it out
        for(int i = 0; i < movieData.size(); i++)
        {
            Image image = null;
            try
            {
                String moviePhotoSource = (((JSONObject)movieData.get(i)).get("moviePhoto")).toString();
                image = new Image(new FileInputStream(moviePhotoSource));
            } catch (FileNotFoundException e)
            {
                e.printStackTrace();
            }

            switch (i){
                case 0: popularMovieImage.setImage(image);
                break;
                case 1: movieImage1.setImage(image);
                break;
                case 2: movieImage2.setImage(image);
                break;
                case 3: movieImage3.setImage(image);
                break;
                case 4: movieImage4.setImage(image);
                break;
                case 5: movieImage5.setImage(image);
                break;
                case 6: movieImage6.setImage(image);
                break;
                case 7: movieImage7.setImage(image);
                break;
                case 8: movieImage8.setImage(image);
                break;
                case 9: movieImage9.setImage(image);
                break;
                case 10: movieImage10.setImage(image);
                break;
            }
        }

    }

    public void searchFunction (MouseEvent event) throws IOException
    {

        for(int i = 0; i < movieData.size(); i++){
            String userInputSearch = searchTextField.getText();
            String userInputSearchLower=userInputSearch.toLowerCase();
            String movieName = (((JSONObject)movieData.get(i)).get("movieName")).toString();
            String movieNameLower = movieName.toLowerCase();

            if(userInputSearch.equals(movieName) || userInputSearchLower.equals(movieNameLower)){
                //later need to navigate to movie info scene
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("movie-info.fxml"));
                Stage stage = (Stage) searchButton.getScene().getWindow();
                stage.setScene(new Scene(fxmlLoader.load(), 1280, 720));
                MovieInfoController controller = fxmlLoader.getController();
                controller.initialize(i);
                stage.show();
                break;
            }

            else {
                noMovieErrorMsg.setVisible(true);
                FadeTransition fadeMessage = new FadeTransition(Duration.millis(6000), noMovieErrorMsg);
                fadeMessage.setFromValue(1);
                fadeMessage.setToValue(0);
                fadeMessage.play();
            }
        }
    }

    //to read JSON file and store in object
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


    public void ComingSoonButton(ActionEvent event) {
        ComingSoonPane.setVisible(true);
        for(int i = 11; i < movieData.size(); i++)
        {
            Image image = null;
            try
            {
                String moviePhotoSource = (((JSONObject)movieData.get(i)).get("moviePhoto")).toString();
                image = new Image(new FileInputStream(moviePhotoSource));
            } catch (FileNotFoundException e)
            {
                e.printStackTrace();
            }

            switch (i){
                case 11: comingSoonMovieImg1.setImage(image);
                    break;
                case 12: comingSoonMovieImg2.setImage(image);
                    break;

            }

        }

        for(int i = 11; i < movieData.size(); i++){
            String movieTittle = (((JSONObject)movieData.get(i)).get("movieName")).toString();
            String movieReleaseDate = (((JSONObject)movieData.get(i)).get("movieDescription")).toString();

            switch (i){
                case 11:
                    comingSoonMovieText1.setText(movieTittle);
                    comingSoonMovieDate1.setText(movieReleaseDate);
                break;
                case 12:
                    comingSoonMovieText2.setText(movieTittle);
                    comingSoonMovieDate2.setText(movieReleaseDate);
                break;
            }
        }




    }

    public void nowShowingButton(ActionEvent event) {
        ComingSoonPane.setVisible(false);
    }

    public void ChangetoMovieInfoScene0(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("movie-info.fxml"));
        Stage stage = (Stage) movieInfoButton.getScene().getWindow();
        stage.setScene(new Scene(fxmlLoader.load(), 1280, 720));
        MovieInfoController controller = fxmlLoader.getController();
        controller.initialize(0);
        stage.show();
    }

    public void ChangetoMovieInfoScene1(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("movie-info.fxml"));
        Stage stage = (Stage) movieButton1.getScene().getWindow();
        stage.setScene(new Scene(fxmlLoader.load(), 1280, 720));
        MovieInfoController controller = fxmlLoader.getController();
        controller.initialize(1);
        stage.show();
    }

    public void ChangetoMovieInfoScene2(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("movie-info.fxml"));
        Stage stage = (Stage) movieButton2.getScene().getWindow();
        stage.setScene(new Scene(fxmlLoader.load(), 1280, 720));
        MovieInfoController controller = fxmlLoader.getController();
        controller.initialize(2);
        stage.show();
    }

    public void ChangetoMovieInfoScene3(MouseEvent event) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("movie-info.fxml"));
        Stage stage = (Stage) movieButton3.getScene().getWindow();
        stage.setScene(new Scene(fxmlLoader.load(), 1280, 720));
        MovieInfoController controller = fxmlLoader.getController();
        controller.initialize(3);
        stage.show();
    }

    public void ChangetoMovieInfoScene4(MouseEvent event) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("movie-info.fxml"));
        Stage stage = (Stage) movieButton4.getScene().getWindow();
        stage.setScene(new Scene(fxmlLoader.load(), 1280, 720));
        MovieInfoController controller = fxmlLoader.getController();
        controller.initialize(4);
        stage.show();
    }

    public void ChangetoMovieInfoScene5(MouseEvent event) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("movie-info.fxml"));
        Stage stage = (Stage) movieButton5.getScene().getWindow();
        stage.setScene(new Scene(fxmlLoader.load(), 1280, 720));
        MovieInfoController controller = fxmlLoader.getController();
        controller.initialize(5);
        stage.show();
    }

    public void ChangetoMovieInfoScene6(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("movie-info.fxml"));
        Stage stage = (Stage) movieButton6.getScene().getWindow();
        stage.setScene(new Scene(fxmlLoader.load(), 1280, 720));
        MovieInfoController controller = fxmlLoader.getController();
        controller.initialize(6);
        stage.show();
    }

    public void ChangetoMovieInfoScene7(MouseEvent event) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("movie-info.fxml"));
        Stage stage = (Stage) movieButton7.getScene().getWindow();
        stage.setScene(new Scene(fxmlLoader.load(), 1280, 720));
        MovieInfoController controller = fxmlLoader.getController();
        controller.initialize(7);
        stage.show();
    }

    public void ChangetoMovieInfoScene8(MouseEvent event) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("movie-info.fxml"));
        Stage stage = (Stage) movieButton8.getScene().getWindow();
        stage.setScene(new Scene(fxmlLoader.load(), 1280, 720));
        MovieInfoController controller = fxmlLoader.getController();
        controller.initialize(8);
        stage.show();
    }

    public void ChangetoMovieInfoScene9(MouseEvent event) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("movie-info.fxml"));
        Stage stage = (Stage) movieButton9.getScene().getWindow();
        stage.setScene(new Scene(fxmlLoader.load(), 1280, 720));
        MovieInfoController controller = fxmlLoader.getController();
        controller.initialize(9);
        stage.show();
    }

    public void ChangetoMovieInfoScene10(MouseEvent event) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("movie-info.fxml"));
        Stage stage = (Stage) movieButton10.getScene().getWindow();
        stage.setScene(new Scene(fxmlLoader.load(), 1280, 720));
        MovieInfoController controller = fxmlLoader.getController();
        controller.initialize(10);
        stage.show();
    }

}


