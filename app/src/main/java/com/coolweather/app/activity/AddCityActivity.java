package com.coolweather.app.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.coolweather.app.R;
import com.coolweather.app.model.County;
import com.coolweather.app.model.CoolWeatherDB;


import java.util.ArrayList;
import java.util.List;

public class AddCityActivity extends Activity implements View.OnClickListener{
    private ListView listView;
    private TextView titleText;
    private ArrayAdapter<String> adapter;
    private List<String> dataList = new ArrayList<String>();
    private CoolWeatherDB coolWeatherDB;
    /**
     * 已添加县级列表
     */
    private List<County> addCountyList;
    private County addCounties;

    private Button addCity;


    /**
     * 是否从ChooesActivity中跳转过来
     */
    private boolean isFromChooseAreaActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        isFromChooseAreaActivity = getIntent().getBooleanExtra("from_chooseArea_activity",false);
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        setContentView(R.layout.add_cities);
        listView  = (ListView) findViewById(R.id.county_list);
        titleText = (TextView) findViewById(R.id.title2_text);
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,dataList);
        listView.setAdapter(adapter);
        coolWeatherDB = CoolWeatherDB.getInstance(this);
        Data data = (Data) getApplication();

        //判断是否已添加城市
        if(data.getCountyName1().equals("1")) {



            String countyCode = getIntent().getStringExtra("county_code");
            String countyName = getIntent().getStringExtra("county_name");

            if (countyCode != null) {

                //判断是不是第一次添加
                if (data.getCountyName1().equals("1")) {//未添加过城市

                    data.setCountyName1(countyName);
                    data.setCountyCode1(countyCode);
                } else {
                    data.setCountyName2(countyName);
                    data.setCountyCode2(countyCode);
                }

                listView.setVisibility(View.VISIBLE);

                //判断是不是显示一个城市还是两个城市
                if (data.getCountyName2().equals("2")) {

                    adapter.add(data.getCountyName1());
                } else {

                    for (int i = 0; i < 2; i++) {
                        adapter.add(data.CountyName[i]);
                    }
                }
            }
        }else if(data.getCountyName2().equals("2")){

            String countyCode = getIntent().getStringExtra("county_code");
            String countyName = getIntent().getStringExtra("county_name");

            //判断是不是第一次添加
            if (data.getCountyName1().equals("1")) {//未添加过城市

                data.setCountyName1(countyName);
                data.setCountyCode1(countyCode);
            } else {

                data.setCountyName2(countyName);
                data.setCountyCode2(countyCode);
            }

            listView.setVisibility(View.VISIBLE);

            //判断是不是显示一个城市还是两个城市
            if (data.getCountyName2().equals("2")) {

                adapter.add(data.getCountyName1());
            } else {

                for (int i = 0; i < 2; i++) {
                    adapter.add(data.CountyName[i]);
                }
            }
        }else{
            String[] CountyName = data.getCountyName();
            for (int i = 0; i < CountyName.length; i++) {
                adapter.add(data.CountyName[i]);
            }
        }


            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Data data = (Data) getApplication();
                if(id==0){
                String countyCode =data.getCountyCode1();
                Log.i("-----",countyCode);
                Intent intent = new Intent(AddCityActivity.this,WeatherActivity.class);
                intent.putExtra("county_code",countyCode);
                startActivity(intent);
                    //重置条件

                }else if (id==1){
                    String countyCode =data.getCountyCode2();
                    Log.i("-----",countyCode);
                    Intent intent = new Intent(AddCityActivity.this,WeatherActivity.class);
                    intent.putExtra("county_code",countyCode);
                    startActivity(intent);
                    //重置条件


                }
                finish();

            }
        });
        addCity = (Button) findViewById(R.id.add_city);
        addCity.setOnClickListener(this);


    }




    @Override
    public  void  onClick(View v){
        switch (v.getId()){
            case R.id.add_city:

                Intent intent = new Intent(this,ChooseAreaActivity.class);
                intent.putExtra("from_AddCity_Activity",true);
                startActivity(intent);
                finish();
                break;


            default:
                break;
        }

    }

    @Override
    public void onBackPressed(){
        Intent intent = new Intent(this,WeatherActivity.class);
        startActivity(intent);
    }




}