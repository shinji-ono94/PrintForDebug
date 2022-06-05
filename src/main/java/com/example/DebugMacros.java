package com.example;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class DebugMacros {
    /**
     * folderは固定。filenameは"[現在時刻][Path].txt"
     * ex.filename="1999-12-30-12-00-00-test1.txt"
     * @param Path 計測パス
     */
    public static void DEBUG_PRINT(String Path) throws IOException{
        double[] result = LocalMacros.VoltMeasure(Path);

        String time = getTime();

        String filename = setFileName(time,Path);

        DebugPrintMgr fw = new DebugPrintMgr("C:\\TesterRoot\\Log\\", filename);
        fw.writeDoubleArray(result);
        fw.close();

    }

    /**
     * filenameは"[現在時刻][Path].txt"
     * ex.filename="1999-12-30-12-00-00-test1.txt"
     * @param Path　計測パス
     * @param folder　txtを保存するフォルダー
     */
    public static void DEBUG_PRINT(String Path,String folder) throws IOException{
        double[] result = LocalMacros.VoltMeasure(Path);

        String time = getTime();

        String filename = setFileName(time,Path);

        DebugPrintMgr fw = new DebugPrintMgr(folder, filename);
        fw.writeDoubleArray(result);
        fw.close();
    }

    /**
     * 自由にファイル出力を指定できる。ただ毎回引数入力するの面倒。
     * @param Path　計測パス
     * @param folder　txtを保存するフォルダー
     * @param filename　txt名
     */
    public static void DEBUG_PRINT(String Path,String folder,String filename) throws IOException{
        double[] result = LocalMacros.VoltMeasure(Path);

        filename = filename + ".txt";

        DebugPrintMgr fw = new DebugPrintMgr(folder, filename);
        fw.writeDoubleArray(result);
        fw.close();
    }

    /**
     * フォルダー内にある全テキストファイルを結合する。
     * 結合した結果は、merge.txtに出力する。
     * @param folder　txtが保存されたフォルダー
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static void MERGE_TXT(String folder) throws FileNotFoundException, IOException{
        int MaxSample = getMaxSample(folder);
        
        File file = new File(folder);
        File[] fileList = file.listFiles();

        String[] mergeText = new String[MaxSample];

        if(fileList != null){
            for (int i = 0; i < fileList.length; i++) {
                int order = 0;

                if((fileList[i].getName()).equals("merge.txt")) break;

                try (BufferedReader br = new BufferedReader(new FileReader(fileList[i]))) {
                    String text;
                    while ((text = br.readLine()) != null) {
                        if(i==0){
                            mergeText[order] = text;
                        }else{
                            mergeText[order] = mergeText[order] + "," + text;
                        }
                        order++;
                    }
                    for(int l = order;l < MaxSample;l++){
                        if(i==0){
                            mergeText[l] = "";
                        }else{
                            mergeText[l] = mergeText[l] + ",";
                        }
                    }
                }
            }
            DebugPrintMgr fw = new DebugPrintMgr(folder, "merge.txt");
            fw.writeStringArray(mergeText);
            fw.close();
        }
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

    /**
     * フォルダー内にある全テキストファイルの中で、
     * 最大の行数を返す。
     * @param folder
     * @return
     */
    public static int getMaxSample(String folder){
        int Sample = 0;
        File file = new File(folder);
        File[] fileList = file.listFiles();

        if(fileList != null){
            for (int i = 0; i < fileList.length; i++) {
                int count = 0;
                try {
                    Scanner sc = new Scanner(fileList[i]);
              
                    while(sc.hasNextLine()) {
                      sc.nextLine();
                      count++;
                    }

                    sc.close();

                    if(count > Sample){
                        Sample = count;
                    }
                }catch(Exception e){
                    e.getStackTrace();
                }
            }
        }
        return Sample;
    }

}
