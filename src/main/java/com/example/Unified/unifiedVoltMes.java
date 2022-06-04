package com.example.Unified;

public class unifiedVoltMes implements unified{

    private final int NG=-1;
    private final int OK=0;

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

    public int measure(String Path, double[] result){
        switch(Path){
            case "test1":
                result[0]=1;
                break;
            case "test2":
                result[0]=2;
                break;
            default:
                return NG;
        }
        return OK;
    }
    
}
