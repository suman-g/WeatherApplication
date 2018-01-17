

package com.example.rnann.weatherdemo;

import android.content.Intent;
import android.media.Image;
import android.support.annotation.MainThread;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class DetailsActivity extends AppCompatActivity {
    Weather displayobj;
    String citynew;
    String statenew;
    Favorites favi;
    DateFormat df;
    Date dateobj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        displayobj = (Weather) getIntent().getExtras().getSerializable("key");
        citynew = getIntent().getExtras().getString("city");
        statenew = getIntent().getExtras().getString("state");
        TextView citydetails = (TextView) findViewById(R.id.TextView_citydetails);
        citydetails.setText("Current Location: " + citynew.replaceAll("_", " ") + ", " + statenew + " (" + displayobj.getTime() + ")");
        ImageView iconlarge = (ImageView) findViewById(R.id.imageView_iconlarge);
        Picasso.with(this).load(displayobj.getIconUrl()).into(iconlarge);
        TextView tempview = (TextView) findViewById(R.id.textView_detailsTemp);
        tempview.setText(displayobj.getTemperature() + "\u00b0" + " F");
        TextView climateview = (TextView) findViewById(R.id.textView_detailsClimateType);
        climateview.setText(displayobj.getClimateType());
        TextView maxtemp = (TextView) findViewById(R.id.textView_maxTemp);
        maxtemp.setText("Max Temperature: " + displayobj.getMaximumTemp() + " Fahrenheit");
        TextView mintemp = (TextView) findViewById(R.id.textView_MinTemp);
        mintemp.setText("Min Temperature: " + displayobj.getMinimumTemp() + " Fahrenheit");
        TextView feels = (TextView) findViewById(R.id.textView_feelsLike);
        feels.setText("Feels Like: " + displayobj.getFeelsLike() + " Fahrenheit");
        TextView humid = (TextView) findViewById(R.id.textView_Humidity);
        humid.setText("Humidity: " + displayobj.getHumidity() + "%");
        TextView Dew = (TextView) findViewById(R.id.textView_dewPoint);
        Dew.setText("Dewpoint: " + displayobj.getDewpoint() + " Fahrenheit");
        TextView Press = (TextView) findViewById(R.id.textView_pressure);
        Press.setText("Pressure: " + displayobj.getPressure() + " hPa");
        TextView cloud = (TextView) findViewById(R.id.textView_clouds);
        cloud.setText("Clouds: " + displayobj.getClouds());
        TextView winds = (TextView) findViewById(R.id.textView_winds);
        winds.setText("Winds: " + displayobj.getWindSpeed() + " mph, " + Stringwinddirection());


    }

    private String Stringwinddirection() {
        String windir = displayobj.getWindDirection();
        String windegrees = displayobj.getWinddegrees();
        StringBuilder sbu = new StringBuilder();
        sbu.append(windegrees);
        sbu.append("\u00b0");
        sbu.append(" ");

        for (int i = 0; i < windir.length(); i++) {
            switch (windir.charAt(i)) {
                case 'N':
                    sbu.append("North ");
                    break;
                case 'E':
                    sbu.append("East ");
                    break;
                case 'W':
                    sbu.append("West ");
                    break;
                case 'S':
                    sbu.append("South ");
                    break;
            }
        }
        return sbu.toString();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.game_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.menu_text: {
                favi = new Favorites();
                for (Favorites f : MainActivity.favlist) {
                    if (f.getFavCity().replaceAll("_", " ").equals(citynew.replaceAll("_", " "))) {
                        df = new SimpleDateFormat("MM/dd/yy");
                        dateobj = new Date();
                        f.setFavDate(df.format(dateobj));
                        f.setFavTemp(displayobj.getTemperature());
                        Toast.makeText(DetailsActivity.this, "Updated Favorites Record", Toast.LENGTH_LONG).show();
                        Intent intent2 = new Intent(DetailsActivity.this, MainActivity.class);
                        startActivity(intent2);
                        return true;
                    }
                }

                    favi.setFavCity(citynew);
                    favi.setFavState(statenew);
                    df = new SimpleDateFormat("MM/dd/yy");
                    dateobj = new Date();
                    favi.setFavDate(df.format(dateobj));
                    favi.setFavTemp(displayobj.getTemperature());
                    MainActivity.favlist.add(favi);
                    Toast.makeText(DetailsActivity.this, "Added to Favorites", Toast.LENGTH_LONG).show();
                    Intent intent1 = new Intent(DetailsActivity.this, MainActivity.class);
                    startActivity(intent1);
                    return true;


            }
                        default:
                            return super.onOptionsItemSelected(item);
                    }
                }

        }

