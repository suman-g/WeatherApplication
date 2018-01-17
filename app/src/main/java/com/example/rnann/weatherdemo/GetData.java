



package com.example.rnann.weatherdemo;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import static java.util.Collections.min;



public class GetData extends AsyncTask<String, Void, ArrayList<Weather>> {
    ProgressDialog progressDialog;
    CityWeatherActivity activity;
    String stateCode1;
    String cityName1;
  // ArrayList<Weather> listing;

    public GetData(CityWeatherActivity activity) {
        this.activity = activity;
    }

    @Override
    protected void onPreExecute() {
        // Intializing Progress Dialog
        progressDialog = new ProgressDialog(activity);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading Hourly Data");
        progressDialog.show();
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(final ArrayList<Weather> weathers) {
        super.onPostExecute(weathers);

        progressDialog.dismiss();

        if (weathers == null) {
            Toast.makeText(activity, "No cities match your search query", Toast.LENGTH_LONG).show();
            activity.handler.postDelayed(new Runnable() {
                public void run() {
                    activity.finish();
                }
            }, 5000);
        }
        Log.d("demo", weathers.toString());
//      listing.addAll(weathers);
        Weather weathermax = Collections.max(weathers, new Comparator<Weather>() {
                    @Override
                    public int compare(Weather o1, Weather o2) {
                        return o1.getTemperature().compareTo(o2.getTemperature());
                    }
                });
        for(Weather w : weathers)
        {
            w.setMaximumTemp(weathermax.getTemperature());
        }

        Weather weathermin = Collections.min(weathers, new Comparator<Weather>() {
            @Override
            public int compare(Weather o1, Weather o2) {
                return o1.getTemperature().compareTo(o2.getTemperature());
            }
        });
        for(Weather w : weathers)
        {
            w.setMinimumTemp(weathermin.getTemperature());
        }

        TextView citystate = (TextView) activity.findViewById(R.id.textView_citystate);
        citystate.setText("Current Location: " + cityName1.replaceAll("_", " ") +", " + stateCode1);


        ListView listView = (ListView) activity.findViewById(R.id.list_view_short);
        WeatherAdapter adapter = new WeatherAdapter(activity, R.layout.row_item_layout, weathers);
        listView.setAdapter(adapter);
        adapter.setNotifyOnChange(true);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(activity,DetailsActivity.class);
                    intent.putExtra("key", (Weather) weathers.get(position));
                    intent.putExtra("city", cityName1);
                    intent.putExtra("state", stateCode1);
                    activity.startActivity(intent);
            }
        });


    }


    @Override
    protected ArrayList<Weather> doInBackground(String... params) {
        cityName1 = params[0].replaceAll(" ", "_");
        stateCode1 = params[1].toUpperCase();
        String URL1 = "http://api.wunderground.com/api/c35608ca23d5261f/hourly/q/" + stateCode1 + "/" + cityName1 + ".json";

      if (stateCode1.length() != 2) {
          return null;
      }

      else {
          try {

              URL url = new URL(URL1);
              HttpURLConnection con = (HttpURLConnection) url.openConnection();
              con.setRequestMethod("GET");
              con.connect();

              if (con.getResponseCode() == HttpURLConnection.HTTP_OK) {

                  BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                  StringBuilder sb = new StringBuilder();
                  String line = reader.readLine();
                  while (line != null) {
                      sb.append(line);
                      line = reader.readLine();
                  }
                  String result = sb.toString();

                  return WeatherUtil.WeatherParse.parseJSON(result);


              }
          } catch (MalformedURLException e) {
              e.printStackTrace();
          } catch (ProtocolException e) {
              e.printStackTrace();
          } catch (IOException e) {
              e.printStackTrace();
          } catch (JSONException e) {
              e.printStackTrace();
          }
      }
        return null;

    }
}
