package com.example;

import com.example.Integrated.integVoltWave;

public class LocalMacros {
    public static double[] VoltMeasure(String Path){
        double[] buf = new double[1];
        integVoltWave integVoltWave= new integVoltWave();
        integVoltWave.measure(Path, buf);
        return buf;
    }
}
