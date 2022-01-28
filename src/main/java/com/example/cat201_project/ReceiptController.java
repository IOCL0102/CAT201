package com.example.cat201_project;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.scene.image.Image;

import java.io.*;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.net.URL;
import java.util.ResourceBundle;
import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;

public class ReceiptController implements Initializable {

    @FXML
    private Button BookedTicket;
    @FXML
    private Button Profile;
    @FXML
    private Button logout;
    @FXML
    private Button home;


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
    private ImageView MoviePoster;
    @FXML
    private ImageView ReceiptQR;

    private JSONArray orderData = null;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) throws NullPointerException{
        JSONObject orderInfo = JsonEditor.getJSONObject("orderInfo.json");
        orderData = (JSONArray) orderInfo.get("orderInfo");

        String AT = (((JSONObject)orderData.get(orderData.size() - 1)).get("AdultTicket")).toString();
        String CT = (((JSONObject)orderData.get(orderData.size() - 1)).get("ChildrenTicket")).toString();
        String EXP = (((JSONObject)orderData.get(orderData.size() - 1)).get("Experience")).toString();
        String TOT = (((JSONObject)orderData.get(orderData.size() - 1)).get("Total")).toString();

        //Initialise movie details from JSON file
        AdultTicket.setText(AT);
        ChildrenTicket.setText(CT);
        Experience.setText(EXP);
        Total.setText(TOT);

        //Initialise movie poster using path from JSON file
        Image image;
        try
        {
            String moviePosterSource = (((JSONObject) orderData.get(orderData.size() - 1)).get("Poster")).toString();
            image = new Image(new FileInputStream(moviePosterSource));
            MoviePoster.setImage(image);

        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }

        //Get order details from JSON file to be embedded on receipt
        String movie = (((JSONObject)orderData.get(orderData.size() - 1)).get("Movie")).toString();
        String date = (((JSONObject)orderData.get(orderData.size() - 1)).get("Date")).toString();
        String time = (((JSONObject)orderData.get(orderData.size() - 1)).get("Time")).toString();
        String seats = (((JSONObject)orderData.get(orderData.size() - 1)).get("Seats")).toString();
        Movie.setText(movie);
        Date.setText(date);
        Time.setText(time);
        Seats.setText(seats);

        //Generate a QR code to be embedded on the receipt
        String str= movie+date+time+seats;//concatenate strings of data into one string
        String path = "src/main/resources/com/example/cat201_project/img/QR"+ orderData.size() + ".png";
        generateQR(str, path);

        //Display QR code onto receipt
        Image image2;
        try
        {
            String QRSource = (((JSONObject) orderData.get(orderData.size() - 1)).get("QR")).toString();
            image2 = new Image(new FileInputStream(QRSource));
            ReceiptQR.setImage(image2);

        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
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

    public void handleHomeBttn(){
        //insert logout function
    }

    public void generateQR(String str, String path){
        ByteArrayOutputStream out = QRCode.from(str).to(ImageType.JPG).stream();
        File f = new File(path);
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(f);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            fos.write(out.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //insert updated qr path to JSON file
        JSONParser parser = new JSONParser();
        JSONObject ordInf = null;
        File inputFile = new File("src/main/resources/com/example/cat201_project/JSON_file/orderInfo.json");
        try{
            ordInf = (JSONObject) parser.parse(new FileReader(inputFile));
            JSONObject obj = (JSONObject)((JSONArray)ordInf.get("orderInfo")).get(orderData.size() - 1);
            obj.put("QR", path);
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch(IOException e){
            e.printStackTrace();
        } catch(ParseException e){
            e.printStackTrace();
        }
        FileWriter writer = null;
        try {
            writer = new FileWriter("src/main/resources/com/example/cat201_project/JSON_file/orderInfo.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            writer.write(ordInf.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("QR Code created successfully.");
    }
}
