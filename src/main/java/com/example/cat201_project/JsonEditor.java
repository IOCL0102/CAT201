package com.example.cat201_project;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.*;
import java.util.Scanner;

public class JsonEditor {
    private static int userArrayIndex = -1 ;
    private JSONObject userInfo ;
    private JSONArray userData ;

    public JsonEditor(String fileName){
        userInfo = getJSONObject(fileName);
        userData = (JSONArray) userInfo.get("userInfo");
        userArrayIndex = -1;
    }


    public JSONObject getUserInfo() {
        return userInfo;
    }

    public JSONArray getUserData() {
        return userData;
    }
    public static void setUserArrayIndex(int aryIndex){ userArrayIndex = aryIndex;}
    public static int getUserArrayIndex() {
        return userArrayIndex;
    }

    public static void deleteLineInFile(int removeLine, String filename) throws IOException {
        File inputFile = new File(filename);
        File tempFile = new File("temp"+filename);
        String currentLine;
        int counting = 0;

        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));


        while ((currentLine = reader.readLine()) != null) {
            counting++;
            if (counting == removeLine)
                continue;
            writer.write(currentLine + System.getProperty("line.separator"));
        }


        writer.close();
        reader.close();

        boolean x = inputFile.renameTo(new File("unwanted.json"));
        // boolean y = tempFile.renameTo(new File("haha.json"));
    }


    private static int findSymbolInFile(String filename , String Symbol) throws FileNotFoundException {
        int line = 1;
        Scanner scanner = new Scanner(new FileReader(filename));
        while(scanner.hasNextLine()){
            String x =scanner.nextLine();

            if(x.equals(Symbol))
                break;

            if(scanner.hasNextLine())
                line++;
        }
        return line;
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
