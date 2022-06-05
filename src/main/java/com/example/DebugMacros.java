package com.example;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class DebugMacros {
    /**
     * folderは固定。filenameは"[現在時刻][Path].txt"
     * ex.filename="1999-12-30-12-00-00-test1.txt"
     * @param Path 計測パス
     */
    public static void DEBUG_PRINT(String Path){
        double[] result = LocalMacros.VoltMeasure(Path);

        String time = getTime();

        String filename = time + "-" + Path + ".txt";

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

        String filename = time + "-" + Path + ".txt";

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

}
