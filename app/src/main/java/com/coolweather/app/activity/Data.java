package com.coolweather.app.activity;

import android.app.Application;

public class Data extends Application {

    @Override
    public void onCreate(){
        super.onCreate();

    }

    String countyCode;

    public String getCountyCode() {
        return countyCode;
    }

    public void setCountyCode(String countyCode) {
        this.countyCode = countyCode;
    }
}
