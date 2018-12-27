package com.coolweather.app.activity;

import android.app.Application;

public class Data extends Application {

    @Override
    public void onCreate(){
        super.onCreate();
    }

    String[] CountyCode={"1","2"};

    String[] CountyName={"1","2"};

    public String[] getCountyCode() {
        return CountyCode;
    }

    public String[] getCountyName() {
        return CountyName;
    }

    public void setCountyCode(String[] countyCode) {
        CountyCode = countyCode;
    }

    public String getCountyName1() {
        return CountyName[0];
    }

    public void setCountyName1(String countyName) {
        CountyName[0] = countyName;
    }

    public String getCountyName2() {
        return CountyName[1];
    }

    public void setCountyName2(String countyName) {
        CountyName[1] = countyName;
    }


    public String getCountyCode1() {
        return CountyCode[0];
    }

    public void setCountyCode1(String countyCode) {
        CountyCode[0] = countyCode;
    }

    public String getCountyCode2() {
        return CountyCode[1];
    }

    public void setCountyCode2(String countyCode) {
        CountyCode[1] = countyCode;
    }

}
