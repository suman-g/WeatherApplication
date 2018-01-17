

package com.example.rnann.weatherdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<City> cities;
    private ArrayAdapter<City> ArrayAdapter;
    private ListView listView;
    private EditText city;
    private EditText state;
    private City newCity;
    private String cityName;
    private String stateName;
    private TextView nofavs;
    public static ArrayList<Favorites> favlist = new ArrayList<Favorites>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        cities = new ArrayList<>();

        listView = (ListView) findViewById(R.id.ListView);

        final FavoriteAdapter adapter = new FavoriteAdapter(this, R.layout.row_favorite_layout, favlist);
        listView.setAdapter(adapter);
        adapter.setNotifyOnChange(true);


        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                favlist.remove(position);
                adapter.notifyDataSetChanged();
                if(favlist.size() == 0)
                {
                    nofavs = (TextView) findViewById(R.id.TextView_nofav);
                    nofavs.setText("There is no city in your Favorites");
                }
                return true;
            }
        });

        findViewById(R.id.SubmitButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                city = (EditText) findViewById(R.id.cityEditText);

                cityName = city.getText().toString();

                state = (EditText) findViewById(R.id.stateEditText);

                stateName = state.getText().toString();

                //Storing cityName and stateName in the newcity Object

                newCity = new City(cityName, stateName);

                //Passing the newCity Object to cityWheatherActivity via Intent

                Intent i = new Intent(MainActivity.this, CityWeatherActivity.class);
                i.putExtra("RAHUL", newCity);
                startActivity(i);

            }
        });

            if(favlist.size() != 0){
                nofavs = (TextView) findViewById(R.id.TextView_nofav);
                nofavs.setText("Favorites");
            }


    }

}
