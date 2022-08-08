package com.yuzhi.home.utils;

import java.io.*;

/**
 * 读取json文件
 */
public class JsonUtil {

    public static final String FILE_NAME_10 = "/Volumes/d/work/all_event_10.json";
    public static final String FILE_NAME_1000 = "/Volumes/d/work/all_event_1000.json";
    public static final String FILE_NAME_10000 = "/Volumes/d/work/all_event_10000.json";

    //读取json文件
    public static String readJsonFile(String filename) {
        String jsonStr = "";
        try {
            File jsonFile = new File(filename);
            FileReader fileReader = new FileReader(jsonFile);
            Reader reader = new InputStreamReader(new FileInputStream(jsonFile), "utf-8");
            int ch = 0;
            StringBuffer sb = new StringBuffer();
            while ((ch = reader.read()) != -1) {
                sb.append((char) ch);
            }
            fileReader.close();
            reader.close();
            jsonStr = sb.toString();
//            System.out.println(jsonStr.getClass().getName());
            return jsonStr;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        String result = new JsonUtil().readJsonFile(FILE_NAME_10);

        System.out.println(result);
    }
}
