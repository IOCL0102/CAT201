package com.example.cat201_project;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.util.Duration;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable{

    @FXML private Text bannerLogoNameText;
    @FXML private Text bannerText;
    @FXML private Text forgotPwText;
    @FXML private Text signUpText;
    @FXML private Text userIDText;
    @FXML private Text errorInvalidAccMessage;

    @FXML private TextField userIDTextField;
    @FXML private TextField userPwTextField;

    @FXML private Button loginButton;
    @FXML private ImageView loginImage;
    @FXML private AnchorPane loginLeftPage;

    private JSONArray userData = null;
    private int userArrayIndex ;

    public LoginController() {
        userArrayIndex = -1;
    }
    public  void setUserArrayIndex(int aryIndex){ userArrayIndex = aryIndex;}
    public int getUserArrayIndex() {
        return userArrayIndex;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        JSONObject userInfo = getJSONObject("userInformation.json");
        userData = (JSONArray) userInfo.get("userInfo");
        errorInvalidAccMessage.setVisible(false);
    }

    public void validateLogin(ActionEvent e){
        String userID = userIDTextField.getText();
        String userPw = userPwTextField.getText();
        boolean isValidAcc = false;

        // get all the data from userInformation.json
        for(int i = 0; i < userData.size(); i++){
            String tempUserID = (((JSONObject)userData.get(i)).get("userID")).toString();
            String tempUserPw = (((JSONObject)userData.get(i)).get("Password")).toString();

            if( tempUserID.equals(userID) && tempUserPw.equals(userPw)) {
                System.out.println("you have logined");
                setUserArrayIndex(i);

                System.out.println("your index is "+ getUserArrayIndex());

                isValidAcc = true;
            }
        }

        // if no account is found on userInformation.json , display error message
        if(isValidAcc == false){
            errorInvalidAccMessage.setVisible(true);
            FadeTransition fadeMessage = new FadeTransition(Duration.millis(6000), errorInvalidAccMessage);
            fadeMessage.setFromValue(1);
            fadeMessage.setToValue(0);
            fadeMessage.play();
        }
    }

    private static JSONObject getJSONObject(String fileName) {
        try {
            FileReader reader = new FileReader("src/main/resources/com/example/cat201_project/JSON_file/" + fileName);
            JSONParser jsonParser = new JSONParser();
            Object obj = jsonParser.parse(reader);
            return (JSONObject) obj;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}