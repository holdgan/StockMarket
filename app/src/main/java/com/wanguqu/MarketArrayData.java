package com.wanguqu;

import android.graphics.Color;

public class MarketArrayData {
    private int mBackgroundColor;
    private String mName;
    private String mPrice;
    private String mWave;
    private String mProportion;
    private Boolean mState;

    public MarketArrayData(int backgroundColor, String name, String price, String wave, String proportion, boolean state) {
        mBackgroundColor = backgroundColor;
        mName = name;
        mPrice = price;
        mWave = wave;
        mProportion = proportion;
        mState = state;
    }

    public int getBackgroundColor() {
        return mBackgroundColor;
    }

    public int getUpColor() {
        return Color.argb(255, 0, 102, 51);
    }

    public String getName() {
        return mName;
    }

    public String getPrice() {
        return mPrice;
    }

    public String getWave() {
        return mWave;
    }

    public String getProportion() {
        return mProportion;
    }

    public Boolean getState() {
        return mState;
    }
}
