

package com.example.rnann.weatherdemo;

import java.io.Serializable;


public class City implements Serializable {

    String cityName;
    String state;

    @Override
    public String toString() {
        return "" + cityName + "," +
                 state ;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public City(String cityName, String state) {

        setCityName(cityName);
        setState(state);
    }
}
