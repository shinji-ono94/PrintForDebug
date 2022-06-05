package com.example;

import java.io.FileWriter;
import java.io.IOException;

public class DebugPrintMgr {
    FileWriter fw;

    /**
     * コンストラクタ
     * @param FullPath
     * @throws IOException
     */
    public DebugPrintMgr(String FullPath) throws IOException{
        fw = new FileWriter(FullPath);
    }

    /**
     * コンストラクタ
     * @param folder
     * @param filename
     * @throws IOException
     */
    public DebugPrintMgr(String folder,String filename) throws IOException{
        String FullPath = folder + filename;
        fw = new FileWriter(FullPath);
    }

    /**
     * 閉じる
     * @throws IOException
     */
    public void close() throws IOException{
        fw.close();
    }

    /**
     * 書き込む
     * @param s
     * @throws IOException
     */
    public void write(String s) throws IOException{
        fw.write(s);
    }

    /**
     * 配列を書き込む
     * @param array
     * @throws IOException
     */
    public void arraywrite(double[] array) throws IOException{
        for(int i=0; i < array.length; i++){
            write(String.valueOf(array[i]));
        }
    }
}
