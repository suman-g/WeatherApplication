//Vidhan Shukla and Rahul Reddy Nannuru
//Homework 5
//Weather App



package com.example.rnann.weatherdemo;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;
import java.util.zip.Inflater;



public class FavoriteAdapter extends ArrayAdapter<Favorites> {

    Context fContext;
    int fResource;
    List fData;

    public FavoriteAdapter(Context context, int resource, List<Favorites> objects) {
        super(context, resource, objects);
        this.fContext = context;
        this.fResource = resource;
        this.fData = objects;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView ==  null){
            LayoutInflater inflater = (LayoutInflater) fContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(fResource,parent,false);
        }
        Favorites favobj = (Favorites) fData.get(position);
        TextView favCityState = (TextView) convertView.findViewById(R.id.textView_favcity);
        favCityState.setText(favobj.getFavCity().replaceAll("_", " ") +", " + favobj.getFavState());
        TextView favTime = (TextView) convertView.findViewById(R.id.textView_favtime);
        favTime.setText("Updated on: " + favobj.getFavDate());
        TextView favTemp = (TextView) convertView.findViewById(R.id.textView_favtemp);
        favTemp.setText(favobj.getFavTemp()+ "\u00b0" + " F");
        return convertView;
    }
}
