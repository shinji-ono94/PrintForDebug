package com.example.Integrated;

public interface lintegVoltWave {
    /**
     * デストラクション
     */
    void destruction();

    /**
     * 初期化
     */
    void Initialize();

    /**
     * 計測
     */
    int measure(String Path, double[] result);
}
