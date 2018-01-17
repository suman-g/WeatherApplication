

package com.example.rnann.weatherdemo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;



public class WeatherUtil {
    public static class WeatherParse {
                            static ArrayList<Weather> parseJSON (String input) throws JSONException {
                                ArrayList<Weather> list = new ArrayList<Weather>();
                                JSONObject root = new  JSONObject(input);
                                JSONArray forecast = root.getJSONArray("hourly_forecast");
                                for(int i= 0; i <forecast.length(); i++){
                                    Weather we = new Weather();
                                    JSONObject job = forecast.getJSONObject(i);
                                    we.setTime(job.getJSONObject("FCTTIME").getString("civil"));
                                    we.setTemperature(job.getJSONObject("temp").getString("english"));
                                    we.setDewpoint(job.getJSONObject("dewpoint").getString("english"));
                                    we.setClouds(job.getString("condition"));
                                    we.setIconUrl(job.getString("icon_url"));
                                    we.setWindSpeed(job.getJSONObject("wspd").getString("english"));
                                    we.setWindDirection(job.getJSONObject("wdir").getString("dir") );
                                    we.setWinddegrees(job.getJSONObject("wdir").getString("degrees"));
                                    we.setClimateType(job.getString("wx"));
                                    we.setHumidity(job.getString("humidity"));
                                    we.setFeelsLike(job.getJSONObject("feelslike").getString("english"));
                                    we.setMaximumTemp(job.getJSONObject("temp").getString("english"));
                                    we.setMinimumTemp(job.getJSONObject("temp").getString("english"));
                                    we.setPressure(job.getJSONObject("mslp").getString("metric"));
                                    list.add(we);
                                }
//
                                return list;
                            }
    }
}
