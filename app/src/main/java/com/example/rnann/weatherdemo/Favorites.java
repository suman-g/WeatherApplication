



package com.example.rnann.weatherdemo;

import java.io.Serializable;



public class Favorites implements Serializable {
    String favCity;
    String favState;
    String favDate;
    String favTemp;

    public String getFavCity() {
        return favCity;
    }

    public void setFavCity(String favCity) {
        this.favCity = favCity;
    }

    public String getFavState() {
        return favState;
    }

    public void setFavState(String favState) {
        this.favState = favState;
    }

    public String getFavDate() {
        return favDate;
    }

    public void setFavDate(String favDate) {
        this.favDate = favDate;
    }

    public String getFavTemp() {
        return favTemp;
    }

    public void setFavTemp(String favTemp) {
        this.favTemp = favTemp;
    }

    @Override
    public String toString() {
        return "Favorites{" +
                "favCity='" + favCity + '\'' +
                ", favState='" + favState + '\'' +
                ", favDate='" + favDate + '\'' +
                ", favTemp='" + favTemp + '\'' +
                '}';
    }
}
