package com.example.cat201_project;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


import java.io.*;

public class JsonEditor {
    private static int userArrayIndex = -1 ;
    private  static final String path = "src/main/resources/com/example/cat201_project/JSON_file/";

    public static void setUserArrayIndex(int aryIndex){ userArrayIndex = aryIndex;}
    public static int getUserArrayIndex() {
        return userArrayIndex;
    }
    public static JSONObject getCurrentUserInfo() throws IOException, ParseException {
        String targetFilePath = path + "userInformation.json";
        File inputFile = new File(targetFilePath);

        JSONParser parser = new JSONParser();
        JSONObject userInfo = (JSONObject) parser.parse(new FileReader(inputFile));
        JSONArray userInfoArray = (JSONArray) userInfo.get("userInfo");
        JSONObject data = (JSONObject) userInfoArray.get(userArrayIndex);
        return data;
    }

    public static void addInfo(String filename, JSONObject jsonObj) throws IOException, ParseException {
        String targetFilepath = path + filename;
        File inputFile = new File(targetFilepath);

        JSONParser parser = new JSONParser();
        JSONObject userInfo = (JSONObject) parser.parse(new FileReader(inputFile));
        JSONArray userInfoArray = (JSONArray) userInfo.get("userInfo");
        userInfoArray.add(jsonObj);

        FileWriter writer = new FileWriter(targetFilepath);
        writer.write(userInfo.toString());
        writer.close();
    }

    public static void updatePassword(String filename, String userID, String password) throws IOException, ParseException {

        String targetFilepath = path + filename;
        File inputFile = new File(targetFilepath);

        JSONParser parser = new JSONParser();
        JSONObject userInfo = (JSONObject) parser.parse(new FileReader(inputFile));
        JSONArray userInfoArray = (JSONArray) userInfo.get("userInfo");

        // userInfoArray.size() return 2 if got 2 object
        for(int i = 0 ; i <userInfoArray.size() ; i++) {
            JSONObject tempObj = (JSONObject) userInfoArray.get(i);
            if (tempObj.get("userID").equals(userID)){
                tempObj.put("password",password);
                System.out.println("PASSWORD CHANGED");
            }
        }

        FileWriter writer = new FileWriter(targetFilepath);
        writer.write(userInfo.toString());
        writer.close();
    }

    public static boolean isExistInFile(String filename, String key, String value) throws IOException, ParseException {
        String targetFilepath = path + filename;
        File inputFile = new File(targetFilepath);

        JSONParser parser = new JSONParser();
        JSONObject userInfo = (JSONObject) parser.parse(new FileReader(inputFile));
        JSONArray userInfoArray = (JSONArray) userInfo.get("userInfo");

        for(int i = 0 ; i <userInfoArray.size() ; i++) {
            JSONObject tempObj = (JSONObject) userInfoArray.get(i);
            if (tempObj.get(key).equals(value)){
                return true;
            }
        }
        return false;
    }

    public static String getEmailBasedOnUserID(String filename, String userID) throws IOException, ParseException {
        String targetFilepath = path + filename;
        File inputFile = new File(targetFilepath);

        JSONParser parser = new JSONParser();
        JSONObject userInfo = (JSONObject) parser.parse(new FileReader(inputFile));
        JSONArray userInfoArray = (JSONArray) userInfo.get("userInfo");

        for(int i = 0 ; i <userInfoArray.size() ; i++) {
            JSONObject tempObj = (JSONObject) userInfoArray.get(i);
            if (tempObj.get("userID").equals(userID)){
                return (String) tempObj.get("email");
            }
        }

        return null;
    }

    protected static JSONObject getJSONObject(String fileName) {
        try {
            FileReader reader = new FileReader("src/main/resources/com/example/cat201_project/JSON_file/" + fileName);
            JSONParser jsonParser = new JSONParser();
            Object obj = jsonParser.parse(reader);
            reader.close();
            return (JSONObject) obj;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


}
