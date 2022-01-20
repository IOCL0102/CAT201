package com.example.cat201_project;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.apache.commons.io.FileUtils;

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

    public static void AddInfo(String filename, JSONObject jsonObj) throws IOException {
        String filepath = "src/main/resources/com/example/cat201_project/JSON_file/";
        int lineToRemove = findSymbolInFile(filepath + filename);
        deleteLineInFile(lineToRemove,filepath, filename);
        writeIntoFile(filepath,filename, jsonObj);
    }

    public static void deleteLineInFile(int removeLine, String filepath, String filename) throws IOException {
        String path = filepath + filename;
        File inputFile = new File(path);
        File tempFile = new File(filepath + "temp" + filename);
        String currentLine;
        int counting = 0;

        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));


        while ((currentLine = reader.readLine()) != null) {
            counting++;
            if (counting == removeLine || counting == removeLine - 1)
                continue;
            writer.write(currentLine + System.getProperty("line.separator"));
        }

        writer.close();
        reader.close();

        FileUtils.copyFile(tempFile,inputFile);
        boolean deleted =tempFile.delete();

        if (deleted)
             System.out.println("Temporary file deleted ");
        else
            System.out.println("Unable to delete temporary file");
    }

    private static int findSymbolInFile(String filename) throws FileNotFoundException {
        int line = 1;
        Scanner scanner = new Scanner(new FileReader(filename));
        while(scanner.hasNextLine()){
            String x =scanner.nextLine();
            if(scanner.hasNextLine())
                line++;
        }
        return line;
    }

    private static void writeIntoFile(String filepath, String filename, JSONObject data) throws IOException {
        FileWriter writer = new FileWriter(filepath+filename,true);
        writer.write(",\n" + data.toString() + "\n]\n}");
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
