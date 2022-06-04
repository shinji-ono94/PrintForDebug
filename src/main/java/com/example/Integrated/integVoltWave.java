package com.example.Integrated;

import com.example.Unified.unifiedVoltMes;

public class integVoltWave implements lintegVoltWave{

    @Override
    public void destruction() {
        // TODO Auto-generated method stub
        System.out.println("Destruction Done");
    }

    @Override
    public void Initialize() {
        // TODO Auto-generated method stub
        System.out.println("Initialize Done");        
    }

    @Override
    public int measure(String Path, double[] result) {
        // TODO Auto-generated method stub
        unifiedVoltMes unifiedVoltMes = new unifiedVoltMes();
        unifiedVoltMes.measure(Path,result);
        return 0;
    }
    
}