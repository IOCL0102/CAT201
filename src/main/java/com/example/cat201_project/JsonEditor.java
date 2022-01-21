package com.example.cat201_project;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.apache.commons.io.FileUtils;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.Scanner;

public class JsonEditor {
    private static int userArrayIndex = -1 ;
    public static void setUserArrayIndex(int aryIndex){ userArrayIndex = aryIndex;}
    public static int getUserArrayIndex() {
        return userArrayIndex;
    }

    public static void AddInfo(String filename, JSONObject jsonObj) throws IOException, ParseException {
        String path = "src/main/resources/com/example/cat201_project/JSON_file/";
        String targetFilepath = "userInformation.json";
        File inputFile = new File(path + targetFilepath);


        JSONParser parser = new JSONParser();
        JSONObject userInfo = (JSONObject) parser.parse(new FileReader(inputFile));
        JSONArray userInfoArray = (JSONArray) userInfo.get("userInfo");
        userInfoArray.add(jsonObj);

        FileWriter writer = new FileWriter(path+targetFilepath);
        writer.write(userInfo.toString());
        writer.close();

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
