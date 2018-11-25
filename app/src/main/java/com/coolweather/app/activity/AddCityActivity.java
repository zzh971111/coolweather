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


        String countyCode =getIntent().getStringExtra("county_code");
        System.out.println("countyCode111"+countyCode);
        if(countyCode!=null) {
            listView.setVisibility(View.VISIBLE);
            adapter.add(countyCode);

        }


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Data data=(Data)getApplication();
                String countyCode =getIntent().getStringExtra("county_code");
                Log.i("-----",countyCode);
                System.out.println("运行成功1");
                if(isFromChooseAreaActivity){

                    System.out.println("运行成功2");
                    //String countyCode=data.getCountyCode();

                    //Log.i("111111111111111");
                    addCountyList = coolWeatherDB.loadCounties(addCounties.getId());

                        adapter.notifyDataSetChanged();
                        listView.setSelection(0);
                        Intent intent = new Intent(AddCityActivity.this,WeatherActivity.class);
                        intent.putExtra("county_code",countyCode);
                        startActivity(intent);
                        finish();

                }



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
