package com.example.payment_module_cinema_project;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.scene.image.Image;

import java.io.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
//import com.google.zxing.NotFoundException;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
//import java.nio.file.Path;
import java.nio.file.Paths;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
//import org.json.simple.parser.ParseException;
import java.net.URL;
import java.util.ResourceBundle;


public class ReceiptController implements Initializable {

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
    private ImageView MoviePoster;
    @FXML
    private ImageView ReceiptQR;

    private JSONArray orderData = null;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        JSONObject orderInfo = JsonEditor.getJSONObject("orderInfo.json");
        orderData = (JSONArray) orderInfo.get("orderInfo");

        String AT = (((JSONObject)orderData.get(orderData.size() - 1)).get("AdultTicket")).toString();
        String CT = (((JSONObject)orderData.get(orderData.size() - 1)).get("ChildrenTicket")).toString();
        String EXP = (((JSONObject)orderData.get(orderData.size() - 1)).get("Experience")).toString();
        String TOT = (((JSONObject)orderData.get(orderData.size() - 1)).get("Total")).toString();

        AdultTicket.setText(AT);
        ChildrenTicket.setText(CT);
        Experience.setText(EXP);
        Total.setText(TOT);

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

        String movie = (((JSONObject)orderData.get(orderData.size() - 1)).get("Movie")).toString();
        String date = (((JSONObject)orderData.get(orderData.size() - 1)).get("Date")).toString();
        String time = (((JSONObject)orderData.get(orderData.size() - 1)).get("Time")).toString();
        String seats = (((JSONObject)orderData.get(orderData.size() - 1)).get("Seats")).toString();

        Movie.setText(movie);
        Date.setText(date);
        Time.setText(time);
        Seats.setText(seats);

        String str= movie+date+time+seats;//concatenate strings of data into one string

        String path = "src/main/resources/com/example/cat201_project/img";

        String charset = "UTF-8";
        Map<EncodeHintType, ErrorCorrectionLevel> hashMap = new HashMap<>();

        hashMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);

        try {
            generateQR(str, path, charset, hashMap, 200, 200);
        } catch (WriterException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("QR Code created successfully.");

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

    public void generateQR(String data, String filepath, String charset, Map m, int h, int w) throws WriterException, IOException
    {
        BitMatrix BM = new MultiFormatWriter().encode(new String(data.getBytes(charset), charset), BarcodeFormat.QR_CODE, w, h);
        MatrixToImageWriter.writeToPath(BM, filepath.substring(filepath.lastIndexOf('.') + 1), Paths.get(filepath));

        JSONObject orderInfo = (JSONObject)orderData.get(orderData.size() - 1);
        orderInfo.put("QR", filepath); //Update QR file path in JSON file
    }
}