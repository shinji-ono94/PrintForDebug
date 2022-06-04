package com.example;

import java.io.FileWriter;
import java.io.IOException;

public class DebugPrintMgr {
    FileWriter fw;

    public DebugPrintMgr(String FullPath) throws IOException{
        fw = new FileWriter(FullPath);
    }

    public DebugPrintMgr(String folder,String filename) throws IOException{
        String FullPath = folder + filename;
        fw = new FileWriter(FullPath);
    }

    public void close() throws IOException{
        fw.close();
    }

    public void write(String s) throws IOException{
        fw.write(s);
    }

    public void arraywrite(double[] array) throws IOException{
        for(int i=0; i < array.length; i++){
            write(String.valueOf(array[i]));
        }
    }
}
