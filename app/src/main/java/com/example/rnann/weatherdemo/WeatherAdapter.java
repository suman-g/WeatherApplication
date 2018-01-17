


package com.example.rnann.weatherdemo;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;



public class WeatherAdapter extends ArrayAdapter<Weather> {
        List<Weather> wData;
        Context wContext;
        int wResource;
    public WeatherAdapter(Context context, int resource, List<Weather> objects) {
        super(context, resource, objects);

        this.wContext = context;
        this.wData = objects;
        this.wResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {



        if(convertView == null)
        {
            LayoutInflater inflater = (LayoutInflater) wContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(wResource, parent, false);
        }

        Weather weatherobj = wData.get(position);
        TextView time1 =  (TextView) convertView.findViewById(R.id.textView_time);
        time1.setText(weatherobj.getTime());
        TextView climate1 =  (TextView) convertView.findViewById(R.id.textView_climateType);
        climate1.setText(weatherobj.getClimateType());
        TextView temperature1 =  (TextView) convertView.findViewById(R.id.textView_temperature);
        temperature1.setText(weatherobj.getTemperature() + "\u00b0" + " F");
        ImageView icon1 = (ImageView) convertView.findViewById(R.id.imageView_icon);
        Picasso.with(wContext).load(weatherobj.getIconUrl()).into(icon1);
        return convertView;
    }
}
