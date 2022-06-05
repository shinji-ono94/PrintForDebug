package com.example;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class DebugMacros {
    /**
     * folderは固定。filenameは"[現在時刻][Path].txt"
     * ex.filename="1999-12-30-12-00-00-test1.txt"
     * @param Path 計測パス
     */
    public static void DEBUG_PRINT(String Path){
        double[] result = LocalMacros.VoltMeasure(Path);

        String time = getTime();

        String filename = setFileName(time,Path);

        try {
            DebugPrintMgr fw = new DebugPrintMgr("C:\\TesterRoot\\Log\\", filename);
            printArray(fw, result);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * filenameは"[現在時刻][Path].txt"
     * ex.filename="1999-12-30-12-00-00-test1.txt"
     * @param Path　計測パス
     * @param folder　txtを保存するフォルダー
     */
    public static void DEBUG_PRINT(String Path,String folder){
        double[] result = LocalMacros.VoltMeasure(Path);

        String time = getTime();

        String filename = setFileName(time,Path);

        try {
            DebugPrintMgr fw = new DebugPrintMgr(folder, filename);
            printArray(fw, result);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * 自由にファイル出力を指定できる。ただ毎回引数入力するの面倒。
     * @param Path　計測パス
     * @param folder　txtを保存するフォルダー
     * @param filename　txt名
     */
    public static void DEBUG_PRINT(String Path,String folder,String filename){
        double[] result = LocalMacros.VoltMeasure(Path);

        filename = filename + ".txt";

        try {
            DebugPrintMgr fw = new DebugPrintMgr(folder, filename);
            printArray(fw, result);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void MERGE_TXT(String folder) throws FileNotFoundException, IOException{
        File file = new File(folder);
        File[] fileList = file.listFiles();

        int order;
        ArrayList<String> strList = new ArrayList<String>();

        if(fileList != null){
            for (int i = 0; i < fileList.length; i++) {
                order = 0;
                try (BufferedReader br = new BufferedReader(new FileReader(fileList[i]))) {
                    String text;
                    while ((text = br.readLine()) != null) {
                        String str1 = strList.get(order);
                        if(i != 0) text=","+text;
                        String str = str1.concat(text);
                        strList.set(order,str);
                        order++;
                    }
                }
            }

            DebugPrintMgr fw = new DebugPrintMgr(folder, "merge.txt");
        }
    }

    /**
     * 配列を出力する。
     * @param fw　プリントオブジェクト
     * @param array　配列(double)
     * @throws IOException
     */
    public static void printArray(DebugPrintMgr fw,double[] array) throws IOException{
        fw.arraywrite(array);
        fw.close();
    }

    /**
     * 時間取得
     * @return　時間("yyyy-MM-dd-HH-mm-ss")
     */
    public static String getTime(){
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        String time = sdf.format(timestamp);
        return time;
    }

    /**
     * ファイル名を作成する。
     * @param s
     * @return
     */
    public static String setFileName(String... s){
        String filename = "";
        for (int i = 0; i < s.length; i++) {
            filename += s;
            if(i == s.length){
                filename += ".txt";
            }else{
                filename += "-";
            }
        }
        return filename;
    }

}
