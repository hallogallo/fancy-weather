package com.miri.fancyweather;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * @author Miriam Hüpper
 * Created on 04.12.16.
 */

public class WeatherFragment extends Fragment {
    private String location , icon , temperature , weather;

    /**
     * Refresh weather Data
     * @param weatherObj: weather object given by NetworkTask
     */
    public void setWeather(WeatherObj weatherObj) {
        this.weather = weatherObj.getWeather();
        this.icon = weatherObj.getIcon();
        this.temperature = weatherObj.getTemperature();
        this.location = weatherObj.getLocation();
    }

    /**
     *
     * @param inflater: Layout inflater (given automatically by Main activity)
     * @param container: Container to contain weather view
     * @param savedInstanceState: (given automatically by Main activity)
     * @return View that represents the weather fragment
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View weatherView = inflater.inflate(R.layout.weather_view, container, false);
        Drawable image;

        //choose which image should be displayed, dependent on the weather description
        switch(icon) {
            case "01d" :
            case "01n" :
                image = getResources().getDrawable(R.drawable.sonne);
                break;
            case "02d" :
            case "02n" :
                image = getResources().getDrawable(R.drawable.sonnemitwolken);
                break;
            case "03d" :
            case "03n" :
            case "04d" :
            case "04n" :
            case "50d" :
            case "50n" :
                image = getResources().getDrawable(R.drawable.wolke);
                break;
            case "09d" :
            case "09n" :
                image = getResources().getDrawable(R.drawable.regen);
                break;
            case "11d" :
            case "11n" :
                image = getResources().getDrawable(R.drawable.jewitta);
                break;
            case "13d" :
            case "13n" :
                image = getResources().getDrawable(R.drawable.schnee);
                break;
            case "10d" :
            case "10n" :
                image = getResources().getDrawable(R.drawable.sonnemitregen);
                break;
            default:
                image = null;
                break;
        }
        if(image != null) {
            //load image into image field
            ((ImageView) weatherView.findViewById(R.id.weatherPic)).setImageDrawable(image);
        }

        //load text data into text fields
        ((TextView) weatherView.findViewById(R.id.weatherText)).setText(weather);
        ((TextView) weatherView.findViewById(R.id.temperature)).setText(temperature + "°C");
        ((TextView) weatherView.findViewById(R.id.locationName)).setText(location);
        return weatherView;
    }

}
