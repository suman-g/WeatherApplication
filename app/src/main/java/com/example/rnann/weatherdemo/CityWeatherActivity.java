


package com.example.rnann.weatherdemo;

import android.app.ProgressDialog;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;

public class CityWeatherActivity extends AppCompatActivity {

    String state;
    String cityName;

    Handler handler = new Handler();
    Boolean x;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_weather);

          //Extracting the City Object passed from main Activiy via Intent

        final City CurrentCity = (City) getIntent().getExtras().get("RAHUL");


        //From the City Object pulling out the cityName and StateName
        state =CurrentCity.getState();
        cityName = CurrentCity.getCityName();



            new GetData(CityWeatherActivity.this).execute(cityName,state);




    }
}
